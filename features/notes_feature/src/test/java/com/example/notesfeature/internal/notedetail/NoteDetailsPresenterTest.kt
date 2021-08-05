package com.example.notesfeature.internal.notedetail


import com.example.notesfeature.internal.service.Note
import com.example.notesfeature.internal.service.NoteService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class NoteDetailsPresenterTest {

    private val note = Note(1, "title", "body")
    private val errorNote = Note(-1, "", "")
    private val scope: CoroutineScope = TestCoroutineScope()

    private val view: NoteDetailsViewContainer = mock()
    private val navigation: NoteDetailsNavigation = mock()
    private val service: NoteService = mock()
    private val sut = NoteDetailsPresenter(view, navigation, service, note.id, scope)

    @Test
    fun `after initialization note will be loaded`() = runBlocking {
        whenever(service.getNote(note.id)).thenReturn(note)
        val argumentCaptor = argumentCaptor<Boolean>()

        sut.start()

        Mockito.verify(view, times(2)).setLoading(argumentCaptor.capture())
        assertEquals(true, argumentCaptor.firstValue)
        assertEquals(false, argumentCaptor.secondValue)
        Mockito.verify(service).getNote(note.id)
        Mockito.verify(view).showNote(NoteState.SingleNote(note))
    }

    @Test
    fun `when screen is closed navigation should be triggered`() = runBlocking {
        whenever(service.getNote(note.id)).thenReturn(note)
        sut.start()

        sut.onCloseNoteSelected()

        Mockito.verify(navigation).closeNoteDetails(note)
    }

    @Test
    fun `when screen is closed without a valid note navigation should be triggered`() = runBlocking {
        sut.onCloseNoteSelected()

        Mockito.verify(navigation).closeNoteDetails(errorNote)
    }

}