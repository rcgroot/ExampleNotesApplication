package com.example.notesapplication.dependecies

import android.util.Log
import com.example.support.Analytics

class AppAnalytics : Analytics {
    override fun trackEvent(event: String) {
        // Naive implementation that does not really track but just logs.
        Log.i("AppAnalytics", event)
    }
}
