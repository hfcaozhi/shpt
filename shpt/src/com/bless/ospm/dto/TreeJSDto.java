package com.bless.ospm.dto;


public class TreeJSDto {
	private String root;
	private TreeItemDto ops = new TreeItemDto();
	public String getRoot() {
		return root;
	}
	public void setRoot(String root) {
		this.root = root;
	}
	public TreeItemDto getOps() {
		return ops;
	}
	public void setOps(TreeItemDto ops) {
		this.ops = ops;
	}
}
