package com.example.ink1804.smol_gazeta;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
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

import com.example.ink1804.smol_gazeta.fragments.SingleNews_Fragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        SingleNews_Fragment.OnCreateFragmentListener{

    private BottomBar mBottomBar;
    private CoordinatorLayout coordinatorLayout;
    DrawerLayout drawer;
    private RecyclerView recycler_cardNews,recycler_hotNews;
    private HtmlParser parser;
    private String gazetaURL = "http://www.smolgazeta.ru";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        FragmentManager ft = getSupportFragmentManager();
        drawer = (DrawerLayout)findViewById(R.id.main_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
    private void bottomBarPreferences(){
       // mBottomBar = BottomBar.attach(this, savedInstanceState);
        //coordinatorLayout = (CoordinatorLayout) findViewById(R.id.three_buttons_activity);

        mBottomBar.setItems(R.menu.bottombar_menu);
        mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.purple));
        mBottomBar.mapColorForTab(1, ContextCompat.getColor(this, R.color.blue_dark));
        mBottomBar.mapColorForTab(2, ContextCompat.getColor(this, R.color.blue_light));
        mBottomBar.mapColorForTab(3, ContextCompat.getColor(this, R.color.green));
        mBottomBar.mapColorForTab(4, ContextCompat.getColor(this, R.color.yellow));
        mBottomBar.mapColorForTab(5, ContextCompat.getColor(this, R.color.orange));
        mBottomBar.mapColorForTab(6, ContextCompat.getColor(this, R.color.red));
        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                switch (menuItemId){
                    case R.id.nav_society:

                        break;
                    case R.id.nav_habitat: break;
                    case R.id.nav_roads: break;
                    case R.id.nav_security: break;
                    case R.id.nav_accidents: break;
                    case R.id.nav_documents: break;
                    case R.id.nav_poster: break;
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        //asd
//        TextView textView = (TextView)findViewById(R.id.subMenu_pager);
        switch (id){
            case R.id.nav_society:
            //    textView.setText("Общество");
                break;
            case R.id.nav_habitat:
//                textView.setText("Среда обитания");
                break;
            case R.id.nav_roads:
//                textView.setText("Дороги");
                break;
            case R.id.nav_security:
//                textView.setText("Безопасность");
                break;
            case R.id.nav_accidents:break;
            case R.id.nav_documents:break;
            case R.id.nav_poster:break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public Context getContext()  {
        return this;
    }
}
