package com.chengtao.culture.fragment;

/**
 * Created by ChengTao on 2016-12-28.
 */

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chengtao.culture.R;
import com.chengtao.culture.fragmentimpl.IExhibition;
import com.chengtao.culture.presenter.ExhibitionPresenter;
import com.chengtao.culture.utils.ItemUtils;
import com.chengtao.culture.view.ListViewDecoration;
import com.chengtao.culture.view.RefreshLayout;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

/**
 * 展会界面
 */
public class ExhibitionFragment extends IFragment implements IExhibition,RefreshLayout.OnRefreshListener,OnSwipeMenuItemClickListener{
    //-------------控件
    private RefreshLayout refreshLayout;
    private SwipeMenuRecyclerView recyclerView;
    //-------------Presenter
    private ExhibitionPresenter exhibitionPresenter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_exhibition;
    }

    @Override
    protected void initView() {
        refreshLayout = getView(R.id.rl_exhibition);
        recyclerView = getView(R.id.rv_exhibition);
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
        exhibitionPresenter.initAdapter();
        exhibitionPresenter.refresh();
    }

    @Override
    protected void initPresenter() {
        exhibitionPresenter = new ExhibitionPresenter(mContext,this);
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
        exhibitionPresenter.refresh();
    }

    @Override
    public void onLoadMore() {
        exhibitionPresenter.loadMore();
    }

    @Override
    public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
        closeable.smoothCloseMenu();
    }
}
