package com.example.myroomsamplenews1.data;

import java.util.List;

import io.reactivex.Flowable;

public interface NewsDataSource {
    Flowable<List<News>> getNews();

}