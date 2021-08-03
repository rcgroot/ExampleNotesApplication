package com.example.notesfeature.internal.service

/**
 * ADR # 8. Use sealed classes for modelling UI state
 */
internal sealed class NoteState {
    object Loading : NoteState()
    data class NoteList(val notes: List<Note>) : NoteState()
    data class SingleNote(val note: Note) : NoteState()
}