// ADR # 20. Feature modules: Put private API classes into package named 'internal'
package com.example.notesfeature.internal.notedetail

import com.example.notesfeature.internal.notedetail.NoteState.SingleNote
import com.example.notesfeature.internal.service.NoteService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ADR # 2. Use Model-View-Presenter architecture
 * ADR # 5. MVP: Inject Presenter's arguments and dependencies via constructor
 * ADR # 6. MVP: Use no Android dependencies in Presenter classes
 * ADR # 14. Feature modules: Limit public API with 'internal' visibility
 */
internal class NoteDetailsPresenter(
    val view: NoteDetailsViewContainer,
    val navigation: NoteDetailsNavigation,
    private val service: NoteService,
    private val noteId: Int,
    scope: CoroutineScope
) : CoroutineScope by scope {

    fun start() {
        loadNote()
    }

    /**
     * ADR # 7. MVP: Prefix Presenter methods handling user input with 'on'
     */
    fun onCloseNoteSelected() {
        navigation.closeNoteDetails((view.note.value as SingleNote).note)
    }

    fun clear() {
        // empty
    }

    private fun loadNote() {
        launch(context = Dispatchers.IO) {
            val deferredNote = async { service.getNote(noteId) }
            val deferredNoteList = async { service.getNotes() }

            val noteList = deferredNoteList.await()
            val note = deferredNote.await()

            withContext(context = Dispatchers.Default) {
                val length = noteList.size
                val position = noteList.indexOf(note) + 1
                view.showNote(SingleNote(note, position, length))

                view.setLoading(false)
            }
        }
    }
}
