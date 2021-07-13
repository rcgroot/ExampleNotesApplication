package com.example.notesapplication.dependecies

import android.content.Context
import com.example.backend.BackendCommunication
import com.example.notesapplication.NotesApplication

class ApplicationComponent(
    val backendCommunication: BackendCommunication
)

val Context.applicationComponent
    get() = (applicationContext as NotesApplication).applicationComponent