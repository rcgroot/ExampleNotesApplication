// ADR # 20. Feature modules: Put private API classes into package named 'internal'
package com.example.notesfeature.internal.notedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.backend.BackendCommunication
import com.example.notesfeature.R
import com.example.notesfeature.databinding.NotesNoteDetailsBinding

private const val ARGUMENTS_ID = "ARGUMENTS_ID"

/**
 * ADR # 14. Feature modules: Limit public API with 'internal' visibility
 * ADR # 19. Organise source files into packages by feature, not layers
 */
internal class NoteDetailsFragment(backend: BackendCommunication) : DialogFragment() {

    private val noteId: Int
        get() = checkNotNull(requireArguments().getInt(ARGUMENTS_ID))
    private val viewModel by viewModels<NoteDetailsViewModel> { NoteDetailsViewModel.Factory(backend, noteId) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = DataBindingUtil.inflate<NotesNoteDetailsBinding>(
        inflater,
        R.layout.notes__note_details,
        container,
        false
    ).apply {
        view = viewModel.presenter.view
        presenter = viewModel.presenter
        lifecycleOwner = viewLifecycleOwner
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NoteDetailsNavigator(this).observeNavigation(
            viewLifecycleOwner,
            viewModel.presenter.navigation
        )
    }

    /**
     * ADR # 22. Expose member method 'withArguments' in each Fragment to pass in arguments
     */
    fun withArguments(id: Int) = apply {
        arguments = bundleOf(
            ARGUMENTS_ID to id
        )
    }
}
