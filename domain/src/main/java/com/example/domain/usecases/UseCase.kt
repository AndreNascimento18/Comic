package com.example.domain.usecases

interface UseCase<Request, Response> {
    suspend fun execute(params: Request): Response
}