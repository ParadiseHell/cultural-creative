package com.chengtao.culture.presenter;

import android.content.Context;

import com.chengtao.culture.adapter.DemandAdapter;
import com.chengtao.culture.entity.Demand;
import com.chengtao.culture.entity.ListInfo;
import com.chengtao.culture.fragmentimpl.IDemand;
import com.chengtao.culture.request.DemandRequest;
import com.chengtao.culture.response.IResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChengTao on 2017-01-11.
 */

public class DemandPresenter extends IPresenter{
    private IDemand iDemand;
    private List<Demand> lists;
    private DemandAdapter adapter;
    //-------------请求
    private DemandRequest demandRequestRefresh;
    private final static int REFRESH = 1;
    private DemandRequest demandRequestLoadMore;
    private final static int LOAD_MORE = 2;
    private int currentPage = 1;
    private boolean isFinish = false;
    public DemandPresenter(Context context,IDemand iDemand) {
        super(context);
        this.iDemand = iDemand;
        lists = new ArrayList<>();
    }

    @Override
    protected void onIRequestSuccess(int requestId, IResponse response) {
        switch (requestId){
            case REFRESH:
                if (response.state()){
                    ArrayList<Demand> list = response.getDataList();
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
                iDemand.refreshFinished();
                break;
            case LOAD_MORE:
                if (response.state()){
                    ArrayList<Demand> list = response.getDataList();
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
                iDemand.loadMoreFinished();
                break;
        }
    }

    @Override
    protected void onIRequestFail(int requestId, Throwable throwable) {
        iDemand.tip(throwable.getMessage());
        switch (requestId){
            case REFRESH:
                iDemand.refreshFinished();
                break;
            case LOAD_MORE:
                iDemand.loadMoreFinished();
                break;
        }
    }

    public void initAdapter(){
        adapter = new DemandAdapter(getContext(),lists);
        iDemand.initAdapter(adapter);
    }

    public void refresh(){
        currentPage = 1;
        isFinish = false;
        demandRequestRefresh = new DemandRequest(getContext(),new DemandRequest.DemandParam(currentPage));
        demandRequestRefresh.setRequestId(REFRESH);
        executeRequest(demandRequestRefresh);
    }

    public void loadMore(){
        if (isFinish){
            iDemand.tip("没有更多了");
            iDemand.loadMoreFinished();
        }else {
            currentPage++;
            demandRequestLoadMore = new DemandRequest(getContext(),new DemandRequest.DemandParam(currentPage));
            demandRequestLoadMore.setRequestId(LOAD_MORE);
            executeRequest(demandRequestLoadMore);
        }

    }
}
