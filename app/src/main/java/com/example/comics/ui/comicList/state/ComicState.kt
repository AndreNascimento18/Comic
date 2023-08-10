package com.example.comics.ui.comicList.state

import com.example.comics.utils.StringValue
import com.example.domain.dto.ComicsDataDTO

sealed class ComicState {

    object ShowLoading : ComicState()

    data class ShowError(
        val title: StringValue, val description: StringValue
    ): ComicState()

    data class ShowComicList(val items: List<ComicsDataDTO.Result>): ComicState()
}
