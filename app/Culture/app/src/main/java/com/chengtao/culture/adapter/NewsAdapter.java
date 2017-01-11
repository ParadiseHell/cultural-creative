package com.chengtao.culture.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.chengtao.culture.R;
import com.chengtao.culture.entity.News;

import java.util.List;

/**
 * Created by ChengTao on 2016-12-30.
 */

public class NewsAdapter extends IAdapter<NewsViewHolder>{
    private List<News> lists;

    public NewsAdapter(List<News> lists, Context context) {
        super(context);
        this.lists = lists;
    }

    @Override
    public NewsViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new NewsViewHolder(realContentView);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        initClickEvent(holder);
        String title = lists.get(position).getTitle();
        String companyName = lists.get(position).getCompanyName();
        String time = lists.get(position).getUpdateAt().toString();
        String visit = lists.get(position).getVisit()+"";
        if (title != null){
            holder.tvNewsTitle.setText(title);
        }else {
            holder.tvNewsTitle.setText("");
        }
        if (companyName != null){
            holder.tvCompanyName.setText(companyName);
        }else {
            holder.tvCompanyName.setText("");
        }
        if (time != null){
            holder.tvTime.setText(time);
        }else {
            holder.tvTime.setText("");
        }
        holder.tvRead.setText(visit);
    }

    @Override
    int getViewLayoutId() {
        return R.layout.item_news;
    }
}

class NewsViewHolder extends IViewHolder{
    TextView tvNewsTitle;
    TextView tvCompanyName;
    TextView tvRead;
    TextView tvTime;

    NewsViewHolder(View itemView) {
        super(itemView);
        tvNewsTitle = getView(R.id.tv_news_title);
        tvCompanyName = getView(R.id.tv_company_name);
        tvRead = getView(R.id.tv_read);
        tvTime = getView(R.id.tv_time);
    }
}