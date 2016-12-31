package com.chengtao.culture.presenter;

import android.content.Context;
import android.view.View;

import com.chengtao.culture.adapter.IAdapter;
import com.chengtao.culture.adapter.NewsAdapter;
import com.chengtao.culture.entity.News;
import com.chengtao.culture.fragmentimpl.INews;
import com.chengtao.culture.response.IResponse;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChengTao on 2016-12-31.
 */

public class NewsPresenter extends IPresenter implements IAdapter.OnRecycleItemClickListener{
    private List<News> lists;
    private NewsAdapter adapter;
    private INews iNews;
    public NewsPresenter(Context context,INews iNews) {
        super(context);
        lists = new ArrayList<>();
        this.iNews = iNews;
    }

    @Override
    protected void onIRequestSuccess(int requestId, IResponse response) {

    }

    @Override
    protected void onIRequestFail(int requestId, Throwable throwable) {

    }

    public void initData(){
        for (int i = 1; i <= 10; i++){
            News news = new News();
            news.setNewsTitle("标题"+i);
            news.setCompanyName("企业"+i);
            news.setVisitNum(i);
            news.setNewsTime("2016-12-31");
            if (i %2 == 0){
                news.setOperateType(NewsAdapter.DELETE);
            }else {
                if (i < 5){
                    news.setOperateType(NewsAdapter.NONE);
                }else {
                    news.setOperateType(NewsAdapter.COLLECT);
                }
            }
            lists.add(news);
        }
        adapter = new NewsAdapter(lists,getContext());
        iNews.initAdapter(adapter);
        adapter.setOnRecycleItemClickListener(this);
    }

    public void refresh(){
        News news = new News();
        news.setNewsTitle("标题刷新");
        news.setCompanyName("企业刷新");
        news.setVisitNum(100);
        news.setNewsTime("2017-01-01");
        news.setOperateType(NewsAdapter.COLLECT);
        lists.add(0,news);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
                iNews.refreshFinished();
            }
        },2000);
    }

    public void loadMore(){
        News news = new News();
        news.setNewsTitle("标题加载");
        news.setCompanyName("企业加载");
        news.setVisitNum(200);
        news.setNewsTime("2017-01-01");
        news.setOperateType(NewsAdapter.DELETE);
        lists.add(news);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
                iNews.loadMoreFinished();
            }
        },2000);
    }

    @Override
    public void onClick(View view, int position) {

    }

    /**
     * 处理Item滑动
     * @param adapterPosition 列表位子
     * @param menuPosition 滑动菜单位置
     * @param direction 方向
     */
    public void handlerItem(int adapterPosition, int menuPosition, int direction){
        if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION){
            switch (lists.get(adapterPosition).getOperateType()){
                case NewsAdapter.DELETE:
                    deleteItem(adapterPosition);
                    break;
                case NewsAdapter.COLLECT:
                    collectItem(adapterPosition);
                    break;
            }
        }
    }
    private void deleteItem(int position){
        lists.remove(position);
        adapter.notifyItemRemoved(position);
    }

    private void collectItem(int position){
        iNews.tip("收藏成功:"+position);
    }
}
