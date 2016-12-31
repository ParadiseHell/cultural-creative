package com.chengtao.culture.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ChengTao on 2016-12-31.
 */

class IViewHolder extends RecyclerView.ViewHolder{
    private View itemView;
    IViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }
    /**
     * 获取控件
     * @param id 控件ID
     * @param <T> 泛型
     * @return 控件
     */
    @SuppressWarnings("unchecked")
    <T extends View> T getView(int id){
        return (T) itemView.findViewById(id);
    }
}
