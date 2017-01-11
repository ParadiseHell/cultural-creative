package com.chengtao.culture.presenter;

import android.content.Context;

import com.chengtao.culture.App;
import com.chengtao.culture.activityimpl.ILogin;
import com.chengtao.culture.entity.User;
import com.chengtao.culture.request.LoginRequest;
import com.chengtao.culture.response.IResponse;
import com.chengtao.culture.utils.MD5;
import com.chengtao.culture.utils.SpUtils;
import com.chengtao.culture.utils.StringUtils;

/**
 * Created by ChengTao on 2016-12-19.
 */

public class LoginPresenter extends IPresenter {
    //----------请求
    @SuppressWarnings("FieldCanBeLocal")
    private LoginRequest loginRequest = null;
    private final static int LOGIN_REQUEST_ID = 1;
    //----------页面接口
    private ILogin iLogin;

    public LoginPresenter(Context context, ILogin iLogin) {
        super(context);
        this.iLogin = iLogin;
    }

    @Override
    protected void onIRequestSuccess(int requestId, IResponse response) {
        boolean state = response.state();
        String message = response.getMessage();
        switch (requestId){
            case LOGIN_REQUEST_ID:
                if (state){
                    if (message != null){
                        iLogin.tip(message);
                    }
                    User user = (User) response.getData();
                    //保存用户信息
                    SpUtils.saveUser(user);
                    iLogin.loginSuccess();
                }else {
                    if (message != null){
                        iLogin.tip(message);
                        iLogin.loginFail();
                    }
                }
                break;
        }
    }

    @Override
    protected void onIRequestFail(int requestId, Throwable throwable) {
        switch (requestId){
            case LOGIN_REQUEST_ID:
                iLogin.tip(throwable.getMessage());
                iLogin.loginFail();
                break;
        }
    }

    /**
     * 登录
     * @param name 用户名
     * @param password 密码
     */
    public void login(String name,String password){
        if (StringUtils.isStrNull(name)){
            iLogin.tip("请填写用户名");
            return;
        }
        if (StringUtils.isStrNull(password)){
            iLogin.tip("请填写密码");
            return;
        }
        iLogin.loginStart();
        password = MD5.getMD5Password(password);
        loginRequest = new LoginRequest(getContext(),new LoginRequest.LoginParam(name,password));
        loginRequest.setRequestId(LOGIN_REQUEST_ID);
        executeRequest(loginRequest);
    }

}
