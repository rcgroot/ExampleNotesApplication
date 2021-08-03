package com.example.notesfeature.internal.notelist

import androidx.core.widget.ContentLoadingProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesfeature.internal.service.NoteState

@BindingAdapter("notes")
internal fun RecyclerView.setNotes(state: NoteState?) {
    if (adapter == null) {
        adapter = NotesListAdapter()
    }
    (adapter as NotesListAdapter).apply {
        notes = (state as? NoteState.NoteList)?.notes ?: emptyList()
    }
}

@BindingAdapter("listener")
internal fun RecyclerView.setListener(noteSelectionListener: NoteSelectionListener?) {
    if (adapter == null) {
        adapter = NotesListAdapter()
    }
    (adapter as NotesListAdapter).apply {
        listener = noteSelectionListener
    }
}

@BindingAdapter("loading")
internal fun ContentLoadingProgressBar.setLoading(state: NoteState?) =
    if (state == NoteState.Loading) {
        this.show()
    } else {
        this.hide()
    }
