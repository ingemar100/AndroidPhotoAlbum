package com.example.ingemar.photoalbumdemo

object Loader  {

    lateinit var photos: List<Photo>
    lateinit var albums: List<Album>

    //must not be on UI thread
    fun load(){
        photos = NetworkService.instance().jsonInterface.getPhotos().execute().body()!!
        albums = NetworkService.instance().jsonInterface.getAlbums().execute().body()!!
    }
}