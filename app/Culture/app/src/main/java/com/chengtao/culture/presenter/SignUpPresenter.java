package com.chengtao.culture.presenter;

/**
 * Created by ChengTao on 2016-12-21.
 */

import android.content.Context;

import com.chengtao.culture.App;
import com.chengtao.culture.activity.LoginActivity;
import com.chengtao.culture.activityimpl.ISignUp;
import com.chengtao.culture.entity.User;
import com.chengtao.culture.request.SignUpRequest;
import com.chengtao.culture.response.IResponse;
import com.chengtao.culture.utils.MD5;
import com.chengtao.culture.utils.SpUtils;
import com.chengtao.culture.utils.StringUtils;

/**
 * 注册界面Presenter
 */
public class SignUpPresenter extends IPresenter {
    //-----------页面接口
    private ISignUp iSignUp = null;
    //-----------请求
    @SuppressWarnings("FieldCanBeLocal")
    private SignUpRequest signUpRequest = null;
    private final static int SIGN_UP_ID = 1;
    private final static String USER_TYPE = "USER_TYPE";

    public SignUpPresenter(Context context,ISignUp iSignUp) {
        super(context);
        this.iSignUp = iSignUp;
    }

    @Override
    protected void onIRequestSuccess(int requestId, IResponse response) {
        switch (requestId){
            case SIGN_UP_ID:
                boolean state = response.state();
                String message = response.getMessage();
                if (state){
                    if (message != null){
                        iSignUp.tip(message);
                    }
                    User user = (User) response.getData();
                    SpUtils.saveUser(user);
                    iSignUp.signUpSuccess();
                }else {
                    if (message != null){
                        iSignUp.tip(message);
                    }
                    iSignUp.singUpFail();
                }
                break;
        }
    }

    @Override
    protected void onIRequestFail(int requestId, Throwable throwable) {
        switch (requestId){
            case SIGN_UP_ID:
                iSignUp.tip("注册失败");
                iSignUp.singUpFail();
                break;
        }
    }

    /**
     * 注册
     * @param userName 用户面
     * @param userPassword 密码
     * @param userPasswordAgain 确认密码
     * @param isCompany 是否公司用户
     */
    public void signUp(String userName,String userPassword,String userPasswordAgain,boolean isCompany){
        if (StringUtils.isStrNull(userName)){
            iSignUp.tip("请填写相用户名");
            return;
        }
        if (StringUtils.isStrNull(userPassword)){
            iSignUp.tip("请填写密码");
            return;
        }
        if (StringUtils.isStrNull(userPasswordAgain)){
            iSignUp.tip("请填写确认密码");
            return;
        }
        if (!userPassword.equals(userPasswordAgain)){
            iSignUp.tip("密码不一致");
            return;
        }
        iSignUp.signUpStart();
        userPassword = MD5.getMD5Password(userPassword);
        if (isCompany){
            signUpRequest = new SignUpRequest(getContext(),new SignUpRequest.SignUpParam(userName,userPassword,USER_TYPE));
        }else {
            signUpRequest = new SignUpRequest(getContext(),new SignUpRequest.SignUpParam(userName,userPassword,null));
        }
        signUpRequest.setRequestId(SIGN_UP_ID);
        executeRequest(signUpRequest);
    }
}
