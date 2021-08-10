package com.example.notesfeature.internal.service

import com.example.backend.BackendCommunication
import com.example.backend.Operation
import com.example.backend.Request
import com.example.backend.Response
import kotlinx.coroutines.runBlocking
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
    fun `test getting valid note`(): Unit = runBlocking {
        whenever(backend.execute(Request(Operation.GET, "/notes/1"))).thenReturn(
            Response(200, """{ "id":1, "title":"title", "body":"body" }""")
        )

        val notes = sut.getNote(1)

        assertThat(notes).isNotNull
    }

    @Test
    fun `test getting no note`(): Unit = runBlocking {
        whenever(backend.execute(Request(Operation.GET, "/notes/2"))).thenReturn(
            Response(200, """{ "id":2, "title":"title", "body":"body" }""")
        )
        var note: Note? = null
        try {
            note = sut.getNote(1)
        } catch (e: Exception) {
            assertThat(note).isNull()
        }
    }

}
