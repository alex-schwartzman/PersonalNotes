package com.example.personalnotes.databinding

import androidx.lifecycle.ViewModel
import com.example.personalnotes.repository.NoteRepository

class NoteDetailViewModel(
    private val noteRepository: NoteRepository,
    val noteId: String
) : ViewModel() {

    val note = noteRepository.getNote(noteId)

    fun delete(){
        noteRepository.delete(noteId)
    }

    fun update() {
        note.value?.let { noteRepository.update(it) }
    }
}
