package com.example.notesapplication.utils

import android.os.AsyncTask
import com.example.notesfeature.NotesDispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.cancel
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Will replace the dispatchers with a variant registered as IdlingResource
 */
class DispatchersRule : TestRule {
    override fun apply(base: Statement, description: Description?): Statement = object : Statement() {
        override fun evaluate() {
            val dispatcher = AsyncTask.THREAD_POOL_EXECUTOR.asCoroutineDispatcher()
            NotesDispatchers.testingOverride = dispatcher
            try {
                base.evaluate()
            } finally {
                NotesDispatchers.testingOverride = null
                dispatcher.cancel()
            }
        }
    }
}