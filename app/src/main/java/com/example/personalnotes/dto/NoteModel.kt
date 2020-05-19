package com.example.personalnotes.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NoteModel(
    @Expose
    @SerializedName("id")
    val id: String,
    @Expose
    @SerializedName("title")
    var title: String
)
