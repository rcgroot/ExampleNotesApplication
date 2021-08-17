package com.example.notesfeature.internal.noteslist

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.notesfeature.internal.notelist.NoteListViewModel
import com.example.notesfeature.internal.service.Note
import com.example.notesfeature.internal.service.NoteService
import com.example.notesfeature.utils.DataBindingIdlingResourceRule
import com.example.notesfeature.utils.DispatchersRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NoteListTest {

    @get:Rule
    val activityRule = ActivityTestRule(NotesListTestActivity::class.java, false, false)

    @get:Rule
    val dataBindingIdlingResourceRule = DataBindingIdlingResourceRule(activityRule)

    @get:Rule
    val dispatcherRule = DispatchersRule()

    @Test
    fun openFirstNote() {
        activityRule.launchActivity(null)
        notes {
            isVisible()
            clickOnItem(0)
        }
    }

    @Test
    fun notesListIsEmpty() {
        NoteListViewModel.setTestNotes(emptyList())

        activityRule.launchActivity(null)

        // Test when we get an empty result
        notes {
            containsThisManyItems(0)
        }
    }

    @Test
    fun notesListContains5Items() {
        val list = listOf(
            Note(1, "title 1", "body 1"),
            Note(2, "title 2", "body 2"),
            Note(3, "title 3", "body 3"),
            Note(4, "title 4", "body 4"),
            Note(5, "title 5", "body 5")
        )
        NoteListViewModel.setTestNotes(list)

        activityRule.launchActivity(null)

        notes {
            containsThisManyItems(5)
        }
    }
}

internal fun NoteListViewModel.Companion.setTestNotes(listOfNotes: List<Note>) {
    serviceOverride = NoteServiceStub().apply {
        notes = listOfNotes
    }
}

internal class NoteServiceStub : NoteService {
    var note: Note? = null
    var notes: List<Note>? = null

    override suspend fun getNotes(): List<Note> = notes ?: emptyList()

    override suspend fun getNote(id: Int): Note = note ?: Note(0, "", "")
}