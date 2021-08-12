package com.example.notesfeature.internal.noteslist

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.notesfeature.R
import org.hamcrest.CoreMatchers.both
import org.hamcrest.CoreMatchers.instanceOf

inline fun notes(block: NoteListRobot.() -> Unit) = NoteListRobot().apply(block)

class NoteListRobot {

    fun isVisible() {
        onView(both(withId(R.id.list)).and(instanceOf(RecyclerView::class.java))).check(matches(isDisplayed()))
    }

    fun clickOnItem(position: Int) {
        onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position, ViewActions.click()))
    }
}
