package com.example.notesapplication.notedetails

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.notesapplication.MainActivity
import com.example.notesapplication.R
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
    }

    /**
     * Maybe we can group together most of the tests (for example the text checks) so we don't lose time
     */
    @Test
    fun visibleAndFunctionalCloseButton() {
        noteDetails {
            closeIconIsEnabled()
            closeIconIsClickable()
            closeIconIsVisible()
            clickCloseButton()
            closeIconDoesNotExist()
        }
    }

    @Test
    fun visibleAndCorrectTitleText() {
        noteDetails {
            mediumSleep()
            titleTextIsVisible()
            titleTextEquals("First note") // TODO this should not be hardcoded, we need to get it from the clicked item
        }
    }

    @Test
    fun visibleAndCorrectContentText() {
        noteDetails {
            mediumSleep()
            contentTextIsVisible()
            contentTextEquals("This this a first note created every in this sample application") // TODO this should not be hardcoded, we need to get it from the clicked item

        }
    }

    @Test
    fun visibleProgressBarText() {
        noteDetails {
            loadingIconIsVisible()
            mediumSleep()
            loadingIconIsNotVisible()
        }
    }

    @Test
    fun viewDismissedOnBackClick() {
        noteDetails {
            closeIconIsVisible()
            pressDeviceBackButton()
            closeIconDoesNotExist()
        }
    }

    @Test
    fun titleIsCorrect() {
        noteDetails {
            textIsVisible(R.string.notes__note_details_title) // TODO put in resources and maybe use id in xml
        }
    }

    @Test
    fun contentIsCorrect() {
        noteDetails {
            textIsVisible(R.string.notes__note_details_body) // TODO put in resources and maybe use id in xml
        }
    }

}
