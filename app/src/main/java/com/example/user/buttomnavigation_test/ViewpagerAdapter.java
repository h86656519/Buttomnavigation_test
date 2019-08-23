package com.example.user.buttomnavigation_test;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewpagerAdapter extends FragmentPagerAdapter {

    public ViewpagerAdapter(FragmentManager fm, Context context  ) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Page1Fragment();
            case 1:
                return new Page2Fragment();
            case 2:
                return new Page3Fragment();
            case 3:
                return new Page4Fragment();
            case 4:
                return new MyFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
