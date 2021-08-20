// ADR # 20. Feature modules: Put private API classes into package named 'internal'
package com.example.notesfeature.internal.notelist

import com.example.notesfeature.internal.service.Note
import com.example.notesfeature.internal.service.NoteService
import com.example.support.Analytics
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * ADR # 2. Use Model-View-Presenter architecture
 * ADR # 5. MVP: Inject Presenter's arguments and dependencies via constructor
 * ADR # 6. MVP: Use no Android dependencies in Presenter classes
 * ADR # 14. Feature modules: Limit public API with 'internal' visibility
 */
internal class NoteListPresenter(
    private val view: NoteListViewContainer,
    private val navigation: NoteListNavigation,
    private val service: NoteService,
    private val analytics: Analytics,
    scope: CoroutineScope
) : CoroutineScope by scope {

    fun start() {
        analytics.trackEvent("NoteList.shown")
        loadNotes()
    }

    /**
     * ADR # 7. MVP: Prefix Presenter methods handling user input with 'on'
     */
    fun onNoteSelected(note: Note) {
        analytics.trackEvent("NotesList.openDetails.${note.id}")
        navigation.openNoteDetails(note)
    }

    fun clear() {
        // empty
    }

    private fun loadNotes() {
        launch {
            view.setLoading(true)
            view.showNotes(service.getNotes())
            view.setLoading(false)
        }
    }

    fun onRefreshNotes() {
        loadNotes()
    }
}
