package com.example.ingemar.photoalbumdemo;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private Retrofit mRetrofit;
    private static NetworkService mInstance = new NetworkService();

    private NetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public JsonApi getJsonInterface() {
        return mRetrofit.create(JsonApi.class);
    }

    public static NetworkService instance(){
        return mInstance;
    }
}