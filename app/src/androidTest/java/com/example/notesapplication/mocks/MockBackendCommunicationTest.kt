package com.example.notesapplication.mocks

import com.example.backend.BackendCommunication
import com.example.backend.Request
import com.example.backend.Response
import com.example.backend_mock.Data
import com.example.backend_mock.Note
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class MockBackendCommunicationTest : BackendCommunication {

    override fun execute(request: Request): Response = get()

    private var noteList = Data(
        mutableListOf(
            Note(
                1,
                "First note",
                "This this a first note created every in this sample application"
            ),
            Note(2, "Second note", "Another note"),
            Note(3, "Third note", "Another note"),
            Note(4, "Forth note", "Another note"),
            Note(5, "Fifth note", "Another note"),
            Note(6, "Sixth note", "Another note"),
            Note(7, "Seventh note", "Another note"),
            Note(8, "Eight note", "Another note"),
            Note(9, "Ninth note", "Another note"),
            Note(10, "Tenth note", "Another note")
        )
    )

    internal fun get() = Response(200, Json.encodeToString(noteList))

}