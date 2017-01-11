package com.chengtao.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class MoblieResponse<T, E> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2566478208749870513L;
	private boolean state;
	private String message;
	private T data;
	private ArrayList<E> dataList;

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ArrayList<E> getDataList() {
		return dataList;
	}

	public void setDataList(ArrayList<E> dataList) {
		this.dataList = dataList;
	}

}