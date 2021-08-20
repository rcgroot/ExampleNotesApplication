package com.example.notesfeature.internal.notedetail


import com.example.notesfeature.internal.service.Note
import com.example.notesfeature.internal.service.NoteService
import com.example.support.Analytics
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class NoteDetailsPresenterTest {
    private val note = Note(1, "Title", "Body")
    private val scope = TestCoroutineScope()

    private val view: NoteDetailsViewContainer = mock()
    private val navigation: NoteDetailsNavigation = mock()
    private val service: NoteService = mock()
    private val analytics: Analytics = mock()
    private val sut = NoteDetailsPresenter(view, navigation, service, analytics, note.id, scope)

    @Test
    fun `when screen is shown analytics event is triggered`() {
        sut.start()

        Mockito.verify(analytics).trackEvent("NoteDetails.Shown.${note.id}")
    }

    @Test
    fun `when note is clicked analytics event is triggered`() {
        sut.cachedNote = note

        sut.onCloseNoteSelected()

        Mockito.verify(analytics).trackEvent("NoteDetails.back")
    }
}