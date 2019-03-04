package com.example.myroomsamplenews1.data;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;


import io.reactivex.Flowable;


@Dao
public abstract class LocalDataSource implements NewsDataSource {

    @Query("SELECT * FROM tbl_news")
    @Override
    public abstract Flowable<List<News>> getNews();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract void saveNewsList(List<News> newsList);

    @Query("DELETE FROM tbl_news")
    public abstract void removeAllRows();
}
