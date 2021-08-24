// ADR # 20. Feature modules: Put private API classes into package named 'internal'
package com.example.notesfeature.internal.notelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.notesfeature.R
import com.example.notesfeature.databinding.NotesListBinding
import com.example.notesfeature.internal.service.NoteService

/**
 * ADR # 14. Feature modules: Limit public API with 'internal' visibility
 * ADR # 19. Organise source files into packages by feature, not layers
 */
internal class NoteListFragment(
    private val noteService: NoteService
) : Fragment() {

    private val viewModel by viewModels<NoteListViewModel> { NoteListViewModel.Factory(noteService) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        DataBindingUtil.inflate<NotesListBinding>(
            inflater,
            R.layout.notes__list,
            container,
            false
        ).apply {
            view = viewModel.view
            presenter = viewModel.presenter
            lifecycleOwner = viewLifecycleOwner
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NoteListNavigator(this, noteService).observeNavigation(
            viewLifecycleOwner,
            viewModel.navigation
        )
    }
}
