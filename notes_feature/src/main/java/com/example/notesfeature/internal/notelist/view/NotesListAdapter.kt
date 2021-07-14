package com.example.notesfeature.internal.notelist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notesfeature.R
import com.example.notesfeature.databinding.FragmentItemBinding
import com.example.notesfeature.internal.notelist.service.Note

class NotesListAdapter : RecyclerView.Adapter<NotesListAdapter.NotesViewHolder>() {

    var notes: List<Note> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = DataBindingUtil.inflate<FragmentItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.fragment_item,
            parent,
            false
        )

        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.binding.note = notes[position]
    }

    override fun getItemCount(): Int = notes.size

    class NotesViewHolder(val binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root)
}