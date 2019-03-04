package com.example.myroomsamplenews1.data;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.schedulers.Schedulers;


public class NewsRepository implements NewsDataSource {
    private CloudDataSource cloudDataSource;
    private LocalDataSource localDataSource;

    public NewsRepository(Context context) {
        localDataSource = AppDatabase.getInstance(context).getLocalDataSource();
        cloudDataSource = new EnglishCloudDataSource();

    }

    @Override
    public Flowable<List<News>> getNews() {
        cloudDataSource.getNews().subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(new FlowableSubscriber<List<News>>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }
                    @Override
                    public void onNext(List<News> newsList) {
                        localDataSource.saveNewsList(newsList);
                    }
                    @Override
                    public void onError(Throwable t) {
                        Log.e("NewsRepo", "onError: " + t.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        Flowable<List<News>> tt=localDataSource.getNews();
        return localDataSource.getNews();
    }



    public void clearCache() {
        localDataSource.removeAllRows();
    }
}
