package com.example.notesfeature.internal.notedetail

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.notesfeature.internal.service.Note

internal class NoteDetailsNavigation {

    /**
     * ADR # 9. Use Consumable and ConsumingObserver for navigation and other one-time triggers
     */
    private val step = MutableLiveData<Consumable<Note>>()

    fun closeNoteDetails(note: Note) {
        step.value = Consumable(note)
    }

    fun observe(owner: LifecycleOwner, action: (Note) -> Unit) {
        // ADR # 9. Use Consumable and ConsumingObserver for navigation and other one-time triggers
        step.observe(owner, ConsumingObserver(action))
    }
}

internal class NoteDetailsNavigator(
    private val fragment: NoteDetailsFragment
) {

    fun observeNavigation(owner: LifecycleOwner, navigation: NoteDetailsNavigation) {
        navigation.observe(owner, ::closeNote)
    }

    private fun closeNote(note: Note) {
        fragment.dismiss()
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
        t?.consume {
            action.invoke(it)
        }
    }

}
