package com.example.notesfeature.internal.notedetail

import android.widget.TextView
import com.example.notesfeature.internal.service.Note
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class NoteDetailsBindingsAdaptersTest {

    private val sut: TextView = mock()

    @Test
    fun `when given a single note show that title`() {
        val note: Note = mock {
            whenever(it.title).thenReturn("title")
        }
        val state: NoteState = NoteState.SingleNote(note)

        sut.setNoteTitle(state)

        verify(sut).text = note.title
    }

    @Test
    fun `when given no note empty the value shown`() {
        sut.setNoteTitle(null)

        verify(sut).text = ""
    }
}
