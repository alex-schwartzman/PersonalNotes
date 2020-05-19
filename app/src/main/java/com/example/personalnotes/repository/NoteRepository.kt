package com.example.personalnotes.repository

import androidx.lifecycle.MutableLiveData
import com.example.personalnotes.datastore.NotesApi
import com.example.personalnotes.dto.NoteModel
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response
import java.util.*

class NoteRepository private constructor(private val notesApi: NotesApi) {

    fun getNotes(): MutableLiveData<List<NoteModel>> {
        val newsData: MutableLiveData<List<NoteModel>> = MutableLiveData<List<NoteModel>>()
        notesApi.getNotes().enqueue(object : Callback<List<NoteModel>> {
            override fun onResponse(call: Call<List<NoteModel>>,
                                    response: Response<List<NoteModel>>
            ) {
                if (response.isSuccessful()) {
                    newsData.setValue(response.body())
                }else{
                    newsData.setValue(LinkedList<NoteModel>())
                }
            }

            override fun onFailure(call: Call<List<NoteModel>>, t: Throwable) {
                newsData.setValue(LinkedList<NoteModel>())
            }
        })
        return newsData
    }

    companion object {
        @Volatile private var instance: NoteRepository? = null

        fun getInstance(notesApi: NotesApi) =
            instance ?: synchronized(this) {
                instance ?: NoteRepository(notesApi).also { instance = it }
            }
    }
}

