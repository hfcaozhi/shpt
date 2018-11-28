package com.bless.ospm.dto;

import java.util.ArrayList;
import java.util.List;

public class ResourceTreeItem {
	private long id;
	private String name;
	/** 类型，用于标识：读取、删除、修改、新增。0 读取 1 新增 2修改 3删除。子节点才有该属性。*/
	private int type=-1;
	private List<Operate> opreate = new ArrayList<Operate>();
	private List<ResourceTreeItem> children = new ArrayList<ResourceTreeItem>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Operate> getOpreate() {
		return opreate;
	}

	public void setOpreate(List<Operate> opreate) {
		this.opreate = opreate;
	}

	public List<ResourceTreeItem> getChildren() {
		return children;
	}

	public void setChildren(List<ResourceTreeItem> children) {
		this.children = children;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}
