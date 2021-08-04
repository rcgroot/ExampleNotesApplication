// ADR # 20. Feature modules: Put private API classes into package named 'internal'
package com.example.notesfeature.internal.notedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.backend.BackendCommunication
import com.example.notesfeature.R
import com.example.notesfeature.databinding.NotesNoteDetailsBinding

/**
 * ADR # 14. Feature modules: Limit public API with 'internal' visibility
 * ADR # 19. Organise source files into packages by feature, not layers
 */
internal class NoteDetailsFragment(backend: BackendCommunication, private val noteId: Int) : DialogFragment() {

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
     * Or disregard everything i added in this app and just uncomment the
     * code below this comment and add the note in the fragments constructor
     */
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
//        MaterialAlertDialogBuilder(requireContext())
//            .setTitle(note.title)
//            .setMessage(note.title)
//            .create()
}
