package com.example.ink1804.smol_gazeta;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.ink1804.smol_gazeta.adapters.ViewPagerAdapter;
import com.example.ink1804.smol_gazeta.fragments.SingleNews_Fragment;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        SingleNews_Fragment.OnCreateFragmentListener{

    String[] subStrings,keyStrings;
    private ViewPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;

    private RecyclerView recycler_hotNews;
    private HtmlParser parser;
    private String gazetaURL = "http://www.smolgazeta.ru";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mViewPager = (ViewPager) findViewById(R.id.subMenu_pager);
        setViewPagerAdapter(R.array.main_subs,R.array.main_keys);

        recycler_hotNews = (RecyclerView)findViewById(R.id.recycle_hot_news);
        recycler_hotNews.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recycler_hotNews.setLayoutManager(horizontalLayoutManager);

        parser = new HtmlParser();
        parser.parseHotNews(gazetaURL,getApplicationContext(),recycler_hotNews);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.nav_main:
                setViewPagerAdapter(R.array.main_subs,R.array.main_keys);
                break;
            case R.id.nav_society:
                setViewPagerAdapter(R.array.society_subs,R.array.society_keys);
                break;
            case R.id.nav_habitat:
                setViewPagerAdapter(R.array.habitat_subs,R.array.habitat_keys);
                break;
            case R.id.nav_roads:
                setViewPagerAdapter(R.array.roads_subs,R.array.roads_keys);
                break;
            case R.id.nav_security:
                setViewPagerAdapter(R.array.security_subs,R.array.security_keys);
                break;
            case R.id.nav_accidents:
                setViewPagerAdapter(R.array.accidents_subs,R.array.accidents_keys);
                break;
            case R.id.nav_documents:
                setViewPagerAdapter(R.array.documents_subs,R.array.documents_keys);
                break;
//            case R.id.nav_poster:
//                setViewPagerAdapter(R.array.poster_subs,R.array.poster_keys);
//                break;
        }
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.main_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public Context getContext()  {
        return this;
    }
    private void setViewPagerAdapter(int subStrings,int keyStrings){
        String[] subs,keys;
        subs = getResources().getStringArray(subStrings);
        keys = getResources().getStringArray(keyStrings);
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), subs, keys);
        mViewPager.setAdapter(mPagerAdapter);
    }

}
