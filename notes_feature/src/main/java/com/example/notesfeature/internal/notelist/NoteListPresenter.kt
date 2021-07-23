// ADR # 20. Feature modules: Put private API classes into package named 'internal'
package com.example.notesfeature.internal.notelist

import com.example.notesfeature.internal.service.Note
import com.example.notesfeature.internal.service.NoteService
import com.example.notesfeature.internal.notelist.view.NoteListViewContainer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * ADR # 2. Use Model-View-Presenter architecture
 * ADR # 5. MVP: Inject Presenter's arguments and dependencies via constructor
 * ADR # 6. MVP: Use no Android dependencies in Presenter classes
 * ADR # 14. Feature modules: Limit public API with 'internal' visibility
 */
internal class NoteListPresenter(
    val view: NoteListViewContainer,
    val navigation: NoteListNavigation,
    private val service: NoteService,
    scope: CoroutineScope
) : CoroutineScope by scope {

    fun start() {
        loadNotes()
    }

    /**
     * ADR # 7. MVP: Prefix Presenter methods handling user input with 'on'
     */
    fun onNoteSelected(note: Note) {
        navigation.openNoteDetails(note)
    }

    fun clear() {
        // empty
    }

    private fun loadNotes() {
        launch {
            view.showLoading()
            view.showNotes(service.getNotes())
        }
    }
}
