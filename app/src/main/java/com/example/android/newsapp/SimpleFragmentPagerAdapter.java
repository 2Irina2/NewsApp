package com.example.android.newsapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by irina on 28.06.2017.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ScienceFragment();
        } else if (position == 1){
            return new MusicFragment();
        } else if (position == 2){
            return new PoliticsFragment();
        } else{
            return new TechnologyFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    public CharSequence getPageTitle(int position){
        switch(position){
            case 0:
                return mContext.getString(R.string.category_science);
            case 1:
                return mContext.getString(R.string.category_music);
            case 2:
                return mContext.getString(R.string.category_politics);
            case 3:
                return mContext.getString(R.string.category_technology);
            default:
                return null;
        }
    }
}
