package com.bless.ospm.dto;

import java.util.List;

public class ResourceTreeJSDto {
	private String root;
	private List<ResourceTreeItem> ops;

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public List<ResourceTreeItem> getOps() {
		return ops;
	}

	public void setOps(List<ResourceTreeItem> ops) {
		this.ops = ops;
	}

}
