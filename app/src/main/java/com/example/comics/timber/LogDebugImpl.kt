package com.example.comics.timber

import android.util.Log
import timber.log.Timber

class LogDebugImpl : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        Log.d(tag, message)
    }
}