package com.chengtao.culture.activity;

import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.ImageView;

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
public class LoginActivity extends BaseActivity implements ILogin ,View.OnClickListener{
    private final static String TITLE = "登录";
    //----------------控件
    private TextInputLayout tilUserName;
    private TextInputLayout tilPassword;
    private ActionProcessButton btnLogin;
    private ImageView ivAvatar;
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
        btnLogin = getView(R.id.btn_login);
        btnLogin.setMode(ActionProcessButton.Mode.ENDLESS);
        //设置标题
        setTitle(TITLE);
    }

    @Override
    protected void setListener() {
        btnLogin .setOnClickListener(this);
        ivAvatar.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {
        loginPresenter = new LoginPresenter(mContext,this);
    }

    @Override
    public void success(String message) {
        btnLogin.setProgress(0);
        showToast(message);
        //跳转登录界面
        MainActivity.invoke(LoginActivity.this);
        finish();
    }

    @Override
    public void fail(String message) {
        btnLogin.setProgress(0);
        showToast(message);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                btnLogin.setProgress(1);
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
        }
    }
}
