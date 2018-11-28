package com.bless.common.vo;

import com.bless.ospm.action.Page;

public class Result {
	
	/** 0 成功 -1失败*/
	private int status;
	private String message;
	private Page page;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}
