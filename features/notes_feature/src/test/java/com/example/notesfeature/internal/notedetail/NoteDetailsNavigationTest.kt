package com.example.notesfeature.internal.notedetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.notesfeature.internal.service.Note
import com.example.support.Consumable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class NoteDetailsNavigationTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val sut = NoteDetailsNavigation()
    private val note = Note(0, "title", "body")
    private val stepObserver: Observer<Consumable<Note>> = mock()

    @Before
    fun setup() {
        sut.step.observeForever(stepObserver)
    }

    @Test
    fun `closeNote triggers navigation observable`() {
        sut.closeNoteDetails(note)
        val captor = argumentCaptor<Consumable<Note>>()

        verify(stepObserver).onChanged(captor.capture())
        var noteId: Int = -100
        captor.firstValue.consume { noteId = it.id }
        assertEquals(noteId, note.id)
    }
}