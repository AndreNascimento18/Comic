package com.example.domain.mapper

interface Mapper<Request, Response> {
    fun map(params: Request): Response
}