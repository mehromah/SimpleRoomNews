package com.example.myroomsamplenews1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.myroomsamplenews1.data.News;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    public  MainViewModel       viewModel;
    public  NewsAdapter         newsAdapter;
    public RecyclerView newsRecyclerView;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new MainViewModel(this);
        newsRecyclerView = findViewById(R.id.rv_home_newsList);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        newsRecyclerView.setNestedScrollingEnabled(false);
        newsAdapter = new NewsAdapter();
        newsRecyclerView.setAdapter(newsAdapter);
        viewModel.getNewsList().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new FlowableSubscriber<List<News>>() {
                @Override
                public void onSubscribe(Subscription s) {
                    s.request(Long.MAX_VALUE);
                    Log.e("MainActivity", "onSubscribe: " + s.toString());
                }

                @Override
                public void onNext(List<News> news) {
                    newsAdapter.setNewsList(news);
                    Log.e("MainActivity", "onNext: " + news.size());
                }

                @Override
                public void onError(Throwable t) {
                    Log.e("MainActivity", "onError: " + t.toString());
                }

                @Override
                public void onComplete() {
                    Log.e("MainActivity", "onComplete");
                }
            });
    }
}