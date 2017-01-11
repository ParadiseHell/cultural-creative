package com.chengtao.culture.entity;

import com.chengtao.library.entity.BaseResponse;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChengTao on 2017-01-08.
 */

/**
 * 展会实体
 */
public class Exhibition extends BaseResponse{
    private int id;
    private int companyId;
    private String companyName;
    private String theme;
    private String detail;
    private ArrayList<String> images;
    private int visit;
    private Timestamp creatAt;
    private Timestamp updateAt;

    public Exhibition() {
    }

    public Exhibition(int id, String companyName, int companyId, String theme, String detail, ArrayList<String> images, Timestamp creatAt, int visit, Timestamp updateAt) {
        this.id = id;
        this.companyName = companyName;
        this.companyId = companyId;
        this.theme = theme;
        this.detail = detail;
        this.images = images;
        this.creatAt = creatAt;
        this.visit = visit;
        this.updateAt = updateAt;
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

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
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
}
