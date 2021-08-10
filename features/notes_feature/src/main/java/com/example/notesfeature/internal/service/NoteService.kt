package com.example.notesfeature.internal.service

import com.example.backend.BackendCommunication
import com.example.backend.Operation
import com.example.backend.Request
import com.example.notesfeature.NotesDispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal class NoteService(
    private val backendCommunication: BackendCommunication
) {

    suspend fun getNotes(): List<Note> = withContext(NotesDispatchers.IO) {
        val request = Request(Operation.GET, "/notes")
        val response = backendCommunication.execute(request)
        Json.decodeFromString<Notes>(response.body).notes
    }

    suspend fun getNote(id: Int): Note = withContext(NotesDispatchers.IO) {
        val request = Request(Operation.GET, "/notes/$id")
        val response = backendCommunication.execute(request)
        Json.decodeFromString(response.body)
    }
}
