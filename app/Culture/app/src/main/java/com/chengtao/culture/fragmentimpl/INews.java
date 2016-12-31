package com.chengtao.culture.fragmentimpl;

import android.support.v7.widget.RecyclerView;
import com.chengtao.culture.activityimpl.IBase;

/**
 * Created by ChengTao on 2016-12-31.
 */

public interface INews extends IBase{
    void initAdapter(RecyclerView.Adapter adapter);
    void refreshFinished();
    void loadMoreFinished();
}
