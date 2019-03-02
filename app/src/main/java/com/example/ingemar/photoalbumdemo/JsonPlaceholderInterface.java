package com.example.ingemar.photoalbumdemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface JsonPlaceholderInterface {
    @GET("/posts/{id}")
    Call<Post> getPostWithID(@Path("id") int id);

    @GET("/albums")
    Call<List<Album>> getAlbums();
}