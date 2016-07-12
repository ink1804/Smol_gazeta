package com.example.ink1804.smol_gazeta.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.example.ink1804.smol_gazeta.fragments.SingleNews_Fragment;

/**
 * Created by Ink1804 on 11.07.16.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private String[] subStrings;
    private String[] keyStrings;

    public ViewPagerAdapter(FragmentManager fm, String[] subStrings, String[] keyStrings) {
        super(fm);
        this.subStrings = subStrings;
        this.keyStrings = keyStrings;
    }

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        SingleNews_Fragment fragment = new SingleNews_Fragment();
        Bundle args = new Bundle();
        args.putString("subStrings",subStrings[position]);
        args.putString("keysStrings",keyStrings[position]);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return subStrings.length;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
