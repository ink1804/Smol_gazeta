package com.example.ink1804.smol_gazeta;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ink1804.smol_gazeta.adapters.RecycleHotNewsAdapter;
import com.example.ink1804.smol_gazeta.adapters.RecycleListOfNewsAdapter;
import com.example.ink1804.smol_gazeta.models.FullNewsItem;
import com.example.ink1804.smol_gazeta.models.HotNewsItem;
import com.example.ink1804.smol_gazeta.models.NewsItem;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ink1804 on 06.07.16.
 */
public class HtmlParser {
    Document doc;
    List<NewsItem> cardNewsList;
    List<HotNewsItem> hotNewsList;
    FullNewsItem item;
    int TTL=0;

    public void parseNewsBlock(final String _url, final String _theme, final Context context, final RecyclerView recyclerView){
        String URL = _url+"/"+_theme;
        cardNewsList = new ArrayList<>();
        if(TTL<10)
            try{
                Ion.with(context)
                        .load(URL)
                        .asString()
                        .setCallback(new FutureCallback<String>() {
                            @Override
                            public void onCompleted(Exception e, String result) {
//                                if(result==null||result==""){
//                                    TTL++;
//                                    parseNewsBlock(_url,_theme,context,recyclerView);
//                                }
                                String text;
                                doc = Jsoup.parse(result);
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
                                TTL=0;
                            }
                        });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public FullNewsItem parseFullNews(final String URL, final Context context, final TextView tv_title, final TextView tv_page, final ImageView image){
        if(TTL<10)
            try {
        Ion.with(context)
                .load(URL)
                .asString()
                .setCallback(new FutureCallback<String>() {
            @Override
            public void onCompleted(Exception e, String result) {
//                if(result==null||result==""){
//                    TTL++;
//                    parseFullNews(URL,context,tv_title,tv_page,image);
//                }
                String imageS,titleS,pageS;
                doc = Jsoup.parse(result);
                imageS = doc.select("div.text-block").first().select("img").attr("src");
                titleS = doc.select("div.text-block").first().select("h1").html();
                doc.select("div.text-block").first().select("div[align]").remove().html();
                doc.select("div.text-block").first().select("h1").remove().html();
                pageS = doc.select("div.text-block").first().html();

                tv_page.setText(Html.fromHtml(pageS).toString());
                tv_title.setText(titleS);
                Glide.with(context).load(imageS).into(image);
                TTL=0;
            }
        });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }
    public void parseHotNews(String URL, final Context context, final RecyclerView recyclerView){
        hotNewsList = new ArrayList<>();
        try {
            Ion.with(context)
                    .load(URL)
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {

                            doc = Jsoup.parse(result);
                            for(int i=0;i<3;i++) {
                                HotNewsItem item = new HotNewsItem();
                                item.title = doc.select("div.important").first().select("div.item").get(i).select("a").html();
                                item.fullNewsLink = doc.select("div.important").first().select("div.item").get(i).select("a").attr("href");
                                hotNewsList.add(item);
                            }
                            RecycleHotNewsAdapter rv = new RecycleHotNewsAdapter(hotNewsList,context);
                            recyclerView.setAdapter(rv);
                            rv.notifyDataSetChanged();
                        }
                    });
        }catch (Exception e){}
    }
}
