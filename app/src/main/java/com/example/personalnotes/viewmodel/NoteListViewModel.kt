package com.example.personalnotes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.personalnotes.dto.NoteModel
import com.example.personalnotes.repository.NoteRepository

class NoteListViewModel constructor(
    noteRepository: NoteRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val notes: MutableLiveData<List<NoteModel>> = noteRepository.getNotes()
}
