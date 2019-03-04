package com.example.myroomsamplenews1.data;

import android.content.Context;
import android.util.Log;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.schedulers.Schedulers;


public class NewsRepository implements NewsDataSource {
    private static final String TAG = "NewsRepository";

    private CloudDataSource      cloudDataSource;
    private LocalDataSource      localDataSource;
    private Flowable<List<News>> news;

    public NewsRepository(Context context) {
        localDataSource = AppDatabase.getInstance(context).getLocalDataSource();
        cloudDataSource = new EnglishCloudDataSource();
    }

    @Override
    public Flowable<List<News>> getNews() {


        cloudDataSource.getNews()
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.newThread())
            .subscribe(new FlowableSubscriber<List<News>>() {
                @Override
                public void onSubscribe(Subscription s) {
                    s.request(Long.MAX_VALUE);
                    Log.e(TAG, "onSubscribe: " + s.toString());
                }

                @Override
                public void onNext(List<News> newsList) {
                    localDataSource.saveNewsList(newsList);
                    Log.e(TAG, "onNext: " + newsList.size());
                }

                @Override
                public void onError(Throwable t) {
                    Log.e(TAG, "onError: " + t.toString());
                }

                @Override
                public void onComplete() {
                    news = localDataSource.getNews();
                    Log.e(TAG, "onComplete");
                }
            });
        news = localDataSource.getNews();
        Log.e(TAG, "getNews: before return news");
        return news;
    }


    public void clearCache() {
        localDataSource.removeAllRows();
    }
}
