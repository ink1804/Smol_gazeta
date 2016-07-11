package com.example.ink1804.smol_gazeta.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ink1804.smol_gazeta.FullNewsActivity;
import com.example.ink1804.smol_gazeta.R;
import com.example.ink1804.smol_gazeta.models.NewsItem;

import java.util.List;

/**
 * Created by Ink1804 on 08.07.16.
 */
public class RecycleListOfNewsAdapter extends RecyclerView.Adapter<RecycleListOfNewsAdapter.ItemHolder> {

    private List<NewsItem> mContent;
    Context context;

    public RecycleListOfNewsAdapter(List<NewsItem> mContent, Context context) {
        this.mContent = mContent;
        this.context = context;
    }

    @Override

    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_news_view,parent,false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, final int position) {
        final NewsItem item = mContent.get(position);
        holder.title.setText(item.title);
        holder.category.setText(item.category);
        holder.date.setText(item.date);
        holder.views.setText(item.views);
        holder.info.setText(item.info);
        Glide.with(context).load(item.image).into(holder.icon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FullNewsActivity.class);
                intent.putExtra("URL",item.fullURL);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mContent.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder{
        ImageView icon;
        TextView category,date,views,title,info;
        public ItemHolder(View itemView) {
            super(itemView);
            icon = (ImageView)itemView.findViewById(R.id.card_imageView);
            category = (TextView)itemView.findViewById(R.id.card_tv_category);
            date = (TextView)itemView.findViewById(R.id.card_tv_date);
            views = (TextView)itemView.findViewById(R.id.card_tv_views);
            title = (TextView)itemView.findViewById(R.id.card_tv_title);
            info = (TextView)itemView.findViewById(R.id.card_tv_info);
        }
    }
}
