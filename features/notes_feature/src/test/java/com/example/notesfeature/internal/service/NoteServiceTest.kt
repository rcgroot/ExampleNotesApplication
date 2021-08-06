package com.example.notesfeature.internal.service

import com.example.backend.BackendCommunication
import com.example.backend.Operation
import com.example.backend.Request
import com.example.backend.Response
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class NoteServiceTest {

    private val backend: BackendCommunication = mock()
    private val sut = NoteService(backend)

    @Test
    fun `test getting empty not list`(): Unit = runBlocking {
        whenever(backend.execute(Request(Operation.GET, "/notes"))).thenReturn(
            Response(200, """{ "notes" : [] }""")
        )

        val notes = sut.getNotes()

        assertThat(notes).isEmpty()
    }

    @Test
    fun `test getting missing note list`(): Unit = runBlocking {
        whenever(backend.execute(Request(Operation.GET, "/notes"))).thenReturn(
            Response(200, """{  }""")
        )

        val notes = sut.getNotes()

        assertThat(notes).isEmpty()
    }

    @Test
    fun `test on empty backend response empty note is generated`(): Unit = runBlocking {
        val noteId = 7
        whenever(backend.execute(Request(Operation.GET, "/notes/$noteId"))).thenReturn(
            Response(200, """{ }""")
        )

        val note = sut.getNote(noteId)

        assertThat(note.id).isEqualTo(-1)
    }


    @Test
    fun `test on unauthorized backend response empty note is generated`(): Unit = runBlocking {
        val noteId = 7
        whenever(backend.execute(Request(Operation.GET, "/notes/$noteId"))).thenReturn(
            Response(403, "")
        )

        val note = sut.getNote(noteId)

        assertThat(note.id).isEqualTo(-1)
    }

    @Test
    fun `test on bad request backend response empty note is generated`(): Unit = runBlocking {
        val noteId = 7
        whenever(backend.execute(Request(Operation.GET, "/notes/$noteId"))).thenReturn(
            Response(500, "")
        )

        val note = sut.getNote(noteId)

        assertThat(note.id).isEqualTo(-1)
    }


    @Test
    fun `test on success backend response that the correct note is returned`(): Unit = runBlocking {
        val testNote = Note(1, "title", "value")
        whenever(backend.execute(Request(Operation.GET, "/notes/${testNote.id}"))).thenReturn(
            Response(200, Json.encodeToString(testNote))
        )

        val note = sut.getNote(testNote.id)

        assertThat(note).isEqualTo(testNote)
    }
}
