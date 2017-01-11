package com.chengtao.entity;

public class ListInfo {
	int currentCount;
	int totalCount;
	
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
