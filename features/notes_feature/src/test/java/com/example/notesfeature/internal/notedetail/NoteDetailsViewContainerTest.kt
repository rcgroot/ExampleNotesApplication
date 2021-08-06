package com.example.notesfeature.internal.notedetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.notesfeature.internal.service.Note
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class NoteDetailsViewContainerTest {
    private val sut = NoteDetailsViewContainer()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val loadingObserver: Observer<Boolean> = mock()
    private val noteObserver: Observer<NoteState> = mock()

    @Before
    fun setup() {
        sut.isLoading.observeForever(loadingObserver)
        sut.note.observeForever(noteObserver)
    }

    @Test
    fun `show loading true triggers loading`() {
        sut.setLoading(true)

        verify(loadingObserver).onChanged(true)
    }

    @Test
    fun `show loading false triggers loading`() {
        sut.setLoading(false)

        verify(loadingObserver).onChanged(false)
    }

    @Test
    fun `show note triggers NoteState`() {
        val noteState = NoteState.SingleNote(Note(0, "title", "value"))
        sut.showNote(noteState)

        verify(noteObserver).onChanged(noteState)
    }
}