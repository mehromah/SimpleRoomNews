package com.example.myroomsamplenews1;

import android.content.Intent;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myroomsamplenews1.data.News;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saeed Shahini on 4/4/2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<News> newsList = new ArrayList<>();
    private int pendingNewsPosition;

    public NewsAdapter() {

    }

    public NewsAdapter(List<News> newsList) {
        this.newsList = newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
        notifyDataSetChanged();
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.bindNews(newsList.get(position));
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private ImageView newsImageView;
        private TextView titleTextView;
        private TextView dateTextView;
        private View videoIndicator;

        public NewsViewHolder(View itemView) {
            super(itemView);
            newsImageView = itemView.findViewById(R.id.iv_news_image);
            videoIndicator = itemView.findViewById(R.id.iv_news_videoIndicator);
            titleTextView = itemView.findViewById(R.id.tv_news_title);
            dateTextView = itemView.findViewById(R.id.tv_news_date);
        }

        public void bindNews(final News news) {
            Picasso.get().load(news.getImage()).into(newsImageView);

            titleTextView.setText(news.getTitle());
            dateTextView.setText(news.getDate());

        }
    }




}
