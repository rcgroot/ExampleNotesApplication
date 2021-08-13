package com.example.notesfeature.internal.notedetail

import androidx.annotation.VisibleForTesting
import androidx.fragment.app.commit
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.example.support.Consumable
import com.example.support.ConsumingObserver

internal class NoteDetailsNavigation {

    /**
     * ADR # 9. Use Consumable and ConsumingObserver for navigation and other one-time triggers
     */
    @VisibleForTesting
    internal val step = MutableLiveData<Consumable<Unit>>()

    fun closeNoteDetails() {
        step.value = Consumable(Unit)
    }

    fun observe(owner: LifecycleOwner, action: (Unit) -> Unit) {
        // ADR # 9. Use Consumable and ConsumingObserver for navigation and other one-time triggers
        step.observe(owner, ConsumingObserver(action))
    }
}

internal class NoteDetailsNavigator(
    private val fragment: NoteDetailsFragment
) {

    fun observeNavigation(owner: LifecycleOwner, navigation: NoteDetailsNavigation) {
        navigation.observe(owner) { closeNote() }
    }

    private fun closeNote() {
        fragment.parentFragmentManager.commit {
            remove(fragment)
        }
    }
}

