package com.chengtao.culture.entity;

import com.chengtao.library.entity.BaseResponse;

import java.util.List;

/**
 * Created by ChengTao on 2016-12-30.
 */

public class News extends BaseResponse{
    private int id;
    private String companyName;
    private String newsTitle;
    private String newsTime;
    private String newsDetail;
    private List<String> images;
    private List<Note> notes;
    private int visitNum;
    private int operateType;
    public News() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(String newsTime) {
        this.newsTime = newsTime;
    }

    public String getNewsDetail() {
        return newsDetail;
    }

    public void setNewsDetail(String newsDetail) {
        this.newsDetail = newsDetail;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public int getVisitNum() {
        return visitNum;
    }

    public void setVisitNum(int visitNum) {
        this.visitNum = visitNum;
    }

    public int getOperateType() {
        return operateType;
    }

    public void setOperateType(int operateType) {
        this.operateType = operateType;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", newsTitle='" + newsTitle + '\'' +
                ", newsTime='" + newsTime + '\'' +
                ", newsDetail='" + newsDetail + '\'' +
                ", images=" + images +
                ", notes=" + notes +
                ", visitNum=" + visitNum +
                '}';
    }
}
