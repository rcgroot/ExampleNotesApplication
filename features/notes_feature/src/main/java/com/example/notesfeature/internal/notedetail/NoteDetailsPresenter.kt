// ADR # 20. Feature modules: Put private API classes into package named 'internal'
package com.example.notesfeature.internal.notedetail

import com.example.notesfeature.internal.notedetail.NoteState.SingleNote
import com.example.notesfeature.internal.service.Note
import com.example.notesfeature.internal.service.NoteService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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

    private var currentNote: Note? = null

    fun start() {
        loadNote()
    }

    /**
     * ADR # 7. MVP: Prefix Presenter methods handling user input with 'on'
     */
    fun onCloseNoteSelected() {
        navigation.closeNoteDetails(currentNote ?: Note(-1, "", ""))
    }

    fun clear() {
        // empty
    }

    private fun loadNote() {
        launch {
            view.setLoading(true)
            val note = service.getNote(noteId).also { currentNote = it }
            view.showNote(SingleNote(note))
            view.setLoading(false)
        }
    }
}
