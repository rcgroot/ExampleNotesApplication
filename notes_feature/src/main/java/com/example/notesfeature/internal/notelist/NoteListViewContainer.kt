package com.example.notesfeature.internal.notelist.view
// TODO the package directive does not match the file location, maybe it should be refactored?

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notesfeature.internal.service.NoteState
import com.example.notesfeature.internal.service.Note

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
