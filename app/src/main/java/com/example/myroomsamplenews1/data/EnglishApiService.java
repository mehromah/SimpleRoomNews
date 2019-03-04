package com.example.myroomsamplenews1.data;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.http.GET;



public interface EnglishApiService {
    @GET("p5gwn")
    Flowable<List<News>> getNews();


}
