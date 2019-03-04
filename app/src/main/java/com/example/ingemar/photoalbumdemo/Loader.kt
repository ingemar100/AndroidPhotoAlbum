package com.example.ingemar.photoalbumdemo

object Loader  {

    lateinit var photos: List<Photo>
    lateinit var albums: List<Album>

    //must not be on UI thread, only called once by AlbumListActivity
    fun load(){
        photos = NetworkService.instance().jsonInterface.getPhotos().execute().body()!!
        albums = NetworkService.instance().jsonInterface.getAlbums().execute().body()!!

        //add object references for easy programming
        for (p in photos){
            p.album = albums.find{it.id == p.albumId}!!
        }
        for (a in albums){
            a.photos = photos.filter{it.albumId == a.id}
        }
    }
}