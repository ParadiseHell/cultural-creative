package com.chengtao.culture.entity;

import com.chengtao.library.entity.BaseResponse;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by ChengTao on 2017-01-11.
 */

public class Supply extends BaseResponse{
    private int id;
    private int companyId;
    private String companyName;
    private String name;
    private String detail;
    private ArrayList<String> images;
    private int visit;
    private Timestamp creatAt;
    private Timestamp updateAt;
    private Timestamp deadLine;

    public Supply() {
    }

    public Supply(int id, int companyId, String companyName, String name, String detail, ArrayList<String> images, int visit, Timestamp creatAt, Timestamp updateAt, Timestamp deadLine) {
        this.id = id;
        this.companyId = companyId;
        this.companyName = companyName;
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

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }

    public Timestamp getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(Timestamp creatAt) {
        this.creatAt = creatAt;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    public Timestamp getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Timestamp deadLine) {
        this.deadLine = deadLine;
    }
}