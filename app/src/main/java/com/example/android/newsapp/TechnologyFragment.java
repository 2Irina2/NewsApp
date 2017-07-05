package com.example.android.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;



public class TechnologyFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<News>>{

    public static String requestUrl = "http://content.guardianapis.com/search?section=technology&api-key=test";

    private ProgressBar progressBar = null;
    private TextView emptyTextView = null;

    public TechnologyFragment(){
        //Required emply construction
    }

    public NewsAdapter newsAdapter = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.news_list, container, false);

        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        emptyTextView = (TextView) rootView.findViewById(R.id.empty_text);

        ListView listView = (ListView) rootView.findViewById(R.id.listview);
        newsAdapter = new NewsAdapter(getActivity(), new ArrayList<News>());
        listView.setAdapter(newsAdapter);
        listView.setEmptyView(emptyTextView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News currentNews = newsAdapter.getItem(position);

                Uri newsUri = Uri.parse(currentNews.getUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(websiteIntent);

            }
        });

        if(hasInternetAccess()){

            getLoaderManager().initLoader(NEWS_LOADER_ID, null, TechnologyFragment.this);

        }
        else{
            progressBar.setVisibility(View.GONE);
            emptyTextView.setText("No internet connection");
        }

        return rootView;

    }

    private static final int NEWS_LOADER_ID = 1;

    @Override
    public android.support.v4.content.Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(getActivity(), requestUrl);
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<List<News>> loader, List<News> data) {
        newsAdapter.clear();

        if(data != null && !data.isEmpty()){
            newsAdapter.addAll(data);
        }
        emptyTextView.setText("No news found");

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<List<News>> loader) {
        newsAdapter.clear();
    }

    public boolean hasInternetAccess(){
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }
}
