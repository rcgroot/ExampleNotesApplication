package com.example.notesapplication

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.backend_mock.MockBackendCommunication
import com.example.notesapplication.dependecies.AppAnalytics
import com.example.notesapplication.dependecies.ApplicationComponent

class NotesApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        super.onCreate()

        applicationComponent = ApplicationComponent(
            backendCommunication = MockBackendCommunication(this),
            analytics = AppAnalytics()
        )
    }
}