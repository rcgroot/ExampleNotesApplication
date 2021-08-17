package com.example.notesfeature

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.backend.BackendCommunication
import com.example.notesfeature.internal.notedetail.NoteDetailsFragment
import com.example.notesfeature.internal.notelist.NoteListFragment
import com.example.notesfeature.internal.service.NoteService
import com.example.notesfeature.internal.service.NoteServiceImpl

/**
 * ADR # 13. Feature modules: Expose only a single Fragment to public and use child Fragments for internal flows
 */
fun noteListFragment(scope: FragmentActivity, backendCommunication: BackendCommunication): Fragment {
    val viewModel = Graph.get(scope, backendCommunication)
    return NoteListFragment(viewModel.noteService)
}

/**
 * ADR # 21. Use AndroidX Fragment factories to inject dependencies into Fragments
 */
fun notesFragmentFactory(scope: FragmentActivity, backendCommunication: BackendCommunication) = object : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val viewModel = Graph.get(scope, backendCommunication)
        return when (Class.forName(className).kotlin) {
            NoteListFragment::class -> NoteListFragment(viewModel.noteService)
            NoteDetailsFragment::class -> NoteDetailsFragment(viewModel.noteService)
            else -> super.instantiate(classLoader, className)
        }
    }
}

internal class Graph(val noteService: NoteService) : ViewModel() {

    companion object {
        fun get(scope: FragmentActivity, backendCommunication: BackendCommunication) =
            ViewModelProvider(scope, Factory(backendCommunication)).get(Graph::class.java)
    }

    class Factory(private val backendCommunication: BackendCommunication) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            Graph(NoteServiceImpl(backendCommunication)) as T
    }
}