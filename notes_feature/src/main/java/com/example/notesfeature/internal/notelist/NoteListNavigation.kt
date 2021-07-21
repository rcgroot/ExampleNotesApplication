package com.example.notesfeature.internal.notelist

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.notesfeature.internal.notedetail.NoteFragment
import com.example.notesfeature.internal.notelist.service.Note

internal class NoteListNavigation {

    /**
     * ADR # 9. Use Consumable and ConsumingObserver for navigation and other one-time triggers
     */
    val step = MutableLiveData<Consumable<Note>>()

    fun openNoteDetails(note: Note) {
        step.value = Consumable(note)
    }
}

internal class NoteListNavigator(
    private val fragment: NoteListFragment
) {

    fun observe(owner: LifecycleOwner, navigation: NoteListNavigation) {
        // ADR # 9. Use Consumable and ConsumingObserver for navigation and other one-time triggers
        navigation.step.observe(owner, ConsumingObserver { openNote(it) })
    }

    private fun openNote(note: Note) {
        NoteFragment()
            // ADR # 13. Feature modules: Expose only a single Fragment to public and use child Fragments for internal flows
            .show(fragment.childFragmentManager, "NOTE:${note.id}")
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

internal class ConsumingObserver<T>(private val action: (T) -> Unit) : Observer<Consumable<T>> {
    override fun onChanged(t: Consumable<T>?) {
        t?.consume { action.invoke(it) }
    }

}
