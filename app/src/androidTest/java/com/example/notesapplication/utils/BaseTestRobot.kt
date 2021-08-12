package com.example.notesapplication.utils

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import org.hamcrest.CoreMatchers

abstract class BaseTestRobot {

    fun clickButton(resId: Int) {
        clickOn(resId)
    }

    fun buttonIsEnabled(resId: Int): ViewInteraction = Espresso.onView((ViewMatchers.withId(resId)))
        .check(ViewAssertions.matches(ViewMatchers.isEnabled()))

    fun buttonIsClickable(resId: Int): ViewInteraction =
        Espresso.onView((ViewMatchers.withId(resId)))
            .check(ViewAssertions.matches(ViewMatchers.isClickable()))

    private fun textView(resId: Int): ViewInteraction = Espresso.onView(ViewMatchers.withId(resId))

    fun textIsVisible(text: Int) = Espresso.onView(ViewMatchers.withText(text))
        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    fun textIsVisible(text: String): ViewInteraction = Espresso.onView(ViewMatchers.withText(text))
        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    fun viewIsVisible(resId: Int): ViewInteraction =
        Espresso.onView(ViewMatchers.withId(resId))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    fun viewIsNotVisible(resId: Int): ViewInteraction =
        Espresso.onView(ViewMatchers.withId(resId))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))

    fun viewDoesNotExist(resId: Int): ViewInteraction = Espresso.onView(ViewMatchers.withId(resId))
        .check(ViewAssertions.doesNotExist())

    private fun matchText(viewInteraction: ViewInteraction, text: Int): ViewInteraction =
        viewInteraction
            .check(ViewAssertions.matches(ViewMatchers.withText(text)))

    fun matchText(resId: Int, text: Int): ViewInteraction = matchText(textView(resId), text)

    fun shortSleep(millis: Long = 350) = apply {
        Thread.sleep(millis)
    }

    fun mediumSleep(millis: Long = 1000) = apply {
        Thread.sleep(millis)
    }

    fun longSleep(millis: Long = 5000) = apply {
        Thread.sleep(millis)
    }

    fun pressDeviceBackButton() {
        Espresso.pressBack()
    }

    fun checkToolbarTitle(expectedText: Int) {
        Espresso.onView(
            CoreMatchers.allOf(
                ViewMatchers.isAssignableFrom(TextView::class.java),
                ViewMatchers.withParent(ViewMatchers.isAssignableFrom(Toolbar::class.java))
            )
        )
            .check(ViewAssertions.matches(ViewMatchers.withText(expectedText)))
    }

    fun checkToolbarTitleString(expectedText: String) {
        Espresso.onView(
            CoreMatchers.allOf(
                ViewMatchers.isAssignableFrom(TextView::class.java),
                ViewMatchers.withParent(ViewMatchers.isAssignableFrom(Toolbar::class.java))
            )
        )
            .check(ViewAssertions.matches(ViewMatchers.withText(expectedText)))
    }

    fun getStringFromTextView(resId: Int): String {
        val stringHolder = arrayListOf<String>()
        Espresso.onView(ViewMatchers.withId(resId)).perform(object : ViewAction {
            override fun getDescription(): String {
                return "getting text from a TextView"
            }

            override fun getConstraints(): org.hamcrest.Matcher<View> {
                return ViewMatchers.isAssignableFrom(TextView::class.java)
            }

            override fun perform(uiController: UiController?, view: View?) {
                val tv = view as TextView
                stringHolder.add(tv.text.toString())
            }
        })
        return stringHolder[0]
    }

}