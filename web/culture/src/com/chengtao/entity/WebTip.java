package com.chengtao.entity;

import java.io.Serializable;

public class WebTip implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6686324181339792978L;
	private boolean state;
	private String title;
	private String message;

	public WebTip(boolean state, String title, String message) {
		super();
		this.state = state;
		this.title = title;
		this.message = message;
	}
	public WebTip(){
		
	}
	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "WebTip [state=" + state + ", title=" + title + ", message=" + message + "]";
	}
	
}
