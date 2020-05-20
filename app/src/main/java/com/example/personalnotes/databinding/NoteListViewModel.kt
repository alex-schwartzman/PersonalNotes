package com.example.personalnotes.databinding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.personalnotes.dto.NoteModel
import com.example.personalnotes.repository.NoteRepository

class NoteListViewModel constructor(
    noteRepository: NoteRepository
) : ViewModel() {

    val notes: MutableLiveData<List<NoteModel>> = noteRepository.getNotes()
}
