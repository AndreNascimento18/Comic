package com.example.comics.di

import com.example.comics.ui.comicList.ComicListViewModel
import com.example.comics.utils.DispatcherProvider
import com.example.data.di.dataModule
import com.example.domain.di.domainModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val commonsModule = module {
    factory { DispatcherProvider() }
}

val viewModelModule = module {
    viewModel { ComicListViewModel(get(), get()) }
}

val appModules = listOf(dataModule, domainModule, commonsModule, viewModelModule)