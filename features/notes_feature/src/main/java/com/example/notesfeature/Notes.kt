package com.example.notesfeature

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.backend.BackendCommunication
import com.example.notesfeature.Graph.Companion.get
import com.example.notesfeature.internal.notedetail.NoteDetailsFragment
import com.example.notesfeature.internal.notelist.NoteListFragment
import com.example.notesfeature.internal.service.NoteService
import com.example.notesfeature.internal.service.NoteServiceImpl
import com.example.support.Analytics

/**
 * ADR # 13. Feature modules: Expose only a single Fragment to public and use child Fragments for internal flows
 */
fun noteListFragment(scope: FragmentActivity, backendCommunication: BackendCommunication, analytics: Analytics):
        Fragment {
    val viewModel = scope.get(backendCommunication, analytics)
    return NoteListFragment(viewModel.noteService, viewModel.analytics)
}

/**
 * ADR # 21. Use AndroidX Fragment factories to inject dependencies into Fragments
 */
fun notesFragmentFactory(scope: FragmentActivity, backendCommunication: BackendCommunication, analytics: Analytics) = object : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val viewModel = scope.get(backendCommunication, analytics)
        return when (Class.forName(className).kotlin) {
            NoteListFragment::class -> NoteListFragment(viewModel.noteService, viewModel.analytics)
            NoteDetailsFragment::class -> NoteDetailsFragment(viewModel.noteService, viewModel.analytics)
            else -> super.instantiate(classLoader, className)
        }
    }
}

internal class Graph(private val service: NoteService, private val analyticsObject:
Analytics) : ViewModel() {
    val noteService: NoteService
        get() = serviceOverride ?: service

    val analytics: Analytics
        get() = analyticsOverride ?: analyticsObject

    companion object {
        var serviceOverride: NoteService? = null
        var analyticsOverride: Analytics? = null
        fun FragmentActivity.get(backendCommunication: BackendCommunication, analytics: Analytics) =
            ViewModelProvider(this, Factory(backendCommunication, analytics)).get(Graph::class.java)
    }

    class Factory(private val backendCommunication: BackendCommunication, private val analytics:
    Analytics) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            Graph(NoteServiceImpl(backendCommunication), analytics) as T
    }
}