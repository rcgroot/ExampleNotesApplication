package com.example.notesapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.notesapplication.dependecies.applicationComponent
import com.example.notesfeature.itemFragment
import com.example.notesfeature.itemFragmentFactory

class MainActivity : AppCompatActivity() {

    private val backendCommunication
        get() = this.applicationComponent.backendCommunication

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = itemFragmentFactory(backendCommunication)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add(
                    R.id.main_container,
                    itemFragment(backendCommunication)
                )
            }
        }
    }
}