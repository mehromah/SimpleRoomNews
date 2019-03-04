package com.example.myroomsamplenews1;


import android.content.Context;

import com.example.myroomsamplenews1.data.News;
import com.example.myroomsamplenews1.data.NewsRepository;

import java.util.List;

import io.reactivex.Flowable;

public class MainViewModel  {
    private NewsRepository newsRepository;

    public MainViewModel(Context context) {
        newsRepository = new NewsRepository(context);
    }

    Flowable<List<News>> getNewsList() {
        return newsRepository.getNews();
    }
}