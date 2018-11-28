package com.bless.ospm.dto;

import java.util.ArrayList;
import java.util.List;

public class OrgTreeDto {
	private Long id;
	private String text;
	private Long parentId;
	private String description;
	private List<OrgTreeDto> children = new ArrayList<OrgTreeDto>();
	public OrgTreeDto(Long id, String text, Long parentId, String discription,
			List<OrgTreeDto> children) {
		super();
		this.id = id;
		this.text = text;
		this.parentId = parentId;
		this.description = discription;
		this.children = children;
	}
	public OrgTreeDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public List<OrgTreeDto> getChildren() {
		return children;
	}
	public void setChildren(List<OrgTreeDto> children) {
		this.children = children;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
