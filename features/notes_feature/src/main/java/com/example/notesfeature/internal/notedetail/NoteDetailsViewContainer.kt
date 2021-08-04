package com.example.notesfeature.internal.notedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notesfeature.internal.service.Note

/**
 * ADR # 3. MVP: Use LiveData observables container as Presenter's View
 */
internal class NoteDetailsViewContainer {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _note = MutableLiveData<NoteState>().apply { postValue(NoteState.Placeholder) }
    val note: LiveData<NoteState>
        get() = _note

    fun showNote(note: NoteState) {
        _note.postValue(note)
    }

    fun setLoading(isLoading: Boolean) {
        _isLoading.postValue(isLoading)
    }
}

/**
 * ADR # 8. Use sealed classes for modelling UI state
 */
internal sealed class NoteState {
    object Placeholder : NoteState()
    data class SingleNote(val note: Note) : NoteState()
}
