package com.example.domain.mapper

import com.example.data.schema.SchemaComicData
import com.example.domain.dto.ComicsDataDTO

class ComicsDataMapper: Mapper<SchemaComicData, ComicsDataDTO> {

    override fun map(params: SchemaComicData): ComicsDataDTO {
        val results = params.data?.results?.mapNotNull { it } ?: emptyList()
        val resultsDTO = results.map {
            ComicsDataDTO.Result(
                title = it.title.orEmpty(),
                description = it.description.orEmpty(),
                thumbnail = ComicsDataDTO.Thumbnail(
                    path = it.thumbnail?.path.orEmpty(),
                    extension = it.thumbnail?.extension.orEmpty(),
                )
            )
        }
        return ComicsDataDTO(data = ComicsDataDTO.Data(resultsDTO))
    }
}