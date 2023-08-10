package com.example.domain.di

import com.example.domain.mapper.ComicsDataMapper
import com.example.domain.usecases.GetComicDataUseCase
import org.koin.dsl.module

val domainModule = module {

    factory {
        ComicsDataMapper()
    }

    factory {
        GetComicDataUseCase(get(), get())
    }
}