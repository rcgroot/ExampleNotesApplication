package com.example.backend_mock

import android.content.Context
import android.os.SystemClock
import com.example.backend.BackendCommunication
import com.example.backend.Operation
import com.example.backend.Request
import com.example.backend.Response
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.lang.Exception

private const val MOCK_NETWORK_DELAY = 750L

class MockBackendCommunication(private val context: Context) : BackendCommunication {

    override fun execute(request: Request): Response =
        when (request.operation) {
            Operation.GET -> get(request.path).also {
                SystemClock.sleep(MOCK_NETWORK_DELAY)
            }
            Operation.POST -> post(request.path, request.body).also {
                SystemClock.sleep(MOCK_NETWORK_DELAY)
            }
        }
}

@Serializable
data class Data(val notes: MutableList<Note>)

@Serializable
data class Note(
    val id: Int,
    val title: String,
    val body: String
)

val badRequest = Response(500, "Bad Request")
val notFound = Response(404, "Server error")
val accessDenied = Response(403, "{}")
val noteList = Data(
    mutableListOf(
        Note(1, "First note", "This this a first note created every in this sample application"),
        Note(2, "Second note", "Another note"),
    )
)

internal fun get(path: String) = when {
    path == "/" -> Response(200, "{}")
    path == "/notes" -> Response(200, Json.encodeToString(noteList))
    "/notes/\\d+".toPattern().matcher(path).matches() -> getNote(path)
    else -> notFound
}

private fun getNote(path: String): Response {
    val id = path.substring(7).toInt()
    val note = noteList.notes.find { it.id == id }
    return if (note != null) {
        Response(200, Json.encodeToString(note))
    } else {
        notFound
    }
}


internal fun post(path: String, body: String?) = when {
    body == null -> badRequest
    path == "/notes" -> addNote(body)
    else -> accessDenied
}

private fun addNote(body: String): Response =
    try {
        val note = Json.decodeFromString<Note>(body)
        val nextId = noteList.notes.last().id + 1
        noteList.notes.add(Note(nextId, note.title, note.body))
        Response(201, "/notes/$nextId")
    } catch (e: Exception) {
        badRequest
    }