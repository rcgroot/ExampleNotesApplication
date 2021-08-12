package com.example.notesapplication.notedetails

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.notesapplication.MainActivity
import com.example.notesapplication.notelist.notes
import com.example.notesapplication.utils.DataBindingIdlingResourceRule
import com.example.notesapplication.utils.DispatchersRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NoteDetailsTest {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    @get:Rule
    val dataBindingIdlingResourceRule = DataBindingIdlingResourceRule(activityRule)

    @get:Rule
    val dispatcherRule = DispatchersRule()

    @Before
    fun before() {
        activityRule.launchActivity(null)
        notes {
            clickOnItem(0)
        }
    }

    @Test
    fun whenNoteIsDisplayedTitleAndSubtitleAreShown() {
        noteDetails {
            isTitleDisplayed()
            isContentDisplayed()
        }
    }

    @Test
    fun closeButtonClosesFragment() {
        noteDetails {
            clickClose()
        }

        notes {
            isVisible()
        }
    }
}