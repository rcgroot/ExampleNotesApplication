package com.example.notesfeature.internal.notedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesfeature.NotesDispatchers
import com.example.notesfeature.internal.service.NoteService
import com.example.support.Analytics
import kotlinx.coroutines.CoroutineScope

internal class NoteDetailsViewModel(noteService: NoteService, analytics: Analytics, noteId: Int) : ViewModel() {

    /**
     * ADR # 4. MVP: Store Presenter inside ViewModel
     */
    val presenter = NoteDetailsPresenter(
        NoteDetailsViewContainer(),
        NoteDetailsNavigation(),
        noteService,
        analytics,
        noteId,
        CoroutineScope(NotesDispatchers.Main)
    )

    init {
        presenter.start()
    }

    override fun onCleared() {
        super.onCleared()
        presenter.clear()
    }

    class Factory(private val noteService: NoteService , private val analytics: Analytics, private val noteId: Int) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            NoteDetailsViewModel(noteService, analytics, noteId) as T
    }
}
