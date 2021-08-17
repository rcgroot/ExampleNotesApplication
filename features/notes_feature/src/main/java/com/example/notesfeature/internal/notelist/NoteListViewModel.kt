package com.example.notesfeature.internal.notelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesfeature.NotesDispatchers
import com.example.notesfeature.internal.service.NoteService
import kotlinx.coroutines.CoroutineScope

internal class NoteListViewModel(private val noteService: NoteService) : ViewModel() {

    companion object {
        var serviceOverride: NoteService? = null
    }

    /**
     * ADR # 4. MVP: Store Presenter inside ViewModel
     */
    val presenter = NoteListPresenter(
        NoteListViewContainer(),
        NoteListNavigation(),
        serviceOverride ?: noteService,
        CoroutineScope(NotesDispatchers.Main)
    )

    init {
        presenter.start()
    }

    override fun onCleared() {
        super.onCleared()
        presenter.clear()
    }

    class Factory(private val noteService: NoteService) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            NoteListViewModel(noteService) as T
    }
}
