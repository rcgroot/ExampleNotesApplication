package com.example.notesfeature.internal.notelist.service

import com.example.backend.BackendCommunication
import com.example.backend.Operation
import com.example.backend.Request
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class NoteService(
    private val backendCommunication: BackendCommunication
) {

    suspend fun getNotes(): List<Note> = withContext(Dispatchers.IO) {
        val request = Request(Operation.GET, "/notes")
        val response = backendCommunication.execute(request)
        Json.decodeFromString<Notes>(response.body).notes
    }
}