package com.chengtao.culture.presenter;

import android.content.Context;
import android.view.View;
import com.chengtao.culture.adapter.ExhibitionAdapter;
import com.chengtao.culture.adapter.IAdapter;
import com.chengtao.culture.entity.Exhibition;
import com.chengtao.culture.entity.ListInfo;
import com.chengtao.culture.fragmentimpl.IExhibition;
import com.chengtao.culture.request.ExhibitionRequest;
import com.chengtao.culture.response.IResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChengTao on 2017-01-07.
 */

/**
 * 展会请求
 */
@SuppressWarnings("ALL")
public class ExhibitionPresenter extends IPresenter implements IAdapter.OnRecycleItemClickListener{
    private IExhibition iExhibition;
    private List<Exhibition> lists;
    private ExhibitionAdapter adapter;
    //-------------请求
    private ExhibitionRequest exhibitionRequestRefresh;
    private final static int EXHIBITION_REQUEST_REFRESH = 1;
    private ExhibitionRequest exhibitionRequestLoadMore;
    private final static int EXHIBITION_REQUEST_LOADMORE = 2;
    private int currentPage = 1;
    private boolean isFinish = false;
    public ExhibitionPresenter(Context context, IExhibition iExhibition) {
        super(context);
        this.iExhibition = iExhibition;
        lists = new ArrayList<>();
    }

    @Override
    protected void onIRequestSuccess(int requestId, IResponse response) {
        switch (requestId){
            case EXHIBITION_REQUEST_REFRESH:
                if (response.state()){
                    ArrayList<Exhibition> list = response.getDataList();
                    ListInfo info = (ListInfo) response.getData();
                    if (list != null){
                        lists.clear();
                        lists.addAll(list);
                        adapter.notifyDataSetChanged();
                    }
                    if (info.getTotalCount() >= info.getCurrentCount()){
                        isFinish = true;
                    }
                }
                iExhibition.refreshFinished();
                break;
            case EXHIBITION_REQUEST_LOADMORE:
                if (response.state()){
                    ArrayList<Exhibition> list = response.getDataList();
                    ListInfo info = (ListInfo) response.getData();
                    if (list != null){
                        lists.clear();
                        lists.addAll(list);
                        adapter.notifyDataSetChanged();
                    }
                    if (info.getTotalCount() >= info.getCurrentCount()){
                        isFinish = true;
                    }
                }
                iExhibition.loadMoreFinished();
                break;
        }
    }

    @Override
    protected void onIRequestFail(int requestId, Throwable throwable) {
        iExhibition.tip(throwable.getMessage());
        switch (requestId){
            case EXHIBITION_REQUEST_REFRESH:
                iExhibition.refreshFinished();
                break;
            case EXHIBITION_REQUEST_LOADMORE:
                iExhibition.loadMoreFinished();
                break;
        }
    }

    public void initAdapter(){
        adapter = new ExhibitionAdapter(getContext(),lists);
        iExhibition.initAdapter(adapter);
        adapter.setOnRecycleItemClickListener(this);
    }

    public void refresh(){
        currentPage = 1;
        isFinish = false;
        exhibitionRequestRefresh = new ExhibitionRequest(getContext(),new ExhibitionRequest.ExhibitonParam(currentPage));
        exhibitionRequestRefresh.setRequestId(EXHIBITION_REQUEST_REFRESH);
        executeRequest(exhibitionRequestRefresh);
    }

    public void loadMore(){
        if (isFinish){
            iExhibition.tip("没有更多了");
            iExhibition.loadMoreFinished();
        }else {
            currentPage++;
            exhibitionRequestLoadMore = new ExhibitionRequest(getContext(),new ExhibitionRequest.ExhibitonParam(currentPage));
            exhibitionRequestLoadMore.setRequestId(EXHIBITION_REQUEST_LOADMORE);
            executeRequest(exhibitionRequestLoadMore);
        }

    }

    @Override
    public void onClick(View view, int position) {

    }
}
