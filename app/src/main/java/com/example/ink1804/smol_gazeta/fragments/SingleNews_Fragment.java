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

    private String keysString="";
    private Context context;
    private String gazetaURL = "http://www.smolgazeta.ru";

    public SingleNews_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_recycler_news,container,false);
        HtmlParser parser = new HtmlParser();
        OnCreateFragmentListener listener = (OnCreateFragmentListener)getActivity();


        Bundle args = getArguments();
        if (args != null) {
            RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycleFr);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            context = listener.getContext();
            keysString = args.getString("keysStrings");
            parser.parseNewsBlock(gazetaURL,keysString,context,recyclerView);
        }else{
            RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycleFr);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            context = listener.getContext();
        parser.parseNewsBlock(gazetaURL,"",context,recyclerView);}
        return view;

    }
    public interface OnCreateFragmentListener{
        Context getContext();
    }

    public static SingleNews_Fragment newInstance(String url) {

        Bundle args = new Bundle();

        SingleNews_Fragment fragment = new SingleNews_Fragment();
        args.putString("url",url);
        fragment.setArguments(args);

        return fragment;
    }
}
