package com.chengtao.culture.presenter;

import android.content.Context;

import com.chengtao.culture.adapter.DemandAdapter;
import com.chengtao.culture.adapter.SupplyAdapter;
import com.chengtao.culture.entity.Demand;
import com.chengtao.culture.entity.ListInfo;
import com.chengtao.culture.entity.Supply;
import com.chengtao.culture.fragmentimpl.ISupply;
import com.chengtao.culture.request.DemandRequest;
import com.chengtao.culture.request.SupplyRequest;
import com.chengtao.culture.response.IResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChengTao on 2017-01-07.
 */

public class SupplyPresenter extends IPresenter{
    private ISupply iSupply;
    private List<Supply> lists;
    private SupplyAdapter adapter;
    //-------------请求
    private SupplyRequest supplyRequestRefresh;
    private final static int REFRESH = 1;
    private SupplyRequest supplyRequestLoadMore;
    private final static int LOAD_MORE = 2;
    private int currentPage = 1;
    private boolean isFinish = false;
    public SupplyPresenter(Context context, ISupply iSupply) {
        super(context);
        this.iSupply = iSupply;
        lists = new ArrayList<>();
    }

    @Override
    protected void onIRequestSuccess(int requestId, IResponse response) {
        switch (requestId){
            case REFRESH:
                if (response.state()){
                    ArrayList<Supply> list = response.getDataList();
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
                iSupply.refreshFinished();
                break;
            case LOAD_MORE:
                if (response.state()){
                    ArrayList<Supply> list = response.getDataList();
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
                iSupply.loadMoreFinished();
                break;
        }
    }

    @Override
    protected void onIRequestFail(int requestId, Throwable throwable) {
        iSupply.tip(throwable.getMessage());
        switch (requestId){
            case REFRESH:
                iSupply.refreshFinished();
                break;
            case LOAD_MORE:
                iSupply.loadMoreFinished();
                break;
        }
    }

    public void initAdapter(){
        adapter = new SupplyAdapter(getContext(),lists);
        iSupply.initAdapter(adapter);
    }

    public void refresh(){
        currentPage = 1;
        isFinish = false;
        supplyRequestRefresh = new SupplyRequest(getContext(),new SupplyRequest.SupplyParam(currentPage));
        supplyRequestRefresh.setRequestId(REFRESH);
        executeRequest(supplyRequestRefresh);
    }

    public void loadMore(){
        if (isFinish){
            iSupply.tip("没有更多了");
            iSupply.loadMoreFinished();
        }else {
            currentPage++;
            supplyRequestLoadMore = new SupplyRequest(getContext(),new SupplyRequest.SupplyParam(currentPage));
            supplyRequestLoadMore.setRequestId(LOAD_MORE);
            executeRequest(supplyRequestLoadMore);
        }

    }
}
