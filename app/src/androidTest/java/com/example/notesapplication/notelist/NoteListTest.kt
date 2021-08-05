package com.example.notesapplication.notelist

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.notesapplication.MainActivity
import com.example.notesapplication.R
import com.example.notesapplication.utils.DataBindingIdlingResourceRule
import com.example.notesapplication.utils.DispatchersRule
import org.hamcrest.CoreMatchers.both
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NoteListTest {

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

    @Test
    fun openFirstNote() {
        // Is list showing?
        onView(both(withId(R.id.list)).and(instanceOf(RecyclerView::class.java))).check(matches(isDisplayed()))
        // Click on first element
        onView(withId(R.id.list)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }
}
