package com.example.ingemar.photoalbumdemo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Photo {
    @SerializedName("albumId")
    @Expose
    var albumId = 0

    @SerializedName("id")
    @Expose
    var id = 0

    @SerializedName("title")
    @Expose
    var title = ""

    @SerializedName("url")
    @Expose
    var url = ""

    @SerializedName("thumbnailUrl")
    @Expose
    var thumbnailUrl = ""

    lateinit var album: Album
}