// ADR # 20. Feature modules: Put private API classes into package named 'internal'
package com.example.notesfeature.internal.notelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.backend.BackendCommunication
import com.example.notesfeature.R
import com.example.notesfeature.databinding.FragmentItemListBinding

/**
 * ADR # 14. Feature modules: Limit public API with 'internal' visibility
 * ADR # 19. Organise source files into packages by feature, not layers
 */
internal class NoteListFragment(
    backend: BackendCommunication
) : Fragment() {

    private val viewModel by viewModels<NoteListViewModel> { NoteListViewModel.Factory(backend) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        DataBindingUtil.inflate<FragmentItemListBinding>(
            inflater,
            R.layout.fragment_item_list,
            container,
            false
        ).apply {
            view = viewModel.presenter.view
            presenter = viewModel.presenter
            lifecycleOwner = viewLifecycleOwner
            NoteListNavigator(this@NoteListFragment).observe(
                viewLifecycleOwner,
                viewModel.presenter.navigation
            )
        }.root
}
