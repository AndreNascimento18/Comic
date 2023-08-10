package com.example.domain.usecases

import com.example.data.network.Repository
import com.example.domain.dto.ComicsDataDTO
import com.example.domain.mapper.ComicsDataMapper

class GetComicDataUseCase(
    private val mapper: ComicsDataMapper,
    private val repository: Repository
): UseCase<Unit, ComicsDataDTO> {

    override suspend fun execute(params: Unit): ComicsDataDTO =
        mapper.map(repository.getComics())
}