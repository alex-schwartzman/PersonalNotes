package com.example.personalnotes.databinding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.personalnotes.repository.NoteRepository

class NoteDetailViewModelFactory(
    private val noteRepository: NoteRepository,
    private val noteId: String
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteDetailViewModel(noteRepository, noteId) as T
    }
}
