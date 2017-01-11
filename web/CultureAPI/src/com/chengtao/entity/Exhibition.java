package com.chengtao.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Exhibition implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6765864021239180677L;
	private int id;
	private int companyId;
	private String companyName;
	private String theme;
	private String detail;
	private ArrayList<String> images;
	private int visit;
	private int total;
	private Timestamp creatAt;
	private Timestamp updateAt;
	public Exhibition(){}
	
	public Exhibition(int id, int companyId, String companyName, String theme, String detail, ArrayList<String> images,
			int visit, int total, Timestamp creatAt, Timestamp updateAt) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.companyName = companyName;
		this.theme = theme;
		this.detail = detail;
		this.images = images;
		this.visit = visit;
		this.total = total;
		this.creatAt = creatAt;
		this.updateAt = updateAt;
	}


	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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