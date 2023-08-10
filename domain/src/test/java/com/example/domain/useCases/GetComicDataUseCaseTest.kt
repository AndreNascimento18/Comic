package com.example.domain.useCases

import com.example.data.network.Repository
import com.example.data.schema.SchemaComicData
import com.example.domain.dto.ComicsDataDTO
import com.example.domain.mapper.ComicsDataMapper
import com.example.domain.usecases.GetComicDataUseCase
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
class GetComicDataUseCaseTest {

    @RelaxedMockK
    private lateinit var mapper: ComicsDataMapper
    @RelaxedMockK
    private lateinit var repository: Repository

    private lateinit var useCase: GetComicDataUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        useCase = GetComicDataUseCase(mapper, repository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `When UseCase is executed without error, Then should response with ComicsDataDTO`() = runTest {
        //GIVEN
        val response = mockk<SchemaComicData>()
        coEvery { repository.getComics() } returns response

        val dto = mockk<ComicsDataDTO>()
        every { mapper.map(response) } returns dto

        //WHEN
        val result = useCase.execute(Unit)

        //THEN
        Truth.assertThat(result).isEqualTo(dto)
    }

    @Test
    fun `When UseCase is executed with error, Then throws Exception`() = runTest {
        //GIVEN
        coEvery { repository.getComics() } coAnswers {
            throw RuntimeException()
        }
        try {
            //WHEN
            useCase.execute(Unit)
            Assert.fail("Should have thrown RuntimeException")
        } catch (ex: Exception) {
            //THEN
            Truth.assertThat(ex).isInstanceOf(RuntimeException::class.java)
        }
    }
}