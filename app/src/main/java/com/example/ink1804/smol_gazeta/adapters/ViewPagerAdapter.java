package com.example.ink1804.smol_gazeta.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.ink1804.smol_gazeta.fragments.SingleNews_Fragment;

/**
 * Created by Ink1804 on 11.07.16.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    String[] subString;
    String key;

    public ViewPagerAdapter(FragmentManager fm, String key, String[] subString) {
        super(fm);
        this.key = key;
        this.subString = subString;
    }

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        SingleNews_Fragment fragment = new SingleNews_Fragment();
        Bundle args = new Bundle();
        args.putString(key,key);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return subString.length;
    }
}
