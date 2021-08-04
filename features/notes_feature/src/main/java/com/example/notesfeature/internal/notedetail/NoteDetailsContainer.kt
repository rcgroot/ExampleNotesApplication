package com.example.notesfeature.internal.notedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notesfeature.internal.service.NoteState
import com.example.notesfeature.internal.service.Note

/**
 * ADR # 3. MVP: Use LiveData observables container as Presenter's View
 */
internal class NoteDetailsContainer {

    private val _note = MutableLiveData<NoteState>()
    val note: LiveData<NoteState>
        get() = _note

    fun showNote(note: Note) {
        _note.postValue(NoteState.SingleNote(note))
    }

    fun showLoading() {
        _note.postValue(NoteState.Loading)
    }
}
