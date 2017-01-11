package com.chengtao.culture.fragmentimpl;

import android.support.v7.widget.RecyclerView;

import com.chengtao.culture.activityimpl.IBase;

/**
 * Created by ChengTao on 2017-01-07.
 */

public interface ISupply extends IBase{
    void initAdapter(RecyclerView.Adapter adapter);
    void refreshFinished();
    void loadMoreFinished();
}
