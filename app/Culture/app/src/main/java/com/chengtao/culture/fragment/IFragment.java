package com.chengtao.culture.fragment;

import com.chengtao.culture.activityimpl.IBase;
import com.chengtao.library.fragment.BaseFragment;

/**
 * Created by ChengTao on 2016-12-22.
 */

/**
 * 所有Fragment父类
 */
public abstract class IFragment extends BaseFragment implements IBase{
    @Override
    public void tip(String message) {
        showToast(message);
    }
}
