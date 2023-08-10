package com.example.data.schema

data class SchemaComicData(val data: Data?) {

    data class Data(
        val results: List<Result?>?
    )

    data class Result(
        val title: String?,
        val description: String?,
        val thumbnail: Thumbnail?
    )

    data class Thumbnail(
        val path: String?,
        val extension: String?,
    )
}
