package com.example.notesfeature.internal.notedetail

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.notesfeature.R

@BindingAdapter("positionOnLoaded")
internal fun TextView.setNotePosition(state: NoteState?) {
    val noteState = state as? NoteState.SingleNote ?: return
    text = context.getString(R.string.notes__note_details_position, noteState.position, noteState.totalCount)
}

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
