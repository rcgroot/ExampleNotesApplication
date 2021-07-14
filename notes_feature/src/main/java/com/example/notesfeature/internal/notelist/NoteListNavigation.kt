package com.example.notesfeature.internal.notelist

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.example.notesfeature.internal.notedetail.NoteFragment
import com.example.notesfeature.internal.notelist.service.Note

internal class NoteListNavigation {

    val step = MutableLiveData<Consumable<Note>>()

    fun openNoteDetails(note: Note) {
        step.value = Consumable(note)
    }
}

internal class NoteListNavigator(
    private val fragmentManager: FragmentManager
) {

    fun observe(owner: LifecycleOwner, navigation: NoteListNavigation) {
        navigation.step.observe(owner, { consumable ->
            consumable?.consume { note ->
                openNote(note)
            }
        })
    }

    private fun openNote(note: Note) {
        NoteFragment()
            .show(fragmentManager, "NOTE:${note.id}")
    }
}

internal class Consumable<T>(
    private val content: T
) {
    private var consumed = false

    fun consume(block: (T) -> Unit) {
        if (!consumed) {
            consumed = true
            block.invoke(content)
        }
    }
}