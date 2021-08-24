// ADR # 20. Feature modules: Put private API classes into package named 'internal'
package com.example.notesfeature.internal.notedetail

import com.example.notesfeature.internal.TrackingHelper
import com.example.notesfeature.internal.notedetail.NoteState.SingleNote
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
internal class NoteDetailsPresenter(
    val view: NoteDetailsViewContainer,
    val navigation: NoteDetailsNavigation,
    private val service: NoteService,
    private val analytics: Analytics,
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
        analytics.trackEvent(TrackingHelper.NOTE_DETAILS_BACK)
        navigation.closeNoteDetails((view.note.value as SingleNote).note)
    }

    fun clear() {
        // empty
    }

    private fun loadNote() {
        launch {
            view.setLoading(true)
            view.showNote(SingleNote(service.getNote(noteId)))
            analytics.trackEvent(TrackingHelper.NOTE_DETAILS_SHOWN.plus(noteId))
            view.setLoading(false)
        }
    }
}
