package com.example.ingemar.photoalbumdemo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Album {
    @SerializedName("userId")
    @Expose
    var userId = 0

    @SerializedName("id")
    @Expose
    var id = 0

    @SerializedName("title")
    @Expose
    var title = ""

    override fun toString(): String{
        return id.toString() + ": " + title + " (user " + userId + ")"
    }

    lateinit var photos: List<Photo>
}