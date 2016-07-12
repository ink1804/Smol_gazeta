package com.example.ink1804.smol_gazeta;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ink1804.smol_gazeta.adapters.ViewPagerAdapter;
import com.example.ink1804.smol_gazeta.fragments.SingleNews_Fragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.ArrayList;


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

//        SingleNews_Fragment.newInstance();

       // recycler_cardNews = (RecyclerView)findViewById(R.id.recycle_list_of_news);
        recycler_hotNews = (RecyclerView)findViewById(R.id.recycle_hot_news);
     //   recycler_cardNews.setLayoutManager(new LinearLayoutManager(this));
        recycler_hotNews.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recycler_hotNews.setLayoutManager(horizontalLayoutManager);

        parser = new HtmlParser();
        //parser.parseNewsBlock(gazetaURL,"",getApplicationContext(),recycler_cardNews);
        parser.parseHotNews(gazetaURL,getApplicationContext(),recycler_hotNews);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        //asd
//        TextView textView = (TextView)findViewById(R.id.subMenu_pager);
        switch (id){
            case R.id.nav_society:
                subStrings = getResources().getStringArray(R.array.society_subs);
                keyStrings = getResources().getStringArray(R.array.society_keys);

                mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), subStrings, keyStrings);
                mViewPager.setAdapter(mPagerAdapter);
                mViewPager.getAdapter().notifyDataSetChanged();
                break;
            case R.id.nav_habitat:
                subStrings = getResources().getStringArray(R.array.habitat_subs);
                keyStrings = getResources().getStringArray(R.array.habitat_keys);
                mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), subStrings, keyStrings);
                mViewPager.setAdapter(mPagerAdapter);


                //mViewPager.setAdapter(mPagerAdapter);
                break;
            case R.id.nav_roads:break;
            case R.id.nav_security:break;
            case R.id.nav_accidents:break;
            case R.id.nav_documents:break;
            case R.id.nav_poster:break;
        }
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.main_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public Context getContext()  {
        return this;
    }
    private void setViewPagerAdapter(String[] subStrings,String[] keyStrings){
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), subStrings, keyStrings);
        //mViewPager.adapter
        mViewPager.setAdapter(mPagerAdapter);
    }
}
