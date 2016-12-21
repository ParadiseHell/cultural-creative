package com.chengtao.culture.response;

import com.chengtao.library.entity.BaseResponse;

import java.util.ArrayList;

/**
 * Created by ChengTao on 2016-12-19.
 */

public class IResponse<T,E> extends BaseResponse {
    private boolean state;
    private String message;
    private T data;
    private ArrayList<E> dataList;

    public boolean state() {
        return state;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public ArrayList<E> getDataList() {
        return dataList;
    }

    @Override
    public String toString() {
        return "IResponse{" +
                "state=" + state +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", dataList=" + dataList +
                '}';
    }
}
