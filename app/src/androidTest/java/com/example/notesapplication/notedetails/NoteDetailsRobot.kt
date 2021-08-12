package com.example.notesapplication.notedetails

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.notesapplication.R

inline fun noteDetails(block: NoteDetailsRobot.() -> Unit) = NoteDetailsRobot().apply(block)

class NoteDetailsRobot {

    fun isTitleDisplayed() {
        onView(withId(R.id.item_title_text)).check(matches(isDisplayed()))
    }

    fun isContentDisplayed() {
        onView(withId(R.id.item_content)).check(matches(isDisplayed()))
    }

    fun clickClose() {
        onView(withId(R.id.close_icon)).perform(click())
    }
}