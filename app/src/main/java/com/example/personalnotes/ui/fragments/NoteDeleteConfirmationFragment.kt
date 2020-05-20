package com.example.personalnotes.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.personalnotes.R
import com.example.personalnotes.databinding.FragmentDeleteConfirmationBinding
import com.example.personalnotes.databinding.NoteDetailViewModel
import com.example.personalnotes.datastore.InjectorUtils
import com.google.android.material.snackbar.Snackbar

class NoteDeleteConfirmationFragment : Fragment() {

    private val args: NoteDeleteConfirmationFragmentArgs by navArgs()

    private val noteDetailViewModel: NoteDetailViewModel by viewModels {
        InjectorUtils.provideNoteDetailViewModelFactory(args.noteId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentDeleteConfirmationBinding>(
            inflater, R.layout.fragment_delete_confirmation, container, false
        ).apply {
            viewModel = noteDetailViewModel
            lifecycleOwner = viewLifecycleOwner
            confirmationCallback = object : ConfirmedCallback {
                override fun confirm() {
                    noteDetailViewModel.delete()
                    Snackbar.make(root.rootView, R.string.deleted_note, Snackbar.LENGTH_LONG)
                        .show()
                    findNavController().popBackStack()
                    findNavController().navigateUp()
                }
            }

            cancelledCallback = object : CancelledCallback {
                override fun cancel() {
                    findNavController().navigateUp()
                }
            }
        }
        return binding.root
    }

    interface ConfirmedCallback {
        fun confirm()
    }

    interface CancelledCallback {
        fun cancel()
    }
}
