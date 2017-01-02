package com.chengtao.culture.request;

import android.content.Context;

import com.chengtao.culture.entity.News;
import com.chengtao.culture.entity.NullObject;
import com.chengtao.culture.response.IResponse;
import com.chengtao.library.entity.BaseResponse;
import java.sql.Date;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ChengTao on 2017-01-01.
 */

public class NewsRequest extends IRequest{
    private NewsParam param;
    public NewsRequest(Context context,NewsParam param) {
        super(context);
        this.param = param;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BaseResponse> Call<T> getCall() {
        return (Call<T>) retrofit.create(NewsAPI.class).getNews(param);
    }

    interface NewsAPI{
        @POST("/news")
        Call<IResponse<NullObject,News>>
        getNews(@Body NewsParam param);
    }
    public static class NewsParam{
        long date;
        int type;
        public NewsParam(long date, int type) {
            this.date = date;
            this.type = type;
        }
    }
}
