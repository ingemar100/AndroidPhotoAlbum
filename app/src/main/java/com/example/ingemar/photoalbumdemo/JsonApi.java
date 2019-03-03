package com.example.ingemar.photoalbumdemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface JsonApi {

    @GET("/albums")
    Call<List<Album>> getAlbums();

    @GET("/photos")
    Call<List<Photo>> getPhotos();

    @GET("/photos/{id}")
    Call<Photo> getPhotoByID(@Path("id") int id);

    @GET("/photos")
    public Call<List<Photo>> getPhotosInAlbum(@Query("albumId") int id);
}