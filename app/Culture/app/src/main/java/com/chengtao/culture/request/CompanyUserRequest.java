package com.chengtao.culture.request;

import android.content.Context;

import com.chengtao.culture.entity.CompanyUser;
import com.chengtao.culture.entity.NullObject;
import com.chengtao.culture.response.IResponse;
import com.chengtao.library.entity.BaseResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ChengTao on 2017-01-11.
 */

public class CompanyUserRequest extends IRequest{
    private CompanyUserParam param;
    public CompanyUserRequest(Context context,CompanyUserParam param) {
        super(context);
        this.param = param;
    }

    @Override
    public <T extends BaseResponse> Call<T> getCall() {
        return (Call<T>) retrofit.create(CompanyUserAPI.class).getUser(param);
    }

    interface CompanyUserAPI{
        @POST("/culture/user/login")
        Call<IResponse<CompanyUser,NullObject>> getUser(@Body CompanyUserParam param);
    }

    public static class CompanyUserParam{
        String userName;
        String userPassword;
        public CompanyUserParam(String name, String password) {
            userName = name;
            userPassword = password;
        }
    }
}
