package com.chengtao.culture.request;

import android.content.Context;

import com.chengtao.culture.entity.Exhibition;
import com.chengtao.culture.entity.ListInfo;
import com.chengtao.culture.entity.NullObject;
import com.chengtao.culture.response.IResponse;
import com.chengtao.library.entity.BaseResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ChengTao on 2017-01-08.
 */

@SuppressWarnings({"unchecked", "SpellCheckingInspection"})
public class ExhibitionRequest extends IRequest{
    private static final String TAB = "exhibition";
    private ExhibitonParam param;
    public ExhibitionRequest(Context context,ExhibitonParam param) {
        super(context);
        this.param = param;
    }

    @Override
    public <T extends BaseResponse> Call<T> getCall() {
        return (Call<T>) retrofit.create(ExhibitionAPI.class).getExhibition(param);
    }

    interface ExhibitionAPI{
        @POST("/culture/activity")
        Call<IResponse<ListInfo,Exhibition>> getExhibition(@Body ExhibitonParam param);
    }
    public static class ExhibitonParam{
        int page;
        String tab;
        public ExhibitonParam(int page) {
            this.page = page;
            tab = TAB;
        }
    }
}

