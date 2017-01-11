package com.chengtao.culture.request;

import android.content.Context;

import com.chengtao.culture.entity.ListInfo;
import com.chengtao.culture.entity.Supply;
import com.chengtao.culture.response.IResponse;
import com.chengtao.library.entity.BaseResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ChengTao on 2017-01-11.
 */

public class SupplyRequest extends IRequest{
    private static final String TAB = "supply";
    private SupplyParam param;
    public SupplyRequest(Context context,SupplyParam param) {
        super(context);
        this.param = param;
    }

    @Override
    public <T extends BaseResponse> Call<T> getCall() {
        return (Call<T>) retrofit.create(SupplyAPI.class).getSupply(param);
    }

    interface SupplyAPI{
        @POST("/culture/activity")
        Call<IResponse<ListInfo,Supply>> getSupply(@Body SupplyParam param);
    }
    public static class SupplyParam{
        int page;
        String tab;
        public SupplyParam(int page) {
            this.page = page;
            tab = TAB;
        }
    }
}
