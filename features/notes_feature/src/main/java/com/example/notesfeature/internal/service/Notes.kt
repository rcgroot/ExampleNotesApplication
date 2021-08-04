package com.example.notesfeature.internal.service

import kotlinx.serialization.Serializable

@Serializable
internal data class Notes(
    val notes: List<Note> = emptyList()
)

@Serializable
internal data class Note(
    val id: Int,
    val title: String,
    val body: String
)
