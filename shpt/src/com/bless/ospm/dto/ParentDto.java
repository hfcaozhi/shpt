package com.bless.ospm.dto;

import java.util.ArrayList;
import java.util.List;

public class ParentDto {
	private Long id;
	private String text;
	private List<ParentDto> children = new ArrayList<ParentDto>();
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
	public List<ParentDto> getChildren() {
		return children;
	}
	public void setChildren(List<ParentDto> children) {
		this.children = children;
	}
}
