package com.example.notesapplication

import android.app.Application
import com.example.backend_mock.MockBackendCommunication
import com.example.notesapplication.dependecies.ApplicationComponent

class NotesApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = ApplicationComponent(
            backendCommunication = MockBackendCommunication(this)
        )
    }
}