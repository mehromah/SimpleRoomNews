package com.example.myroomsamplenews1.data;


import java.util.List;

import io.reactivex.Flowable;

public class EnglishCloudDataSource extends CloudDataSource {
    private EnglishApiService englishApiService;

    public EnglishCloudDataSource() {
        super();
        englishApiService = retrofit.create(EnglishApiService.class);
    }

    @Override
    public Flowable<List<News>> getNews() {
        return englishApiService.getNews();
    }



}
