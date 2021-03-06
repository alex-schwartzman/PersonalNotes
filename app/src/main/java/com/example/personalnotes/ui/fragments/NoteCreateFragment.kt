package com.example.personalnotes.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.personalnotes.R
import com.example.personalnotes.databinding.FragmentNoteCreateBinding
import com.example.personalnotes.databinding.NoteCreateViewModel
import com.example.personalnotes.datastore.InjectorUtils
import com.google.android.material.snackbar.Snackbar

class NoteCreateFragment : Fragment() {

    private lateinit var binding: FragmentNoteCreateBinding

    private val noteCreateViewModel: NoteCreateViewModel by viewModels {
        InjectorUtils.provideNoteCreateViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteCreateBinding.inflate(inflater, container, false)
            .apply {
                viewModel = noteCreateViewModel
                lifecycleOwner = viewLifecycleOwner
                createCallback = object : CreateCallback {
                    override fun create() {
                        noteCreateViewModel.create()
                        Snackbar.make(root.rootView, R.string.created_note, Snackbar.LENGTH_LONG)
                            .show()
                        findNavController().navigateUp()
                    }
                }

                discardCallback = object : DiscardCallback {
                    override fun discard() {
                        Snackbar.make(
                            root.rootView,
                            R.string.discarded_draft_note,
                            Snackbar.LENGTH_LONG
                        )
                            .show()
                        findNavController().navigateUp()
                    }
                }
            }
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    interface DiscardCallback {
        fun discard()
    }

    interface CreateCallback {
        fun create()
    }
}
