package com.example.notesfeature.internal.notelist

import com.example.notesfeature.internal.TrackingHelper
import com.example.notesfeature.internal.service.Note
import com.example.notesfeature.internal.service.NoteService
import com.example.notesfeature.internal.notelist.NoteListViewContainer
import com.example.support.Analytics
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
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
    private val analytics: Analytics = mock()
    private val sut = NoteListPresenter(view, navigation, service, analytics, scope)

    @Test
    fun `after initialization note will be loaded`() = runBlocking {
        whenever(service.getNotes()).thenReturn(notes)

        sut.start()

        verify(view).setLoading(true)
        verify(service).getNotes()
        verify(view).showNotes(notes)
        verify(analytics).trackEvent(TrackingHelper.NOTE_LIST_SHOWN)
    }

    @Test
    fun `when note is selected details should open`() = runBlocking {
        sut.onNoteSelected(note)

        verify(analytics).trackEvent(TrackingHelper.NOTE_LIST_OPEN_DETAILS.plus(note.id))
        verify(navigation).openNoteDetails(note)
    }

    @Test
    fun `when refreshing new notes are loaded`() = runBlockingTest {
        sut.onRefreshNotes()

        verify(analytics).trackEvent(TrackingHelper.NOTE_LIST_SHOWN)
        verify(service).getNotes()
    }
}
