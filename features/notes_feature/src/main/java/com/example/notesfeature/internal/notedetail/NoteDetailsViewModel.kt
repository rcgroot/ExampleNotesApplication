package com.example.notesfeature.internal.notedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.backend.BackendCommunication
import com.example.notesfeature.internal.service.NoteService

internal class NoteDetailsViewModel(backendCommunication: BackendCommunication, noteId: Int) : ViewModel() {

    /**
     * ADR # 4. MVP: Store Presenter inside ViewModel
     */
    val presenter = NoteDetailsPresenter(
        NoteDetailsContainer(),
        NoteDetailsNavigation(),
        NoteService(backendCommunication),
        noteId,
        viewModelScope
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
