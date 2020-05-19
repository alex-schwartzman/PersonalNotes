package com.example.personalnotes.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.personalnotes.databinding.ListItemNoteBinding
import com.example.personalnotes.dto.NoteModel
import com.example.personalnotes.ui.main.NoteListFragmentDirections

class NoteAdapter : ListAdapter<NoteModel, RecyclerView.ViewHolder>(NoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NoteViewHolder(
            ListItemNoteBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val note = getItem(position)
        (holder as NoteViewHolder).bind(note)
    }

    class NoteViewHolder(
        private val binding: ListItemNoteBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.note?.let { note ->
                    navigateToNote(note, it)
                }
            }
        }

        private fun navigateToNote(
            note: NoteModel,
            view: View
        ) {
            val direction =
                NoteListFragmentDirections.actionViewPagerFragmentToNoteDetailFragment(
//                    note.id
                )
            view.findNavController().navigate(direction)
        }

        fun bind(item: NoteModel) {
            binding.apply {
                note = item
                executePendingBindings()
            }
        }
    }
}

private class NoteDiffCallback : DiffUtil.ItemCallback<NoteModel>() {

    override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}