package com.example.data.network

import com.example.data.schema.SchemaComicData

interface Repository {
    suspend fun getComics(): SchemaComicData
}