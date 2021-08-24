package com.example.notesfeature.internal.notelist

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.example.notesfeature.internal.notedetail.NoteDetailsFragment
import com.example.notesfeature.internal.service.Note
import com.example.notesfeature.internal.service.NoteService
import com.example.support.Analytics
import com.example.support.Consumable
import com.example.support.ConsumingObserver

internal class NoteListNavigation {

    /**
     * ADR # 9. Use Consumable and ConsumingObserver for navigation and other one-time triggers
     */
    private val step = MutableLiveData<Consumable<Note>>()

    fun openNoteDetails(note: Note) {
        step.value = Consumable(note)
    }

    fun observe(owner: LifecycleOwner, action: (Note) -> Unit) {
        // ADR # 9. Use Consumable and ConsumingObserver for navigation and other one-time triggers
        step.observe(owner, ConsumingObserver(action))
    }
}

internal class NoteListNavigator(
    private val fragment: NoteListFragment,
    private val noteService: NoteService,
    private val analytics: Analytics
) {

    fun observeNavigation(owner: LifecycleOwner, navigation: NoteListNavigation) {
        navigation.observe(owner, ::openNote)
    }

    private fun openNote(note: Note) {
        NoteDetailsFragment(noteService, analytics)
            .withArguments(note.id) // type safe arguments
// ADR # 13. Feature modules: Expose only a single Fragment to public and use child Fragments for internal flows
            .show(fragment.childFragmentManager, "NOTE:${note.id}")
    }
}
