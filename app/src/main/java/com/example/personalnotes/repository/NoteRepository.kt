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

    fun getNote(id: String): MutableLiveData<NoteModel?> {
        val noteModelData: MutableLiveData<NoteModel?> = MutableLiveData<NoteModel?>()
        notesApi.getNote(id).enqueue(object : Callback<NoteModel?> {
            override fun onResponse(call: Call<NoteModel?>,
                                    response: Response<NoteModel?>) {
                if (response.isSuccessful()) {
                    noteModelData.setValue(response.body())
                }
            }

            override fun onFailure(call: Call<NoteModel?>, t: Throwable) {
                noteModelData.setValue(null)
            }
        })
        return noteModelData
    }

    fun update(note: NoteModel): MutableLiveData<NoteModel?> {
        val noteModelData: MutableLiveData<NoteModel?> = MutableLiveData<NoteModel?>()

        notesApi.updateNote(note.id, note).enqueue(object : Callback<NoteModel?> {
            override fun onResponse(call: Call<NoteModel?>,
                                    response: Response<NoteModel?>) {
                if (response.isSuccessful()) {
                    noteModelData.setValue(response.body())
                }
            }

            override fun onFailure(call: Call<NoteModel?>, t: Throwable) {
                noteModelData.setValue(null)
            }
        })

        return noteModelData
    }

    companion object {
        @Volatile private var instance: NoteRepository? = null

        fun getInstance(notesApi: NotesApi) =
            instance ?: synchronized(this) {
                instance ?: NoteRepository(notesApi).also { instance = it }
            }
    }
}

