package com.example.notesapplication

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.backend_mock.MockBackendCommunication
import com.example.notesapplication.dependecies.ApplicationComponent
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class NotesApplication : Application() {

    private lateinit var applicationComponentJob: Deferred<ApplicationComponent>
    val applicationComponent: ApplicationComponent
        get() = runBlocking { applicationComponentJob.await() }

    override fun onCreate() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        super.onCreate()

        applicationComponentJob = GlobalScope.async(context = Dispatchers.IO, start = CoroutineStart.DEFAULT) {
            ApplicationComponent(
                backendCommunication = MockBackendCommunication(this@NotesApplication)
            )
        }
    }
}
