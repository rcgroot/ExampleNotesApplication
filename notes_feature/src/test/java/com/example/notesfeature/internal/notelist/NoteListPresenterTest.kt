package com.example.notesfeature.internal.notelist

import com.example.notesfeature.internal.service.Note
import com.example.notesfeature.internal.service.NoteService
import com.example.notesfeature.internal.notelist.view.NoteListViewContainer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class NoteListPresenterTest {

    private val note = Note(1, "Title", "Body")
    private val notes: List<Note> = listOf(note)
    private val scope: CoroutineScope = TestCoroutineScope()

    /**
     * ADR # 17. Testing: use Mockito MockMaker for mocking in unit tests
     */
    private val view: NoteListViewContainer = mock()
    private val navigation: NoteListNavigation = mock()
    private val service: NoteService = mock()
    private val sut = NoteListPresenter(view, navigation, service, scope)

    @Test
    fun `after initialization note will be loaded`() = runBlocking {
        whenever(service.getNotes()).thenReturn(notes)

        sut.start()

        verify(view).showLoading()
        verify(service).getNotes()
        verify(view).showNotes(notes)
    }

    @Test
    fun `when note is selected details should open`() = runBlocking {
        sut.onNoteSelected(note)

        verify(navigation).openNoteDetails(note)
    }
}
