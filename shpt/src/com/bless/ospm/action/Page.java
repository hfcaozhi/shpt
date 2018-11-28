package com.bless.ospm.action;

import java.util.List;

public class Page<T> {

	/** 当前页码 */
	private int pageNo = 1;
	/** 每页记录数 */
	private int pageSize = 1;
	/** 总页数 */
	private int pageCount;

	/** 总记录灵数 */
	private int total;

	private boolean hasNxt;
	private boolean hasPre;
	
	private List<T> rs;
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return (this.total%this.pageSize==0)?this.total/this.pageSize:this.total/this.pageSize+1;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public boolean isHasNxt() {
		if(this.pageNo>0&&getPageCount()>1&&this.pageNo<getPageCount())return true;
		else
			return false;
	}

	public void setHasNxt(boolean hasNxt) {
		this.hasNxt = hasNxt;
	}

	public boolean isHasPre() {
		if(this.pageNo==1||getPageCount()<=1) return false;
		else 
			return true;
	}

	public void setHasPre(boolean hasPre) {
		this.hasPre = hasPre;
	}

	public List<T> getRs() {
		return rs;
	}

	public void setRs(List<T> rs) {
		this.rs = rs;
	}

}
