package com.example.notesapplication.notedetails

import com.example.notesapplication.R
import com.example.notesapplication.utils.BaseTestRobot
import com.schibsted.spain.barista.interaction.BaristaListInteractions.clickListItem
import org.junit.Assert

inline fun noteDetails(block: NoteDetailsRobot.() -> Unit) = NoteDetailsRobot().apply(block)

class NoteDetailsRobot : BaseTestRobot() {

    init {
        openNote()
    }

    private fun openNote() {
        clickListItem(R.id.list, 0)
    }

    fun closeIconIsVisible() {
        viewIsVisible(R.id.close_icon)
    }

    fun closeIconIsClickable() {
        buttonIsClickable(R.id.close_icon)
    }

    fun closeIconIsEnabled() {
        buttonIsEnabled(R.id.close_icon)
    }

    fun closeIconDoesNotExist() {
        viewDoesNotExist(R.id.close_icon)
    }

    fun clickCloseButton() {
        clickButton(R.id.close_icon)
    }

    fun loadingIconIsVisible() {
        viewIsVisible(R.id.progress_bar_loader)
    }

    fun loadingIconIsNotVisible() {
        viewIsNotVisible(R.id.progress_bar_loader)
    }

    fun titleTextIsVisible() {
        viewIsVisible(R.id.item_title_text)
    }

    fun titleTextEquals(text: String) {
        Assert.assertEquals(getTitleText(), text)
    }

    fun getTitleText(): String {
        return getStringFromTextView(R.id.item_title_text)
    }

    fun contentTextIsVisible() {
        viewIsVisible(R.id.item_content_text)
    }

    fun getContentText(): String {
        return getStringFromTextView(R.id.item_content_text)
    }

    fun contentTextEquals(text: String) {
        Assert.assertEquals(getContentText(), text)
    }

}
