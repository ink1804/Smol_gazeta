package com.example.ink1804.smol_gazeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ink1804.smol_gazeta.models.FullNewsItem;

public class FullNewsActivity extends AppCompatActivity {

    String URL;
    HtmlParser parser = new HtmlParser();
    TextView tv_title,tv_page;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_news);
        Intent intent = getIntent();
        URL = intent.getExtras().getString("URL");
        parser = new HtmlParser();
        tv_page = (TextView)findViewById(R.id.full_tv_page);
        tv_title = (TextView)findViewById(R.id.full_tv_title);
        image = (ImageView)findViewById(R.id.full_image);
        parser.parseFullNews(URL,getApplicationContext(),tv_title,tv_page,image);



    }
}
