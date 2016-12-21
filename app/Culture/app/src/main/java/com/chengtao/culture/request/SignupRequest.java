package com.chengtao.culture.request;

import android.content.Context;
import com.chengtao.library.entity.BaseResponse;
import retrofit2.Call;

/**
 * Created by ChengTao on 2016-12-20.
 */

public class SignupRequest extends IRequest{

    SignupRequest(Context context) {
        super(context);
    }

    @Override
    public <T extends BaseResponse> Call<T> getCall() {
        return null;
    }
}
