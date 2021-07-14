package com.example.backend

interface BackendCommunication {

    fun execute(request: Request): Response
}