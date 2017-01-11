package com.chengtao.culture.entity;

import com.chengtao.library.entity.BaseResponse;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by ChengTao on 2017-01-11.
 */

public class Demand extends BaseResponse{
    private int id;
    private int userId;
    private String userName;
    private String name;
    private String detail;
    private ArrayList<String> images;
    private int visit;
    private Timestamp creatAt;
    private Timestamp updateAt;
    private Timestamp deadLine;

    public Demand() {
    }

    public Demand(int id, int userId, String userName, String name, String detail, ArrayList<String> images, int visit, Timestamp creatAt, Timestamp updateAt, Timestamp deadLine) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.name = name;
        this.detail = detail;
        this.images = images;
        this.visit = visit;
        this.creatAt = creatAt;
        this.updateAt = updateAt;
        this.deadLine = deadLine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    public Timestamp getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(Timestamp creatAt) {
        this.creatAt = creatAt;
    }

    public Timestamp getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Timestamp deadLine) {
        this.deadLine = deadLine;
    }
}
