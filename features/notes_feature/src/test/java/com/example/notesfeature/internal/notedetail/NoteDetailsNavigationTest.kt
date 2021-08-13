package com.example.notesfeature.internal.notedetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import com.example.notesfeature.internal.service.Note
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock

class NoteDetailsNavigationTest {

    @get:Rule
    val instantRule = InstantTaskExecutorRule()

    private val sut = NoteDetailsNavigation()

    @Test
    fun `when closing notes an navigation event is fired`() {

        sut.closeNoteDetails()

        var result = false
        sut.step.value?.consume {
            result = true
        }
        assertThat(result).isTrue
    }
}
