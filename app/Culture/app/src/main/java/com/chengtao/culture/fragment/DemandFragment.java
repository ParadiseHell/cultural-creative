package com.chengtao.culture.fragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chengtao.culture.R;
import com.chengtao.culture.entity.Demand;
import com.chengtao.culture.fragmentimpl.IDemand;
import com.chengtao.culture.presenter.DemandPresenter;
import com.chengtao.culture.view.ListViewDecoration;
import com.chengtao.culture.view.RefreshLayout;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.List;

/**
 * Created by ChengTao on 2017-01-11.
 */

public class DemandFragment extends IFragment implements IDemand,RefreshLayout.OnRefreshListener,OnSwipeMenuItemClickListener {
    //-------------控件
    private RefreshLayout refreshLayout;
    private SwipeMenuRecyclerView recyclerView;
    //----------Presenter
    private DemandPresenter demandPresenter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_demand;
    }

    @Override
    protected void initView() {
        refreshLayout = getView(R.id.rl_demand);
        recyclerView = getView(R.id.rv_demand);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new ListViewDecoration());// 添加分割线。
    }

    @Override
    protected void setListener() {
        refreshLayout.setListener(this);
        recyclerView.setSwipeMenuItemClickListener(this);
    }

    @Override
    protected void initData() {
        demandPresenter.initAdapter();
        demandPresenter.refresh();
    }

    @Override
    protected void initPresenter() {
        demandPresenter = new DemandPresenter(mContext,this);
    }

    @Override
    public void initAdapter(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void refreshFinished() {
        refreshLayout.finishRefreshing();
    }

    @Override
    public void loadMoreFinished() {
        refreshLayout.finishLoadmore();
    }

    @Override
    public void onRefresh() {
        demandPresenter.refresh();
    }

    @Override
    public void onLoadMore() {
        demandPresenter.loadMore();
    }

    @Override
    public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {

    }
}
