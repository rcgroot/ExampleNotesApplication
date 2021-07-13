package com.example.backend_mock

import android.content.Context
import com.example.backend.BackendCommunication
import com.example.backend.Request
import com.example.backend.Response

class MockBackendCommunication(private val notesApplication: Context) : BackendCommunication {

    override suspend fun execute(request: Request): Response {
        TODO("Not yet implemented")
    }
}