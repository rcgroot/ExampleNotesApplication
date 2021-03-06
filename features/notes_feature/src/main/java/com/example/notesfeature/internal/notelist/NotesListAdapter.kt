package com.example.notesfeature.internal.notelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notesfeature.R
import com.example.notesfeature.databinding.NotesListItemBinding
import com.example.notesfeature.internal.service.Note

internal class NotesListAdapter : RecyclerView.Adapter<NotesListAdapter.NotesViewHolder>() {

    var listener: NoteSelectionListener? = null
    var notes: List<Note> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = DataBindingUtil.inflate<NotesListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.notes__list_item,
            parent,
            false
        )

        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = notes[position]
        holder.binding.note = note
        holder.binding.onClickListener = View.OnClickListener {
            listener?.onNoteSelected(note)
        }
    }

    override fun getItemCount(): Int = notes.size

    class NotesViewHolder(val binding: NotesListItemBinding) : RecyclerView.ViewHolder(binding.root)
}

internal interface NoteSelectionListener {
    fun onNoteSelected(note: Note)
}
