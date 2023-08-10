package com.example.comics

import com.example.comics.utils.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher

class DispatcherProviderTest(private val dispatcher: CoroutineDispatcher) : DispatcherProvider() {

    override val io: CoroutineDispatcher
        get() = dispatcher

    override val main: CoroutineDispatcher
        get() = dispatcher
}