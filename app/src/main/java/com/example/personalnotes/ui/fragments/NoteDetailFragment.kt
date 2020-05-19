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
import com.example.personalnotes.databinding.FragmentNoteDetailBinding
import com.example.personalnotes.databinding.NoteDetailViewModel
import com.example.personalnotes.datastore.InjectorUtils
import com.example.personalnotes.dto.NoteModel
import com.google.android.material.snackbar.Snackbar

class NoteDetailFragment : Fragment() {

    private val args: NoteDetailFragmentArgs by navArgs()

    private val noteDetailViewModel: NoteDetailViewModel by viewModels {
        InjectorUtils.provideNoteDetailViewModelFactory(args.noteId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentNoteDetailBinding>(
            inflater, R.layout.fragment_note_detail, container, false
        ).apply {
            viewModel = noteDetailViewModel
            lifecycleOwner = viewLifecycleOwner
            callback = object : Callback {
                override fun save(note: NoteModel?) {
                    note?.let {
                        noteDetailViewModel.update(it)
                        Snackbar.make(root, R.string.updated_note, Snackbar.LENGTH_LONG)
                            .show()
                        findNavController().navigateUp()
                    }
                }
            }
        }

        return binding.root
    }

    interface Callback {
        fun save(note: NoteModel?)
    }
}
