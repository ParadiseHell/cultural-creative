package com.chengtao.culture.activity;

import android.support.design.widget.Snackbar;

import com.chengtao.culture.activityimpl.IBase;
import com.chengtao.library.activity.BaseActivity;

/**
 * Created by ChengTao on 2016-12-21.
 */

/**
 * 所有界面父类
 */
public abstract class IActivity extends BaseActivity implements IBase{
    @Override
    public void tip(String message) {
        showToast(message);
    }

}
