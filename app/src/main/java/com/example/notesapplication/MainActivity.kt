package com.example.notesapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.notesapplication.dependecies.applicationComponent
import com.example.notesfeature.noteListFragment
import com.example.notesfeature.notesFragmentFactory

class MainActivity : AppCompatActivity() {

    private val backendCommunication
        get() = this.applicationComponent.backendCommunication

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = notesFragmentFactory(backendCommunication)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add(
                    R.id.main_container,
                    noteListFragment(backendCommunication)
                )
            }
        }
    }
}