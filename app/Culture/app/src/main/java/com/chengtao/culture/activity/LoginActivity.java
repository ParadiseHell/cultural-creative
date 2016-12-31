package com.chengtao.culture.activity;

import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chengtao.culture.App;
import com.chengtao.culture.R;
import com.chengtao.culture.activityimpl.ILogin;
import com.chengtao.culture.presenter.LoginPresenter;
import com.chengtao.library.activity.BaseActivity;
import com.dd.processbutton.iml.ActionProcessButton;

/**
 * Created by ChengTao on 2016-12-19.
 */

/**
 * 登录界面
 */
public class LoginActivity extends IActivity implements ILogin ,View.OnClickListener{
    private final static String TITLE = "登录";
    //----------------控件
    private TextInputLayout tilUserName;
    private TextInputLayout tilPassword;
    private ActionProcessButton btnLogin;
    private ImageView ivAvatar;
    private TextView tvSignUp;
    //---------------Presenter
    private LoginPresenter loginPresenter;
    //---------------其他
    private int avatarClickNum = 0;
    private static final int AVATAR_CLICK_MAX_NUM = 5;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        ivAvatar = getView(R.id.iv_avatar);
        tilUserName = getView(R.id.til_username);
        tilPassword = getView(R.id.til_password);
        tvSignUp = getView(R.id.tv_sign_up);
        btnLogin = getView(R.id.btn_login);
        btnLogin.setMode(ActionProcessButton.Mode.ENDLESS);
        //设置标题
        setTitle(TITLE);
    }

    @Override
    protected void setListener() {
        btnLogin .setOnClickListener(this);
        ivAvatar.setOnClickListener(this);
        tvSignUp.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {
        loginPresenter = new LoginPresenter(mContext,this);
    }

    @Override
    public void loginSuccess() {
        btnLogin.setProgress(0);
        //跳转登录界面
        MainActivity.invoke(LoginActivity.this);
        finish();
    }

    @Override
    public void loginFail() {
        btnLogin.setProgress(0);
    }

    @Override
    public void loginStart() {
        btnLogin.setProgress(1);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                loginPresenter.login(
                        tilUserName.getEditText().getText().toString(),
                        tilPassword.getEditText().getText().toString());
                break;
            case R.id.iv_avatar:
                avatarClickNum++;
                if (avatarClickNum >= AVATAR_CLICK_MAX_NUM){
                    avatarClickNum = 0;
                    loginPresenter.setHost();
                }
                break;
            case R.id.tv_sign_up:
                SignUpActivity.invoke(LoginActivity.this);
                break;
        }
    }
}
