package com.example.android.reviewsample1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Shereen on 11/14/2017.
 */

public class CustomFragmentPagerAdapter extends FragmentPagerAdapter {
    public CustomFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new SportNewsFragment();
        } else {
            return new AboutFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
