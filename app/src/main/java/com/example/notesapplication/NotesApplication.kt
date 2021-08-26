package com.example.notesapplication

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.backend_mock.MockBackendCommunication
import com.example.notesapplication.dependecies.ApplicationComponent
import com.example.notesfeature.NotesDispatchers
import kotlinx.coroutines.runBlocking

class NotesApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        super.onCreate()

        runBlocking (NotesDispatchers.IO) {
            applicationComponent = ApplicationComponent(
                //TODO Fix doing blocking application startup caused by IO on Main thread
                backendCommunication = MockBackendCommunication(this@NotesApplication)
            )
        }
    }
}
