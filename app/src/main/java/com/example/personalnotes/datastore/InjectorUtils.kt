package com.example.personalnotes.datastore

import androidx.fragment.app.Fragment
import com.example.personalnotes.viewmodel.NoteListViewModelFactory
import com.example.personalnotes.repository.NoteRepository

object InjectorUtils {

    fun provideNoteListViewModelFactory(fragment: Fragment): NoteListViewModelFactory {
        val repository = NoteRepository.getInstance(NotesApiService.client)
        return NoteListViewModelFactory(
            repository,
            fragment
        )
    }
}

