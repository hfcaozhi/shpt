package com.bless.ospm.dto;

import java.util.ArrayList;
import java.util.List;

public class TreeItemDto {
	private Long id;
	private String name;
	private String folderA;
	private String folderB;
	private Long menuId;
	private Long codeId;
	private Long resourceId;
	private Long parentId;
	private int isDel;
	private List<TreeItemDto> children = new ArrayList<TreeItemDto>();
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFolderA() {
		return folderA;
	}
	public void setFolderA(String folderA) {
		this.folderA = folderA;
	}
	public String getFolderB() {
		return folderB;
	}
	public void setFolderB(String folderB) {
		this.folderB = folderB;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	public Long getCodeId() {
		return codeId;
	}
	public void setCodeId(Long codeId) {
		this.codeId = codeId;
	}
	public List<TreeItemDto> getChildren() {
		return children;
	}
	public void setChildren(List<TreeItemDto> children) {
		this.children = children;
	}
	public Long getResourceId() {
		return resourceId;
	}
	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
}
