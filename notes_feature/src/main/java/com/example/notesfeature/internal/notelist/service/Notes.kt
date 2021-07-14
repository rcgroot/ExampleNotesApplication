package com.example.notesfeature.internal.notelist.service

import kotlinx.serialization.Serializable

@Serializable
data class Notes(
    val notes: List<Note>
)

@Serializable
data class Note(
    val id: Int,
    val title: String,
    val body: String
)
