// ADR # 20. Feature modules: Put private API classes into package named 'internal'
package com.example.notesfeature.internal.notedetail

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * ADR # 14. Feature modules: Limit public API with 'internal' visibility
 * ADR # 19. Organise source files into packages by feature, not layers
 */
internal class NoteFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Some title")
            .setMessage("With a body")
            .create()
}
