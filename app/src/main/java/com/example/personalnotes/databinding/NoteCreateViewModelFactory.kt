package com.example.personalnotes.databinding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.personalnotes.repository.NoteRepository

class NoteCreateViewModelFactory(
    private val noteRepository: NoteRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteCreateViewModel(noteRepository) as T
    }
}
