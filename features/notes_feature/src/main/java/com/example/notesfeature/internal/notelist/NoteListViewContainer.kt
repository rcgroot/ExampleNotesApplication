package com.example.notesfeature.internal.notelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notesfeature.internal.service.Note

/**
 * ADR # 3. MVP: Use LiveData observables container as Presenter's View
 */
internal class NoteListViewContainer {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>>
        get() = _notes

    fun showNotes(notes: List<Note>) {
        _notes.postValue(notes)
    }

    fun setLoading(isLoading: Boolean) {
        _isLoading.postValue(isLoading)
    }
}
