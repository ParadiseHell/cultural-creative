package com.chengtao.culture.adapter;

import android.content.Context;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

/**
 * Created by ChengTao on 2016-12-30.
 */

public abstract class IAdapter<T extends RecyclerView.ViewHolder> extends MAdapter<T>{
    private Context mContext;
    private OnRecycleItemClickListener onRecycleItemClickListener;
    private OnRecycleItemLongClickListener onRecycleItemLongClickListener;
    IAdapter(Context context) {
        mContext = context;
    }
    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return getView(parent);
    }
    /**
     * 获取Item布局ID
     * @return Item布局ID
     */
    abstract int getViewLayoutId();

    /**
     * 获取布局View
     * @param parent ViewGroup
     * @return 布局View
     */
    private View getView(ViewGroup parent){
        return LayoutInflater.from(mContext).inflate(getViewLayoutId(),parent,false);
    }

    /**
     * 获取上下文
     * @return 上下文
     */
    public Context getmContext() {
        return mContext;
    }

    /**
     * Item点击接口
     */
    public interface OnRecycleItemClickListener{
        void onClick(View view,int position);
    }

    /**
     * Item长点击接口
     */
    public interface OnRecycleItemLongClickListener{
        void onLongClick(View view,int position);
    }

    public void setOnRecycleItemClickListener(OnRecycleItemClickListener onRecycleItemClickListener) {
        this.onRecycleItemClickListener = onRecycleItemClickListener;
    }

    public void setOnRecycleItemLongClickListener(OnRecycleItemLongClickListener onRecycleItemLongClickListener) {
        this.onRecycleItemLongClickListener = onRecycleItemLongClickListener;
    }

    /**
     * 初始化点击事件
     * @param hodler ViewHolder
     */
    void initClickEvent(final T hodler){
        if (onRecycleItemClickListener != null){
            hodler.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRecycleItemClickListener.onClick(view,hodler.getLayoutPosition());
                }
            });
        }
        if (onRecycleItemLongClickListener != null){
            hodler.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onRecycleItemLongClickListener.onLongClick(view,hodler.getLayoutPosition());
                    return false;
                }
            });
        }
    }
}

abstract class MAdapter<T extends RecyclerView.ViewHolder> extends SwipeMenuAdapter<T>{

}