package com.chengtao.culture.request;

import android.content.Context;
import android.util.Log;

import com.chengtao.culture.entity.NullObject;
import com.chengtao.culture.entity.User;
import com.chengtao.culture.response.IResponse;
import com.chengtao.library.entity.BaseResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by ChengTao on 2016-12-19.
 */

public class LoginRequest extends IRequest{
    private User user = null;
    public LoginRequest(Context context, String name, String password) {
        super(context);
        user = new User(name,password);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BaseResponse> Call<T> getCall() {
        return (Call<T>) retrofit.create(LoginAPI.class).login(user);
    }
    interface LoginAPI{
        @POST("/user/login")
        Call<IResponse<User,NullObject>> login(@Body User user);
    }
}
