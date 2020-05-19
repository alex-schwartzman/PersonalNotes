package com.example.personalnotes.databinding

import androidx.lifecycle.ViewModel
import com.example.personalnotes.dto.NoteModel
import com.example.personalnotes.repository.NoteRepository

class NoteDetailViewModel(
    private val noteRepository: NoteRepository,
    private val noteId: String
) : ViewModel() {

    fun update(note: NoteModel) {
        noteRepository.update(note);
    }

    val id = noteId
    val note = noteRepository.getNote(noteId)
}
