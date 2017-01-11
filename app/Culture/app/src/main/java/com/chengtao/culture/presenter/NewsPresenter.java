package com.chengtao.culture.presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.chengtao.culture.adapter.IAdapter;
import com.chengtao.culture.adapter.NewsAdapter;
import com.chengtao.culture.entity.ListInfo;
import com.chengtao.culture.entity.News;
import com.chengtao.culture.fragmentimpl.INews;
import com.chengtao.culture.request.NewsRequest;
import com.chengtao.culture.response.IResponse;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChengTao on 2016-12-31.
 */

@SuppressWarnings("FieldCanBeLocal")
public class NewsPresenter extends IPresenter implements IAdapter.OnRecycleItemClickListener{
    private List<News> lists;
    private NewsAdapter adapter;
    private INews iNews;
    //------------请求
    private NewsRequest newsRequestRefresh;
    private final static int NEWS_REQUEST_REFRESH = 1;
    private NewsRequest newsRequestLoadMore;
    private final static int NEWS_REQUEST_LOAD_MORE  = 2;
    private int currentPage = 1;
    private boolean isFinish = false;
    public NewsPresenter(Context context,INews iNews) {
        super(context);
        lists = new ArrayList<>();
        this.iNews = iNews;
    }

    @SuppressWarnings({"unchecked", "UnusedAssignment"})
    @Override
    protected void onIRequestSuccess(int requestId, IResponse response) {
        Log.e("TAG","onIRequestSuccess:"+requestId);
        switch (requestId){
            case NEWS_REQUEST_REFRESH:
                if (response.state()){
                    List<News> refreshList = new ArrayList<>();
                    ListInfo refreshInfo = new ListInfo();
                    refreshInfo = (ListInfo) response.getData();
                    refreshList = response.getDataList();
                    if (refreshList != null){
                        lists.clear();
                        lists.addAll(refreshList);
                        adapter.notifyDataSetChanged();
                    }
                    if (refreshInfo != null){
                        if (refreshInfo.getCurrentCount() >= refreshInfo.getTotalCount()){
                            isFinish = true;
                        }
                    }
                }
                iNews.refreshFinished();
                break;
            case NEWS_REQUEST_LOAD_MORE:
                if (response.state()){
                    List<News> loadMoreList = new ArrayList<>();
                    ListInfo loadMoreInfo = (ListInfo) response.getData();
                    loadMoreList = response.getDataList();
                    if (loadMoreList != null){
                        lists.clear();
                        lists.addAll(loadMoreList);
                        adapter.notifyDataSetChanged();
                    }
                    if (loadMoreInfo != null){
                        if (loadMoreInfo.getCurrentCount() >= loadMoreInfo.getTotalCount()){
                            isFinish = true;
                        }
                    }
                }
                iNews.loadMoreFinished();
                break;
        }
    }

    @Override
    protected void onIRequestFail(int requestId, Throwable throwable) {
        Log.e("TAG","onIRequestFail:"+requestId);
        switch (requestId){
            case NEWS_REQUEST_REFRESH:
                iNews.tip(throwable.getMessage());
                iNews.refreshFinished();
                break;
            case NEWS_REQUEST_LOAD_MORE:
                iNews.tip(throwable.getMessage());
                iNews.loadMoreFinished();
                break;
        }
    }

    public void initAdapter(){
        adapter = new NewsAdapter(lists,getContext());
        iNews.initAdapter(adapter);
        //设置监听
        adapter.setOnRecycleItemClickListener(this);
    }

    public void initNews(){
        newsRequestRefresh = new NewsRequest(getContext(), new NewsRequest.NewsParam(currentPage));
        newsRequestRefresh.setRequestId(NEWS_REQUEST_REFRESH);
        executeRequest(newsRequestRefresh);
    }

    public void refresh(){
        currentPage = 1;
        isFinish = false;
        newsRequestRefresh = new NewsRequest(getContext(), new NewsRequest.NewsParam(currentPage));
        newsRequestRefresh.setRequestId(NEWS_REQUEST_REFRESH);
        executeRequest(newsRequestRefresh);
    }

    public void loadMore(){
        if (isFinish){
            iNews.tip("没有更多了");
            iNews.loadMoreFinished();
        }else {
            currentPage++;
            newsRequestLoadMore = new NewsRequest(getContext(),new NewsRequest.NewsParam(currentPage));
            newsRequestLoadMore.setRequestId(NEWS_REQUEST_LOAD_MORE);
            executeRequest(newsRequestLoadMore);
        }
    }

    @Override
    public void onClick(View view, int position) {

    }
}
