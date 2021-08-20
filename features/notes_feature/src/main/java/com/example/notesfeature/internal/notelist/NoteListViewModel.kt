package com.example.notesfeature.internal.notelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesfeature.NotesDispatchers
import com.example.notesfeature.internal.service.NoteService
import com.example.support.Analytics
import kotlinx.coroutines.CoroutineScope

internal class NoteListViewModel(noteService: NoteService, analytics: Analytics) : ViewModel() {
    val view: NoteListViewContainer by lazy { NoteListViewContainer() }
    val navigation: NoteListNavigation by lazy { NoteListNavigation() }
    /**
     * ADR # 4. MVP: Store Presenter inside ViewModel
     */
    val presenter = NoteListPresenter(
        view,
        navigation,
        noteService,
        analytics,
        CoroutineScope(NotesDispatchers.Main)
    )

    init {
        presenter.start()
    }

    override fun onCleared() {
        super.onCleared()
        presenter.clear()
    }

    class Factory(private val noteService: NoteService, private val analytics: Analytics) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            NoteListViewModel(noteService, analytics) as T
    }
}
