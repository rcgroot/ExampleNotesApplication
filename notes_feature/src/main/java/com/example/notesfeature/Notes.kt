package com.example.notesfeature

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.backend.BackendCommunication
import com.example.notesfeature.internal.notedetail.NoteDetailsFragment
import com.example.notesfeature.internal.notedetail.noteDetailsFragment
import com.example.notesfeature.internal.notelist.NoteListFragment

/**
 * ADR # 13. Feature modules: Expose only a single Fragment to public and use child Fragments for internal flows
 */
fun noteListFragment(backendCommunication: BackendCommunication): Fragment =
    NoteListFragment(backendCommunication)

/**
 * ADR # 21. Use AndroidX Fragment factories to inject dependencies into Fragments
 */
fun notesFragmentFactory(backendCommunication: BackendCommunication) = object : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        when (Class.forName(className).kotlin) {
            NoteListFragment::class -> noteListFragment(backendCommunication)
            NoteDetailsFragment::class -> noteDetailsFragment(backendCommunication)
            else -> super.instantiate(classLoader, className)
        }
}
