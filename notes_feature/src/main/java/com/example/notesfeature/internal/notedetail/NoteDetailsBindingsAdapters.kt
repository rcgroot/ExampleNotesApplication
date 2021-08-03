package com.example.notesfeature.internal.notedetail

import android.widget.TextView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesfeature.internal.service.NoteState

@BindingAdapter("titleOnLoaded")
internal fun TextView.setNoteTitle(state: NoteState?) {
    val notes = (state as? NoteState.SingleNote)?.note
    notes?.let {
        text = it.title
    }
}

@BindingAdapter("contentOnLoaded")
internal fun TextView.setNoteContent(state: NoteState?) {
    val notes = (state as? NoteState.SingleNote)?.note
    notes?.let {
        text = it.body
    }
}
