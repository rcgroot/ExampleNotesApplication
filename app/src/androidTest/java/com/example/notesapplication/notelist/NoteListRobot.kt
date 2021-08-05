package com.example.notesapplication.notelist

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.CoreMatchers.both
import org.hamcrest.CoreMatchers.instanceOf
import com.example.notesapplication.R

inline fun notes(block: NoteListRobot.() -> Unit) = NoteListRobot().apply(block)

class NoteListRobot {

    fun isVisible() {
        onView(both(withId(R.id.list)).and(instanceOf(RecyclerView::class.java))).check(matches(isDisplayed()))
    }

    fun clickOnItem(position: Int) {
        onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position, ViewActions.click()))
    }
}
