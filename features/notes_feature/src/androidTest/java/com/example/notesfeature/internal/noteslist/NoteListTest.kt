package com.example.notesfeature.internal.noteslist

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.notesfeature.utils.DataBindingIdlingResourceRule
import com.example.notesfeature.utils.DispatchersRule
import org.junit.Before
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

    @Before
    fun before() {
        activityRule.launchActivity(null)
    }

    @Test
    fun openFirstNote() {
        notes {
            isVisible()
            clickOnItem(0)
        }
    }
}
