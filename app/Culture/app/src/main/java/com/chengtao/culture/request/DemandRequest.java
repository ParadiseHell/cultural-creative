package com.chengtao.culture.request;

import android.content.Context;

import com.chengtao.culture.entity.Demand;
import com.chengtao.culture.entity.ListInfo;
import com.chengtao.culture.response.IResponse;
import com.chengtao.library.entity.BaseResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ChengTao on 2017-01-11.
 */

public class DemandRequest extends IRequest{
    private static final String TAB = "demand";
    private DemandParam param;
    public DemandRequest(Context context,DemandParam param) {
        super(context);
        this.param = param;
    }

    @Override
    public <T extends BaseResponse> Call<T> getCall() {
        return (Call<T>) retrofit.create(DemandAPI.class).getDemand(param);
    }

    interface DemandAPI{
        @POST("/culture/activity")
        Call<IResponse<ListInfo,Demand>> getDemand(@Body DemandParam param);
    }

    public static class DemandParam{
        int page;
        String tab;
        public DemandParam(int page) {
            this.page = page;
            tab = TAB;
        }
    }
}
