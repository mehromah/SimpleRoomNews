package com.example.myroomsamplenews1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.myroomsamplenews1.data.News;

import org.reactivestreams.Subscriber;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {
    public MainViewModel viewModel;
    public NewsAdapter newsAdapter;
    public RecyclerView newsRecyclerView;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new MainViewModel(this);
        newsRecyclerView = findViewById(R.id.rv_home_newsList);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        newsAdapter = new NewsAdapter();
        newsRecyclerView.setAdapter(newsAdapter);


        viewModel.getNewsList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SsSingleObserver<List<News>>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<News> news) {
                        newsAdapter.setNewsList(news);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });


        newsRecyclerView.setNestedScrollingEnabled(false);


    }

    @Override
    public int getCoordinatorLayoutId() {


        return R.id.coordinator_main;

    }


}
