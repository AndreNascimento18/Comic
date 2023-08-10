package com.example.data.network

import com.example.data.BuildConfig

class RepositoryImpl(private val api: ComicsApi) : Repository {

    override suspend fun getComics() = api.getComics(
        ts = BuildConfig.TS,
        apikey = BuildConfig.API_KEY,
        hash = BuildConfig.HASH
    )
}