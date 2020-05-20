package com.example.personalnotes.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.personalnotes.R
import com.example.personalnotes.databinding.FragmentNoteDetailBinding
import com.example.personalnotes.databinding.NoteDetailViewModel
import com.example.personalnotes.datastore.InjectorUtils
import com.google.android.material.snackbar.Snackbar

class NoteDetailFragment : Fragment() {

    private lateinit var binding: FragmentNoteDetailBinding
    private val args: NoteDetailFragmentArgs by navArgs()

    private val noteDetailViewModel: NoteDetailViewModel by viewModels {
        InjectorUtils.provideNoteDetailViewModelFactory(args.noteId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
            .apply {
                viewModel = noteDetailViewModel
                lifecycleOwner = viewLifecycleOwner
                saveCallback = object : SaveCallback {
                    override fun save() {
                        noteDetailViewModel.update()
                        Snackbar.make(root.rootView, R.string.updated_note, Snackbar.LENGTH_LONG)
                            .show()
                        findNavController().navigateUp()
                    }
                }

                deleteCallback = object : DeleteCallback {
                    override fun delete() {
                        findNavController().navigate(
                            NoteDetailFragmentDirections.actionDetailFragmentToDeleteConfirmationFragment(
                                args.noteId
                            )
                        )
                    }
                }
            }
        return binding.root
    }

    interface DeleteCallback {
        fun delete()
    }

    interface SaveCallback {
        fun save()
    }
}
