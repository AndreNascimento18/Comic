package com.example.comics.ui.comicList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comics.R
import com.example.comics.ui.comicList.state.ComicState
import com.example.comics.utils.DispatcherProvider
import com.example.comics.utils.StringValue
import com.example.domain.dto.ComicsDataDTO
import com.example.domain.usecases.GetComicDataUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ComicListViewModel(
    private val getComicDataUseCase: GetComicDataUseCase,
    private val dispatcher: DispatcherProvider
) : ViewModel() {

    private val _state = MutableLiveData<ComicState>()
    val state: LiveData<ComicState> = _state

    private val _comicList = MutableLiveData<List<ComicsDataDTO.Result>>()
    val comicList: LiveData<List<ComicsDataDTO.Result>> = _comicList

    fun initialize() {
        if (!comicList.value.isNullOrEmpty()) {
            showComicList(comicList.value!!)
            return
        }

        viewModelScope.launch(dispatcher.main) {
            _state.value = ComicState.ShowLoading
            kotlin.runCatching {
                withContext(dispatcher.io) {
                    getComicDataUseCase.execute(Unit)
                }
            }.onSuccess { response ->
                showComicList(response.data.results)
            }.onFailure {
                _state.value = ComicState.ShowError(
                    title = StringValue.StringResource(R.string.error_generic_title),
                    description = StringValue.StringResource(R.string.error_generic_description)
                )
            }
        }
    }

    private fun showComicList(items: List<ComicsDataDTO.Result>) {
        _comicList.value = items
        _state.value = ComicState.ShowComicList(items)
    }
}