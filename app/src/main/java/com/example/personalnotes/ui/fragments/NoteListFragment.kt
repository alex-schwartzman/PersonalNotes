package com.example.personalnotes.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.personalnotes.databinding.FragmentNoteListBinding
import com.example.personalnotes.datastore.InjectorUtils
import com.example.personalnotes.ui.adapters.NoteAdapter
import com.example.personalnotes.databinding.NoteListViewModel
import com.example.personalnotes.dto.NoteModel

class NoteListFragment : Fragment() {

    private val viewModel: NoteListViewModel by viewModels {
        InjectorUtils.provideNoteListViewModelFactory(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNoteListBinding.inflate(inflater, container, false)
        context ?: return binding.root
        val adapter = NoteAdapter()
        binding.noteList.adapter = adapter
        viewModel.notes.observe(viewLifecycleOwner) { notes ->
            adapter.submitList(notes)
        }
        return binding.root
    }
}
