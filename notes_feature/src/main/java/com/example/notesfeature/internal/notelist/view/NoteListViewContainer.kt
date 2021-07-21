package com.example.notesfeature.internal.notelist.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notesfeature.internal.notelist.service.Note

/**
 * ADR # 3. MVP: Use LiveData observables container as Presenter's View
 */
internal class NoteListViewContainer {

    private val _notes = MutableLiveData<NoteState>()
    val notes: LiveData<NoteState>
        get() = _notes

    fun showNotes(notes: List<Note>) {
        _notes.postValue(NoteState.NoteList(notes))
    }

    fun showLoading() {
        _notes.postValue(NoteState.Loading)
    }
}

/**
 * ADR # 8. Use sealed classes for modelling UI state
 */
internal sealed class NoteState {
    object Loading : NoteState()
    data class NoteList(val notes: List<Note>) : NoteState()
}
