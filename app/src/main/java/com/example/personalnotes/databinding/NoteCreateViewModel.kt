package com.example.personalnotes.databinding

import androidx.lifecycle.ViewModel
import com.example.personalnotes.dto.NoteModel
import com.example.personalnotes.repository.NoteRepository

class NoteCreateViewModel(
    private val noteRepository: NoteRepository
) : ViewModel() {

    val id = "new ID"
    val note = NoteModel(id, "")

    fun create() {
        noteRepository.create(note)
    };
}
