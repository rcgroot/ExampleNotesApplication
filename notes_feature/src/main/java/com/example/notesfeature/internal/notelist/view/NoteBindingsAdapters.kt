package com.example.notesfeature.internal.notelist.view

import androidx.core.widget.ContentLoadingProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("notes")
internal fun RecyclerView.setNotes(state: NoteState?) {
    val notesListAdapter: NotesListAdapter
    if (adapter == null) {
        notesListAdapter = NotesListAdapter()
        adapter = notesListAdapter
    } else {
        notesListAdapter = adapter as NotesListAdapter
    }
    notesListAdapter.notes = (state as? NoteState.NoteList)?.notes ?: emptyList()
}

@BindingAdapter("listener")
internal fun RecyclerView.setListener(listener: NoteSelectionListener?) {
    val notesListAdapter: NotesListAdapter
    if (adapter == null) {
        notesListAdapter = NotesListAdapter()
        adapter = notesListAdapter
    } else {
        notesListAdapter = adapter as NotesListAdapter
    }
    notesListAdapter.listener = listener
}

@BindingAdapter("loading")
internal fun ContentLoadingProgressBar.setLoading(state: NoteState?) =
    if (state == NoteState.Loading) {
        this.show()
    } else {
        this.hide()
    }
