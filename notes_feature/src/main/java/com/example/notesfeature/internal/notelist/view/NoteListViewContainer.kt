package com.example.notesfeature.internal.notelist.view

import androidx.lifecycle.MutableLiveData
import com.example.notesfeature.internal.notelist.service.Note
import com.example.notesfeature.internal.notelist.service.Notes

class NoteListViewContainer {
    val notes = MutableLiveData<List<Note>>()
}