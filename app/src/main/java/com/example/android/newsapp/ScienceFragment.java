package com.example.android.newsapp;

import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;



public class ScienceFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<News>>{

    public static String requestUrl = "http://content.guardianapis.com/search?q=science&api-key=test";

    public ScienceFragment(){
        //Required emply construction
    }

    public NewsAdapter newsAdapter = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.news_list, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.listview);
        newsAdapter = new NewsAdapter(getActivity(), new ArrayList<News>());
        listView.setAdapter(newsAdapter);

        if(hasInternetAccess()){

            getLoaderManager().initLoader(NEWS_LOADER_ID, null, ScienceFragment.this);
        }
        else{
            //Do stuff later
        }

        return rootView;

    }

    private static final int NEWS_LOADER_ID = 1;

    @Override
    public android.support.v4.content.Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this, requestUrl);
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<List<News>> loader, List<News> data) {
        newsAdapter.clear();

        if(data != null && !data.isEmpty()){
            newsAdapter.addAll(data);
        }
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
