package com.chengtao.culture.request;

import android.content.Context;

import com.chengtao.culture.entity.NullObject;
import com.chengtao.culture.entity.User;
import com.chengtao.culture.response.IResponse;
import com.chengtao.library.entity.BaseResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ChengTao on 2016-12-20.
 */

/**
 * 注册请求
 */
@SuppressWarnings("unchecked")
public class SignUpRequest extends IRequest{
    private User user;
    public SignUpRequest(Context context, User user) {
        super(context);
        this.user = user;
    }
    interface SignUpAPI{
        @POST("user/signUp")
        Call<IResponse<User,NullObject>> signUp(@Body User user);
    }
    @Override
    public <T extends BaseResponse> Call<T> getCall() {
        return (Call<T>) retrofit.create(SignUpAPI.class).signUp(user);
    }
}
