package com.example.notesfeature.internal.noteslist

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.example.backend.BackendCommunication
import com.example.backend_mock.MockBackendCommunication
import com.example.notesfeature.noteListFragment
import com.example.notesfeature.notesFragmentFactory

class NotesListTestActivity : FragmentActivity() {
    private val backendCommunication: BackendCommunication = MockBackendCommunication(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = notesFragmentFactory(this, backendCommunication)
        super.onCreate(savedInstanceState)
        val layout = FrameLayout(this)
        layout.id = View.generateViewId()
        setContentView(layout)
        val fragment = noteListFragment(this, backendCommunication)
        supportFragmentManager.commit {
            add(
                layout.id,
                fragment
            )
        }
    }
}
