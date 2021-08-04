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
}
