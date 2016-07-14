package com.example.ink1804.smol_gazeta;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ink1804.smol_gazeta.adapters.ViewPagerAdapter;
import com.example.ink1804.smol_gazeta.fragments.SingleNews_Fragment;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        SingleNews_Fragment.OnCreateFragmentListener{

    private ViewPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    DrawerLayout drawer;
    private RecyclerView recycler_hotNews;
    private HtmlParser parser;
    private String gazetaURL = "http://www.smolgazeta.ru";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//reclame
        createReclameBlock();
        createNavigationDrawer();
        createRecyclerViews();
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
            default:break;
//            case R.id.nav_poster:
//                setViewPagerAdapter(R.array.poster_subs,R.array.poster_keys);
//                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public Context getContext()  {
        return this;
    }

    private void setViewPagerAdapter(int subStrings, int keyStrings){
        String[] subs,keys;
        subs = getResources().getStringArray(subStrings);
        keys = getResources().getStringArray(keyStrings);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout);
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), subs, keys);
        mViewPager.setAdapter(mPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);
        for(int i=0;i<subs.length;i++){
            tabLayout.getTabAt(i).setText(subs[i]);
        }
    }
    private void createNavigationDrawer(){
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("");
        TextView toolbar_textView = (TextView)findViewById(R.id.toolbar_tv);
        Typeface font = Typeface.createFromAsset(getAssets(),"fonts/minion_bold_cond_disp.otf");
        toolbar_textView.setTypeface(font);
        toolbar_textView.setText("СМОЛЕНСКАЯ ГАЗЕТА");

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.theme_main, R.string.theme_poster);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    private void createRecyclerViews(){
        mViewPager = (ViewPager) findViewById(R.id.subMenu_pager);
        setViewPagerAdapter(R.array.main_subs,R.array.main_keys);

        recycler_hotNews = (RecyclerView)findViewById(R.id.recycle_hot_news);
        recycler_hotNews.setLayoutManager(new LinearLayoutManager(this));

        parser = new HtmlParser();
        parser.parseHotNews(gazetaURL,getApplicationContext(),recycler_hotNews);
        //        LinearLayoutManager horizontalLayoutManager
//                = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
//        recycler_hotNews.setLayoutManager(horizontalLayoutManager);
    }
    private void createReclameBlock(){
        ImageView block_one = (ImageView)findViewById(R.id.imView_reclame1);
        ImageView block_two = (ImageView)findViewById(R.id.imView_reclame2);
        block_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ReclameActivity.class);
                intent.putExtra("url","http://fizmat.smolgu.ru");
                startActivity(intent);
            }
        });
        block_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ReclameActivity.class);
                intent.putExtra("url","https://gymnastics.competitions.center");
                startActivity(intent);
            }
        });
    }
}
