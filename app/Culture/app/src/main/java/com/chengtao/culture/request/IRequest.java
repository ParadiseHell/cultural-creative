package com.chengtao.culture.request;

import android.content.Context;

import com.chengtao.culture.utils.SpUtils;
import com.chengtao.library.http.request.AsyncRequest;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ChengTao on 2016-12-19.
 */

public abstract class IRequest extends AsyncRequest{
    //---------------默认参数
    private static final String USER_NAME = "username";
    private static final String USER_PASSWORD = "password";
    private static final String PLATFORM = "platform";
    private static final String PLATFORM_NAME = "android";
    IRequest(Context context) {
        super(context);
    }

    @Override
    public String getHost() {
        return SpUtils.getHost();
    }

    @Override
    protected Converter.Factory getConverterFactory() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .setLongSerializationPolicy(LongSerializationPolicy.DEFAULT)
                .create();
        return GsonConverterFactory.create(gson);
    }

    @Override
    protected OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new CommonInterceptor())
                .build();
    }

    private class CommonInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            //获取旧的请求
            Request oldRequest = chain.request();
            //创建新的请求体
            Request.Builder builder = oldRequest.newBuilder()
                    .addHeader(PLATFORM,PLATFORM_NAME)
                    .method(oldRequest.method(),oldRequest.body());
            //排除登录和注册
            if (!(IRequest.this instanceof LoginRequest)
                    && !(IRequest.this instanceof SignupRequest)){
                builder.addHeader(USER_NAME,SpUtils.getUserName())
                        .addHeader(USER_PASSWORD,SpUtils.getUserPassword());
            }
            //创建新的请求
            Request newRequest = builder.build();
            //返回新的响应
            return chain.proceed(newRequest);
        }
    }
}
