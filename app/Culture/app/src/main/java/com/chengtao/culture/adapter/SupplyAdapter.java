package com.chengtao.culture.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.chengtao.culture.R;
import com.chengtao.culture.entity.Supply;

import java.util.List;

/**
 * Created by ChengTao on 2017-01-11.
 */

public class SupplyAdapter extends IAdapter<SupplyAdapter.SupplyViewHolder>{
    private List<Supply> lists;
    public SupplyAdapter(Context context,List<Supply> lists) {
        super(context);
        this.lists = lists;
    }

    @Override
    int getViewLayoutId() {
        return R.layout.item_supply;
    }

    @Override
    public SupplyViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new SupplyViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(SupplyViewHolder holder, int position) {
        initClickEvent(holder);
        String title = lists.get(position).getName();
        String user = lists.get(position).getCompanyName();
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

    class SupplyViewHolder extends IViewHolder {
        TextView tvTitle;
        TextView tvUser;
        TextView tvTime;
        TextView tvVisit;

        SupplyViewHolder(View itemView) {
            super(itemView);
            tvTitle = getView(R.id.tv_title);
            tvUser = getView(R.id.tv_user_name);
            tvTime = getView(R.id.tv_time);
            tvVisit = getView(R.id.tv_visit);
        }
    }
}
