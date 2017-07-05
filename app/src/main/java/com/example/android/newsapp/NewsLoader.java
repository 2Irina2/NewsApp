package com.example.android.newsapp;

import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by irina on 29.06.2017.
 */

public class NewsLoader extends android.support.v4.content.AsyncTaskLoader {

    private static final String LOG_TAG = NewsLoader.class.getName();

    private String mUrl;

    public NewsLoader(Context context, String url){
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public Object loadInBackground() {
        if(mUrl == null){
            return null;
        }
        List<News> news = QueryUtils.fetchNewsData(mUrl);
        Log.e(LOG_TAG, "Data has been fetched");
        return news;
    }
}
