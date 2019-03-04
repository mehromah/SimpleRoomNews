package com.example.myroomsamplenews1;


import android.content.Context;

import com.example.myroomsamplenews1.data.News;
import com.example.myroomsamplenews1.data.NewsRepository;

import java.util.List;

import io.reactivex.Flowable;

public class MainViewModel  {
    private NewsRepository newsRepository;
    private Flowable<List<News>> newsList;

    public MainViewModel(Context context) {

        newsRepository = new NewsRepository(context);
        newsList = newsRepository.getNews();
    }

    Flowable<List<News>> getNewsList() {
        return newsList;
    }




}
