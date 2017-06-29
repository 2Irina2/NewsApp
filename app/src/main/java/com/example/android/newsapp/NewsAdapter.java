package com.example.android.newsapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by irina on 28.06.2017.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Activity context, ArrayList<News> news){
        super(context, 0, news);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.news_list_item, parent, false);
        }

        News currentNews = getItem(position);

        TextView articleTitle = (TextView) listItemView.findViewById(R.id.article_title);
        articleTitle.setText(currentNews.getTitle());

        TextView articleSection = (TextView) listItemView.findViewById(R.id.article_section);
        articleSection.setText(currentNews.getSection());

        TextView articleAuthor = (TextView) listItemView.findViewById(R.id.article_author);
        articleAuthor.setText(currentNews.getAuthor());

        TextView articleDate = (TextView) listItemView.findViewById(R.id.article_date);
        articleDate.setText(currentNews.getDate());

        return listItemView;
    }
}



















