package com.example.personalnotes.datastore

import com.example.personalnotes.dto.NoteModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface NotesApi {

    @GET("notes")
    fun getNotes(): Call<List<NoteModel>>

    @POST("notes")
    fun createNote(@Body note: NoteModel): Call<NoteModel>

    @GET("notes/{id}")
    fun getNote(@Path("id") id: String): Call<NoteModel>

    @PUT("notes/{id}")
    fun updateNote(@Path("id") id: String, @Body note: NoteModel): Call<NoteModel>

    @DELETE("notes/{id}")
    fun deleteNote(@Path("id") id: String): Call<ResponseBody>

}
