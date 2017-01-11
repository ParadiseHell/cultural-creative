package com.chengtao.culture.request;

import android.content.Context;

import com.chengtao.culture.entity.NullObject;
import com.chengtao.culture.entity.PersonUser;
import com.chengtao.culture.response.IResponse;
import com.chengtao.library.entity.BaseResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ChengTao on 2017-01-11.
 */

public class PersonUserRequest extends IRequest{
    private PersonUserParam param;
    public PersonUserRequest(Context context,PersonUserParam param) {
        super(context);
        this.param = param;
    }

    @Override
    public <T extends BaseResponse> Call<T> getCall() {
        return (Call<T>) retrofit.create(PersonUserAPI.class).getUser(param);
    }

    interface PersonUserAPI{
        @POST("/culture/user/login")
        Call<IResponse<PersonUser,NullObject>> getUser(@Body PersonUserParam param);
    }


    public static class PersonUserParam{
        String userName;
        String userPassword;
        public PersonUserParam(String name, String password) {
            userName = name;
            userPassword = password;
        }
    }
}
