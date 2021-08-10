package com.example.notesfeature

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.NONE
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Wrapper around kotlinx.coroutines.Dispatchers to help testing.
 *
 * For instance in Espresso tests replace Default with a dispatcher
 * taking into account for isIdle or a synchronized variant
 *
 * For replacing Main see kotlinx-coroutines-test and Dispatchers.setMain(...)
 */
object NotesDispatchers {

    @VisibleForTesting(otherwise = NONE)
    var testingOverride: CoroutineDispatcher? = null

    val Main
        get() = testingOverride ?: kotlinx.coroutines.Dispatchers.Main

    val Default: CoroutineDispatcher
        get() = testingOverride ?: kotlinx.coroutines.Dispatchers.Default

    val IO: CoroutineDispatcher
        get() = testingOverride ?: kotlinx.coroutines.Dispatchers.IO
}
