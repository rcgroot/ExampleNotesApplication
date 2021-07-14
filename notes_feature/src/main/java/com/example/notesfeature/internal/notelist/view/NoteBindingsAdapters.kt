package com.example.notesfeature.internal.notelist.view

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesfeature.internal.notelist.service.Note
import com.example.notesfeature.internal.notelist.service.Notes

@BindingAdapter("notes")
fun RecyclerView.setNotes(notes: List<Note>?) {
    val notesListAdapter: NotesListAdapter
    if (adapter == null) {
        notesListAdapter = NotesListAdapter()
        adapter = notesListAdapter
    } else {
        notesListAdapter = adapter as NotesListAdapter
    }
    notesListAdapter.notes = notes ?: emptyList()
}