package com.chengtao.culture.entity;

import com.chengtao.library.entity.BaseResponse;

/**
 * Created by ChengTao on 2017-01-11.
 */

public class ListInfo extends BaseResponse{
    int currentCount;
    int totalCount;

    public ListInfo() {
    }

    public ListInfo(int currentCount, int totalCount) {
        super();
        this.currentCount = currentCount;
        this.totalCount = totalCount;
    }
    public int getCurrentCount() {
        return currentCount;
    }
    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
