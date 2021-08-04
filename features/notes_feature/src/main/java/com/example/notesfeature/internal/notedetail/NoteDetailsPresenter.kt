// ADR # 20. Feature modules: Put private API classes into package named 'internal'
package com.example.notesfeature.internal.notedetail

import com.example.notesfeature.internal.service.NoteService
import com.example.notesfeature.internal.service.NoteState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * ADR # 2. Use Model-View-Presenter architecture
 * ADR # 5. MVP: Inject Presenter's arguments and dependencies via constructor
 * ADR # 6. MVP: Use no Android dependencies in Presenter classes
 * ADR # 14. Feature modules: Limit public API with 'internal' visibility
 */
internal class NoteDetailsPresenter(
    val view: NoteDetailsContainer,
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
        navigation.closeNoteDetails((view.note.value as NoteState.SingleNote).note)
    }

    fun clear() {
        // empty
    }

    private fun loadNote() {
        launch {
            view.showLoading()
            view.showNote(service.getNote(noteId))
        }
    }
}
