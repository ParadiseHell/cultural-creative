package com.chengtao.culture.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.chengtao.culture.R;
import com.chengtao.culture.entity.Demand;

import java.util.List;

/**
 * Created by ChengTao on 2017-01-11.
 */

public class DemandAdapter extends IAdapter<DemandAdapter.DemandViewHolder>{
    private List<Demand> lists;
    public DemandAdapter(Context context,List<Demand> lists) {
        super(context);
        this.lists = lists;
    }

    @Override
    int getViewLayoutId() {
        return R.layout.item_demand;
    }

    @Override
    public DemandViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new DemandViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(DemandViewHolder holder, int position) {
        initClickEvent(holder);
        String title = lists.get(position).getName();
        String user = lists.get(position).getUserName();
        String time = lists.get(position).getUpdateAt().toString();
        String visit = lists.get(position).getVisit()+"";
        if (title != null){
            holder.tvTitle.setText(title);
        }else {
            holder.tvTitle.setText("");
        }
        if (user != null){
            holder.tvUser.setText(user);
        }else {
            holder.tvUser.setText("");
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

    class DemandViewHolder extends IViewHolder {
        TextView tvTitle;
        TextView tvUser;
        TextView tvTime;
        TextView tvVisit;
        DemandViewHolder(View itemView) {
            super(itemView);
            tvTitle = getView(R.id.tv_title);
            tvUser = getView(R.id.tv_user_name);
            tvTime = getView(R.id.tv_time);
            tvVisit = getView(R.id.tv_visit);
        }
    }
}
