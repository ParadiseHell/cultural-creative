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
    private LoginParam param = null;
    public LoginRequest(Context context, LoginParam param) {
        super(context);
        this.param = param;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BaseResponse> Call<T> getCall() {
        return (Call<T>) retrofit.create(LoginAPI.class).login(param);
    }
    interface LoginAPI{
        @POST("/culture/user/login")
        Call<IResponse<User,NullObject>> login(@Body LoginParam param);
    }

    public static class LoginParam{
        String userName;
        String userPassword;
        public LoginParam(String name, String password) {
            userName = name;
            userPassword = password;
        }
    }
}
