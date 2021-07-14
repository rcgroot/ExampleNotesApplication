package com.example.notesfeature.internal.notelist.view

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesfeature.internal.notelist.service.Note

@BindingAdapter("notes")
internal fun RecyclerView.setNotes(notes: List<Note>?) {
    val notesListAdapter: NotesListAdapter
    if (adapter == null) {
        notesListAdapter = NotesListAdapter()
        adapter = notesListAdapter
    } else {
        notesListAdapter = adapter as NotesListAdapter
    }
    notesListAdapter.notes = notes ?: emptyList()
}

@BindingAdapter("listener")
internal fun RecyclerView.setNotes(listener: NoteSelectionListener?) {
    val notesListAdapter: NotesListAdapter
    if (adapter == null) {
        notesListAdapter = NotesListAdapter()
        adapter = notesListAdapter
    } else {
        notesListAdapter = adapter as NotesListAdapter
    }
    notesListAdapter.listener = listener
}