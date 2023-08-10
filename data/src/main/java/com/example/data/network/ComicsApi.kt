package com.example.data.network

import com.example.data.schema.SchemaComicData
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicsApi {

    @GET("comics")
    suspend fun getComics(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): SchemaComicData
}