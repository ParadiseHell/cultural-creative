package com.chengtao.culture.adapter;

import android.content.Context;
import android.media.tv.TvContentRating;
import android.view.View;
import android.widget.TextView;

import com.chengtao.culture.R;
import com.chengtao.culture.entity.Exhibition;

import java.util.List;

/**
 * Created by ChengTao on 2017-01-08.
 */

public class ExhibitionAdapter extends IAdapter<ExhibitionViewHolder>{
    private List<Exhibition> lists;
    public ExhibitionAdapter(Context context, List<Exhibition> lists) {
        super(context);
        this.lists = lists;
    }

    @Override
    int getViewLayoutId() {
        return R.layout.item_exhibition;
    }


    @Override
    public ExhibitionViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new ExhibitionViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(ExhibitionViewHolder holder, int position) {
        initClickEvent(holder);
        String title = lists.get(position).getTheme();
        String companyName = lists.get(position).getCompanyName();
        String time = lists.get(position).getUpdateAt().toString();
        String visit = lists.get(position).getVisit()+"";
        if (title != null){
            holder.tvTitle.setText(title);
        }else {
            holder.tvTitle.setText("");
        }
        if (companyName != null){
            holder.tvCompany.setText(companyName);
        }else {
            holder.tvCompany.setText("");
        }
        if (time != null){
            holder.tvTime.setText(time);
        }else {
            holder.tvTime.setText("");
        }
        holder.tvVisit.setText(visit);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }
}
class ExhibitionViewHolder extends IViewHolder{
    TextView tvTitle;
    TextView tvCompany;
    TextView tvTime;
    TextView tvVisit;
    ExhibitionViewHolder(View itemView) {
        super(itemView);
        tvTitle = getView(R.id.tv_exhibition_title);
        tvCompany = getView(R.id.tv_company_name);
        tvTime = getView(R.id.tv_time);
        tvVisit = getView(R.id.tv_visit);
    }
}
