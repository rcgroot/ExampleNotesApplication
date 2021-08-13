package com.example.notesfeature.internal.notedetail

import com.example.notesfeature.internal.service.Note
import com.example.notesfeature.internal.service.NoteService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class NoteDetailsPresenterTest {

    private val view: NoteDetailsViewContainer = mock()
    private val navigation: NoteDetailsNavigation = mock()
    private val service: NoteService = mock()
    private val noteId = 1
    private val scope: CoroutineScope = TestCoroutineScope()
    private val sut = NoteDetailsPresenter(
        view, navigation, service, noteId, scope
    )

    @Test
    fun `test when close the screen will dismiss`() = runBlockingTest {
        val note: Note = mock()
        whenever(service.getNote(noteId)).thenReturn(note)
        sut.start()

        sut.onCloseNoteSelected()

        verify(navigation).closeNoteDetails()
    }

    @Test
    fun `after initialization note will be loaded()`() = runBlockingTest {
        sut.start()

        verify(service).getNote(noteId)
    }
}
