package com.chengtao.culture.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chengtao.culture.R;
import com.chengtao.culture.entity.CompanyUser;
import com.chengtao.culture.entity.Exhibition;
import com.chengtao.culture.entity.PersonUser;
import com.chengtao.culture.entity.User;

import java.io.Serializable;

/**
 * Created by ChengTao on 2017-01-07.
 */

/**
 * 用户信息界面
 */
public class UserInfoActivity extends IActivity{
    private static final String USER = "USER";
    private Toolbar toolbar;
    private TextView tvUser;
    @SuppressWarnings("FieldCanBeLocal")
    private CollapsingToolbarLayout toolbarLayout;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void initView() {
        toolbar = getView(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbarLayout = getView(R.id.collapsing_toolbar);
        toolbarLayout.setTitle("用户信息");
        tvUser = getView(R.id.tv_user);
    }

    @Override
    protected void setListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        try {
            Object user = bundle.get(USER);
            if (user instanceof PersonUser){
                tvUser.setText(user.toString());
                toolbarLayout.setTitle(((PersonUser) user).getName());
            }else if(user instanceof CompanyUser){
                tvUser.setText(user.toString());
                toolbarLayout.setTitle(((CompanyUser) user).getName());
            }
        }catch (Exception e){
            Log.e("TAG",e.getMessage());
        }
    }

    @Override
    protected void initPresenter() {

    }

    public static void invoke(Context context){
        Intent intent = new Intent(context,UserInfoActivity.class);
        context.startActivity(intent);
    }
    public static void invoke(Context context,Object obj){
        Intent intent = new Intent(context,UserInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(USER, (Serializable) obj);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
