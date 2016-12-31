package com.chengtao.culture.activity;

/**
 * Created by ChengTao on 2016-12-21.
 */

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import com.chengtao.culture.R;
import com.chengtao.culture.activityimpl.ISignUp;
import com.chengtao.culture.presenter.SignUpPresenter;
import com.dd.processbutton.iml.ActionProcessButton;

/**
 * 注册界面
 */
@SuppressWarnings("ConstantConditions")
public class SignUpActivity extends IActivity implements ISignUp,View.OnClickListener{
    private final static String TITLE = "注册";
    //----------控件
    private TextInputLayout tilUserName;
    private TextInputLayout tilUserPassword;
    private TextInputLayout tilUserPasswordAgain;
    private ActionProcessButton btnSignUp;
    //----------Presenter
    private SignUpPresenter signUpPresenter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_sign_up;
    }

    @Override
    protected void initView() {
        tilUserName = getView(R.id.til_user_name);
        tilUserPassword = getView(R.id.til_user_password);
        tilUserPasswordAgain = getView(R.id.til_user_password_again);
        btnSignUp = getView(R.id.btn_sign_up);
        btnSignUp.setMode(ActionProcessButton.Mode.ENDLESS);
        btnSignUp.setProgress(0);
        //设置标题
        setTitle(TITLE);
    }

    @Override
    protected void setListener() {
        btnSignUp.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {
        signUpPresenter = new SignUpPresenter(mContext,this);
    }

    @Override
    public void singUpFail() {
        btnSignUp.setProgress(0);
    }

    @Override
    public void signUpSuccess() {
        btnSignUp.setProgress(0);
        MainActivity.invoke(SignUpActivity.this);
        finish();
    }

    @Override
    public void signUpStart() {
        btnSignUp.setProgress(1);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_sign_up:
                signUpPresenter.signUp(
                        tilUserName.getEditText().getText().toString().trim(),
                        tilUserPassword.getEditText().getText().toString().trim(),
                        tilUserPasswordAgain.getEditText().getText().toString().trim()
                );
                break;
        }
    }

    /**
     * 注册界面跳转
     * @param activity Activity
     */
    public static void invoke(Activity activity){
        Intent intent = new Intent(activity,SignUpActivity.class);
        activity.startActivity(intent);
    }
}
