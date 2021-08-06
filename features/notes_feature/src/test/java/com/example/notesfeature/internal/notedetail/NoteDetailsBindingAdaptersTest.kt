package com.example.notesfeature.internal.notedetail

import android.widget.TextView
import com.example.notesfeature.internal.service.Note
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyZeroInteractions

class NoteDetailsBindingAdaptersTest {
    private val sut: TextView = mock()
    private val note = Note(1, "title", "body")
    private val noteState = NoteState.SingleNote(note)

    @Test
    fun `setNoteTitle sets title for SingleNote`() {
        sut.setNoteTitle(noteState)

        verify(sut).text = note.title
    }

    @Test
    fun `setNoteContent sets body for SingleNote`() {
        sut.setNoteContent(noteState)

        verify(sut).text = note.body
    }

    @Test
    fun `setNoteTitle doesn't set title for null input`() {
        sut.setNoteTitle(null)

        verifyZeroInteractions(sut)
    }

    @Test
    fun `setNoteContent doesn't set body for null input`() {
        sut.setNoteContent(null)

        verifyZeroInteractions(sut)
    }


    @Test
    fun `setNoteTitle doesn't set title for placeholder input`() {
        sut.setNoteTitle(NoteState.Placeholder)

        verifyZeroInteractions(sut)
    }

    @Test
    fun `setNoteContent doesn't set body for placeholder input`() {
        sut.setNoteContent(NoteState.Placeholder)

        verifyZeroInteractions(sut)
    }
}