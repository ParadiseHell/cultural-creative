package com.chengtao.culture.presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.chengtao.culture.adapter.IAdapter;
import com.chengtao.culture.adapter.NewsAdapter;
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
    private NewsRequest newsRequest = null;
    private final static int NEWS_REQUEST = 1;
    private NewsRequest newsRequestRefresh;
    private final static int NEWS_REQUEST_REFRESH = 2;
    private NewsRequest newsRequestLoadMore;
    private final static int NEWS_REQUEST_LOAD_MORE  = 3;
    private final static int REFRESH = 0;
    private final static int LOAD_MORE = 1;
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
            case NEWS_REQUEST:
                if (response.state()){
                    List<News> list = response.getDataList();
                    lists.addAll(list);
                    Log.e("TAG","list:"+lists.toString());
                    adapter.notifyDataSetChanged();
                }
                iNews.refreshFinished();
                break;
            case NEWS_REQUEST_REFRESH:
                if (response.state()){
                    List<News> refreshList = new ArrayList<>();
                    refreshList = response.getDataList();
                    if (refreshList != null){
                        lists.addAll(0,refreshList);
                        Log.e("TAG","list:"+lists.toString());
                        adapter.notifyDataSetChanged();
                    }
                }
                iNews.refreshFinished();
                break;
            case NEWS_REQUEST_LOAD_MORE:
                if (response.state()){
                    List<News> loadMoreList = new ArrayList<>();
                    loadMoreList = response.getDataList();
                    if (loadMoreList != null){
                        lists.addAll(0,loadMoreList);
                        Log.e("TAG","list:"+lists.toString());
                        adapter.notifyDataSetChanged();
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
            case NEWS_REQUEST:
                iNews.tip(throwable.getMessage());
                break;
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
        adapter.setOnRecycleItemClickListener(this);
    }

    public void initNews(){
        newsRequest = new NewsRequest(getContext(),new NewsRequest.NewsParam(0,REFRESH));
        newsRequest.setRequestId(NEWS_REQUEST);
        executeRequest(newsRequest);
    }

    public void refresh(){
        try {
            newsRequestRefresh = new NewsRequest(getContext(),new NewsRequest.NewsParam(lists.get(0).getNewsTime(),REFRESH));
        }catch (Exception e){
            newsRequestRefresh = new NewsRequest(getContext(),new NewsRequest.NewsParam(0,REFRESH));
        }
        newsRequestRefresh.setRequestId(NEWS_REQUEST_REFRESH);
        executeRequest(newsRequestRefresh);
    }

    public void loadMore(){
        try {
            newsRequestLoadMore = new NewsRequest(getContext(),new NewsRequest.NewsParam(lists.get(lists.size() - 1).getNewsTime(),LOAD_MORE));
        }catch (Exception e){
            newsRequestLoadMore = new NewsRequest(getContext(),new NewsRequest.NewsParam(0,LOAD_MORE));
        }
        newsRequestLoadMore.setRequestId(NEWS_REQUEST_LOAD_MORE);
        executeRequest(newsRequestLoadMore);
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
    @SuppressWarnings("UnusedParameters")
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
