package com.example.myroomsamplenews1;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.myroomsamplenews1.data.News;

import java.util.List;
import java.util.Observer;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    public MainViewModel viewModel;
    public NewsAdapter newsAdapter;
    public RecyclerView newsRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          newsRecyclerView = findViewById(R.id.rv_home_newsList);

        viewModel = new MainViewModel(this);
       //newsAdapter = new NewsAdapter(news);
        setupViews();
    }


    private void setupViews() {
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        newsRecyclerView.setAdapter(newsAdapter);
        Flowable<List<News>> t = viewModel.getNewsList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<List<News>>() {
                    @Override
                    public void accept(List<News> news) throws Exception {
                        newsAdapter.setNewsList(news);

                    }
                });

        newsRecyclerView.setNestedScrollingEnabled(false);
    }
}
