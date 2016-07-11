package com.example.ink1804.smol_gazeta.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ink1804.smol_gazeta.FullNewsActivity;
import com.example.ink1804.smol_gazeta.R;
import com.example.ink1804.smol_gazeta.models.HotNewsItem;
import com.example.ink1804.smol_gazeta.models.NewsItem;

import java.util.List;

/**
 * Created by Ink1804 on 09.07.16.
 */
public class RecycleHotNewsAdapter extends RecyclerView.Adapter<RecycleHotNewsAdapter.ItemHolder> {

    private List<HotNewsItem> mContent;
    Context context;

    public RecycleHotNewsAdapter(List<HotNewsItem> mContent, Context context) {
        this.mContent = mContent;
        this.context = context;
    }
    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hot_news_view,parent,false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        final HotNewsItem item = mContent.get(position);
        holder.textView_hot.setText(item.title);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FullNewsActivity.class);
                intent.putExtra("URL",item.fullNewsLink);
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
        TextView textView_hot;
        public ItemHolder(View itemView) {
            super(itemView);
            textView_hot = (TextView)itemView.findViewById(R.id.hot_tv);
        }
    }
}
