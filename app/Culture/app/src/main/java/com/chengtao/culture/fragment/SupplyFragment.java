package com.chengtao.culture.fragment;

/**
 * Created by ChengTao on 2016-12-28.
 */

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chengtao.culture.R;
import com.chengtao.culture.fragmentimpl.ISupply;
import com.chengtao.culture.presenter.SupplyPresenter;
import com.chengtao.culture.view.ListViewDecoration;
import com.chengtao.culture.view.RefreshLayout;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

/**
 * 供求界面
 */
public class SupplyFragment extends IFragment implements ISupply,RefreshLayout.OnRefreshListener{
    //-------------控件
    private RefreshLayout refreshLayout;
    private SwipeMenuRecyclerView recyclerView;
    //
    private SupplyPresenter supplyPresenter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_supply_demand;
    }

    @Override
    protected void initView() {
        refreshLayout = getView(R.id.rl_supply);
        recyclerView = getView(R.id.rv_supply);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new ListViewDecoration());// 添加分割线。
    }

    @Override
    protected void setListener() {
        refreshLayout.setListener(this);
    }

    @Override
    protected void initData() {
        supplyPresenter.initAdapter();
        supplyPresenter.refresh();
    }

    @Override
    protected void initPresenter() {
        supplyPresenter = new SupplyPresenter(mContext,this);
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
        supplyPresenter.refresh();
    }

    @Override
    public void onLoadMore() {
        supplyPresenter.loadMore();
    }
}
