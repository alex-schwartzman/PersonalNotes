package com.example.personalnotes.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.personalnotes.databinding.FragmentNoteListBinding
import com.example.personalnotes.databinding.NoteListViewModel
import com.example.personalnotes.datastore.InjectorUtils
import com.example.personalnotes.ui.adapters.NoteAdapter

class NoteListFragment : Fragment() {

    private lateinit var binding: FragmentNoteListBinding

    private val viewModel: NoteListViewModel by viewModels {
        InjectorUtils.provideNoteListViewModelFactory(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteListBinding.inflate(inflater, container, false)
        context ?: return binding.root
        val adapter = NoteAdapter()
        binding.noteList.adapter = adapter
        viewModel.notes.observe(viewLifecycleOwner) { notes ->
            adapter.submitList(notes)
        }
        binding.newItemClickListener = View.OnClickListener {
            findNavController().navigate(NoteListFragmentDirections.actionViewPagerFragmentToNoteCreateFragment())
        }
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }
}
