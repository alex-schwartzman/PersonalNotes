package com.example.personalnotes.datastore

import androidx.fragment.app.Fragment
import com.example.personalnotes.databinding.NoteListViewModelFactory
import com.example.personalnotes.repository.NoteRepository
import com.example.personalnotes.databinding.NoteDetailViewModelFactory

object InjectorUtils {
    fun provideNoteListViewModelFactory(fragment: Fragment): NoteListViewModelFactory {
        val repository = NoteRepository.getInstance(NotesApiService.client)
        return NoteListViewModelFactory(
            repository,
            fragment
        )
    }

    fun provideNoteDetailViewModelFactory(noteId: String): NoteDetailViewModelFactory {
        val repository = NoteRepository.getInstance(NotesApiService.client)
        return NoteDetailViewModelFactory(repository, noteId)
    }
}

