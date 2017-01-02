package com.chengtao.culture.presenter;

import android.content.Context;
import com.chengtao.culture.activityimpl.IMain;
import com.chengtao.culture.response.IResponse;

/**
 * Created by ChengTao on 2017-01-01.
 */

public class MainPresenter extends IPresenter{

    public MainPresenter(Context context, IMain iMain) {
        super(context);
    }

    @Override
    protected void onIRequestSuccess(int requestId, IResponse response) {

    }

    @Override
    protected void onIRequestFail(int requestId, Throwable throwable) {

    }
}
