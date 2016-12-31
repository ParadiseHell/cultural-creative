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
    public static final int NONE = 0;
    public static final int DELETE = 1;
    public static final int COLLECT = 2;

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
    public int getItemViewType(int position) {
        return lists.get(position).getOperateType();
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        initClickEvent(holder);
        holder.tvNewsTitle.setText(lists.get(position).getNewsTitle());
        holder.tvCompanyName.setText(lists.get(position).getCompanyName());
        holder.tvRead.setText(lists.get(position).getVisitNum()+"");
        holder.tvTime.setText(lists.get(position).getNewsTime());
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