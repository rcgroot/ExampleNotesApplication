package com.example.notesfeature.internal.notelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.backend.BackendCommunication
import com.example.notesfeature.NotesDispatchers
import com.example.notesfeature.internal.service.NoteService
import com.example.notesfeature.internal.service.NoteServiceImpl
import kotlinx.coroutines.CoroutineScope

internal class NoteListViewModel(backendCommunication: BackendCommunication) : ViewModel() {

    companion object {
        var serviceOverride: NoteService? = null
    }

    /**
     * ADR # 4. MVP: Store Presenter inside ViewModel
     */
    val presenter = NoteListPresenter(
        NoteListViewContainer(),
        NoteListNavigation(),
        serviceOverride ?: NoteServiceImpl(backendCommunication),
        CoroutineScope(NotesDispatchers.Main)
    )

    init {
        presenter.start()
    }

    override fun onCleared() {
        super.onCleared()
        presenter.clear()
    }

    class Factory(private val backendCommunication: BackendCommunication) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            NoteListViewModel(backendCommunication) as T
    }
}
