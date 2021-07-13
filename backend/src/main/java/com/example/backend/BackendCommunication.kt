package com.example.backend

interface BackendCommunication {

    suspend fun execute(request: Request): Response
}