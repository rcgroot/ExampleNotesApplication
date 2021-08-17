package com.example.notesfeature.internal.notedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.backend.BackendCommunication
import com.example.notesfeature.NotesDispatchers
import com.example.notesfeature.internal.service.NoteService
import com.example.notesfeature.internal.service.NoteServiceImpl
import kotlinx.coroutines.CoroutineScope

internal class NoteDetailsViewModel(backendCommunication: BackendCommunication, noteId: Int) : ViewModel() {

    /**
     * ADR # 4. MVP: Store Presenter inside ViewModel
     */
    val presenter = NoteDetailsPresenter(
        NoteDetailsViewContainer(),
        NoteDetailsNavigation(),
        NoteServiceImpl(backendCommunication),
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

    class Factory(private val backendCommunication: BackendCommunication,private val noteId: Int) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            NoteDetailsViewModel(backendCommunication, noteId) as T
    }
}
