package com.chengtao.culture.fragment;

/**
 * Created by ChengTao on 2016-12-28.
 */

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chengtao.culture.App;
import com.chengtao.culture.R;
import com.chengtao.culture.fragmentimpl.INews;
import com.chengtao.culture.presenter.NewsPresenter;
import com.chengtao.culture.utils.ItemUtils;
import com.chengtao.culture.view.ListViewDecoration;
import com.chengtao.culture.view.RefreshLayout;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

/**
 * 资讯界面
 */
public class NewsFragment extends IFragment implements INews,RefreshLayout.OnRefreshListener,OnSwipeMenuItemClickListener{
    //-------------控件
    private RefreshLayout refreshLayout;
    private SwipeMenuRecyclerView recyclerView;
    //----------Presenter
    private NewsPresenter newsPresenter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView() {
        refreshLayout = getView(R.id.rl_news);
        //---------------
        recyclerView = getView(R.id.rv_news);
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
        newsPresenter.initAdapter();
        newsPresenter.initNews();
    }

    @Override
    protected void initPresenter() {
        newsPresenter = new NewsPresenter(mContext,this);
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
        newsPresenter.refresh();
    }

    @Override
    public void onLoadMore() {
        newsPresenter.loadMore();
    }

    @Override
    public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
        closeable.smoothCloseMenu();
    }
}
