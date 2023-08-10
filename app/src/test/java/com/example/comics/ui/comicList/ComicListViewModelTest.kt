package com.example.comics.ui.comicList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.comics.DispatcherProviderTest
import com.example.comics.ui.comicList.state.ComicState
import com.example.domain.dto.ComicsDataDTO
import com.example.domain.usecases.GetComicDataUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.slot
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
class ComicListViewModelTest {

    @Rule @JvmField
    val rule = InstantTaskExecutorRule()

    private val dispatcher = DispatcherProviderTest(UnconfinedTestDispatcher())

    @RelaxedMockK
    private lateinit var getComicDataUseCase: GetComicDataUseCase

    private lateinit var viewModel: ComicListViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = ComicListViewModel(getComicDataUseCase, dispatcher)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `When initialize is called and UseCase response without error, Then should send ShowComicList with comic items to layer of view`() = runTest {
        //GIVEN
        val response = ComicsDataDTO(data = ComicsDataDTO.Data(results = emptyList()))
        coEvery { getComicDataUseCase.execute(Unit) } returns response

        val captors = mutableListOf<ComicState>()
        val stateObserver = mockk<Observer<ComicState>>()
        val slot = slot<ComicState>()
        every { stateObserver.onChanged(capture(slot)) } answers {
            captors.add(slot.captured)
        }
        viewModel.state.observeForever(stateObserver)

        val comicListObserver = mockk<Observer<List<ComicsDataDTO.Result>>>()
        every { comicListObserver.onChanged(any()) } answers {}
        viewModel.comicList.observeForever(comicListObserver)

        //WHEN
        viewModel.initialize()

        //THEN
        verify(exactly = 1) {
            stateObserver.onChanged(ComicState.ShowLoading)
        }
        verify(exactly = 1) {
            val expected = captors.filterIsInstance(ComicState.ShowComicList::class.java).first()
            stateObserver.onChanged(expected)
        }
        verify(exactly = 1)  {
            comicListObserver.onChanged(response.data.results)
        }
    }

    @Test
    fun `When initialize is called and UseCase response with error, Then should send ShowError to layer of view`() = runTest {
        //GIVEN
        coEvery { getComicDataUseCase.execute(Unit) } coAnswers {
            throw RuntimeException()
        }

        val captors = mutableListOf<ComicState>()
        val stateObserver = mockk<Observer<ComicState>>()
        val slot = slot<ComicState>()
        every { stateObserver.onChanged(capture(slot)) } answers {
            captors.add(slot.captured)
        }
        viewModel.state.observeForever(stateObserver)

        val comicListObserver = mockk<Observer<List<ComicsDataDTO.Result>>>()
        every { comicListObserver.onChanged(any()) } answers {}
        viewModel.comicList.observeForever(comicListObserver)

        //WHEN
        viewModel.initialize()

        //THEN
        verify(exactly = 1) {
            stateObserver.onChanged(ComicState.ShowLoading)
        }
        verify(exactly = 1) {
            val expected = captors.filterIsInstance(ComicState.ShowError::class.java).first()
            stateObserver.onChanged(expected)
        }
        verify(exactly = 0) {
            comicListObserver.onChanged(any())
        }
    }
}