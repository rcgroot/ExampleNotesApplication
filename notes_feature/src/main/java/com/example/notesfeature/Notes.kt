package com.example.notesfeature

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.backend.BackendCommunication
import com.example.notesfeature.internal.notelist.NoteListFragment

fun noteListFragment(backendCommunication: BackendCommunication): Fragment =
    NoteListFragment(backendCommunication)

fun notesFragmentFactory(backendCommunication: BackendCommunication) = object : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        when (Class.forName(className).kotlin) {
            NoteListFragment::class -> noteListFragment(backendCommunication)
            else -> super.instantiate(classLoader, className)
        }
}