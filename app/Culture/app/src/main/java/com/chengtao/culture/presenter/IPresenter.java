package com.chengtao.culture.presenter;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import com.chengtao.culture.R;
import com.chengtao.culture.response.IResponse;
import com.chengtao.culture.utils.SpUtils;
import com.chengtao.library.entity.BaseResponse;
import com.chengtao.library.presenter.BasePresenter;

/**
 * Created by ChengTao on 2016-12-19.
 */

/**
 * 自定义Presenter
 */
abstract class IPresenter extends BasePresenter{
    IPresenter(Context context) {
        super(context);
    }

    @Override
    public void onRequestSuccess(int requestId, BaseResponse response) {
        IResponse response1 = (IResponse) response;
        Log.e("TAG","onRequestSuccess");
        onIRequestSuccess(requestId,response1);
    }
    @Override
    public void onRequestFail(int requestId, Throwable throwable) {
        if (throwable == null || throwable.getMessage() == null){
            throwable = new Throwable("请求超时");
            onIRequestFail(requestId,throwable);
        }else {
            onIRequestFail(requestId,throwable);
        }
    }

    protected abstract void onIRequestSuccess(int requestId, IResponse response);
    protected abstract void onIRequestFail(int requestId, Throwable throwable);

    /**
     * 设置主机地址
     */
    public void setHost(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_host,null);
        final TextInputLayout tilHost = (TextInputLayout) view.findViewById(R.id.til_host);
        final TextInputLayout tilPort = (TextInputLayout) view.findViewById(R.id.til_port);
        AlertDialog dialog  = new AlertDialog.Builder(getContext())
                .setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @SuppressWarnings("ConstantConditions")
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SpUtils.saveHost(
                                tilHost.getEditText().getText().toString().trim(),
                                tilPort.getEditText().getText().toString().trim()
                        );
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).create();
        dialog.show();
    }
}
