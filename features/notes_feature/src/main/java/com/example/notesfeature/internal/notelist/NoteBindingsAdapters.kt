package com.example.notesfeature.internal.notelist

import androidx.core.widget.ContentLoadingProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesfeature.internal.service.Note

@BindingAdapter("notes")
internal fun RecyclerView.setNotes(noteList: List<Note>?) {
    if (adapter == null) {
        adapter = NotesListAdapter()
    }
    (adapter as NotesListAdapter).apply {
        notes = noteList ?: emptyList()
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
internal fun ContentLoadingProgressBar.setLoading(isLoading: Boolean?) =
    if (isLoading == true) {
        this.show()
    } else {
        this.hide()
    }
