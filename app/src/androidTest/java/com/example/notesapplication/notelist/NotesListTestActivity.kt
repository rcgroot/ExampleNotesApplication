package com.example.notesapplication.notelist

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.example.backend.BackendCommunication
import com.example.notesapplication.mocks.MockBackendCommunicationTest
import com.example.notesfeature.noteListFragment
import com.example.notesfeature.notesFragmentFactory

class NotesListTestActivity : FragmentActivity() {
    private val backendCommunication: BackendCommunication = MockBackendCommunicationTest()

    override fun onCreate(savedInstanceState: Bundle?) {
//        (backendCommunication as MockBackendCommunicationTest).noteList = arrayListOf<>()
        supportFragmentManager.fragmentFactory = notesFragmentFactory(backendCommunication)
        super.onCreate(savedInstanceState)
        val layout = FrameLayout(this)
        layout.id = View.generateViewId()
        setContentView(layout)
        supportFragmentManager.commit {
            add(
                layout.id,
                noteListFragment(backendCommunication)
            )
        }
    }
}
