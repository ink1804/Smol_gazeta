package com.example.ink1804.smol_gazeta.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ink1804.smol_gazeta.HtmlParser;
import com.example.ink1804.smol_gazeta.MainActivity;
import com.example.ink1804.smol_gazeta.R;
import com.example.ink1804.smol_gazeta.adapters.RecycleListOfNewsAdapter;
import com.example.ink1804.smol_gazeta.models.NewsItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

/**
 * Created by Ink1804 on 11.07.16.
 */
public class SingleNews_Fragment extends Fragment {

    List<NewsItem> cardNewsList;
    Context context;
    private String gazetaURL = "http://www.smolgazeta.ru";
    String result;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_recycler_news,container,false);
        OnCreateFragmentListener listener = (OnCreateFragmentListener)getActivity();
        context = listener.getContext();
        HtmlParser parser = new HtmlParser();
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycleFr);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        parser.parseNewsBlock(gazetaURL,"",context,recyclerView);
        return view;

    }
    public interface OnCreateFragmentListener{
        Context getContext();
    }
}
/*
RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycleFr);
        OnCreateFragmentListener listener = (OnCreateFragmentListener)getActivity();
        context = listener.getContext();
        Bundle args = new Bundle();
        result = args.getString("html_page");
        String text;
        Document doc = Jsoup.parse(result);
        Element myDiv = doc.select("div.news-list").first();
        text = myDiv.html();
        doc = Jsoup.parse(text);
        for (int i=0;i<4;i++) {
            NewsItem item = new NewsItem();
            item.image =  doc.select("div.block").get(i).select("img").attr("src");
            item.fullURL = doc.select("div.block").get(i).select("a.image").attr("href");
            item.category= doc.select("div.block").get(i).select("div.type").select("a").first().text();
            item.title = doc.select("div.block").get(i).select("div.title").select("div").first().text();
            item.date = doc.select("div.block").get(i).select("span.date").first().text();
            item.info = doc.select("div.block").get(i).select("div").get(4).text();
            item.views= doc.select("div.block").get(i).select("span.views").first().text();
            cardNewsList.add(item);
        }
        RecycleListOfNewsAdapter im = new RecycleListOfNewsAdapter(cardNewsList,context);
        recyclerView.setAdapter(im);
        im.notifyDataSetChanged();
 */