package com.bless.ospm.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 下拉列表框子项
 * 
 * @author lala
 * 
 */
public class ComboItem {
	private String id;
	private String text;
	private boolean selected;
	private List<ComboItem> children = new ArrayList<ComboItem>();
	public ComboItem() {
	}

	public ComboItem(String id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public List<ComboItem> getChildren() {
		return children;
	}

	public void setChildren(List<ComboItem> children) {
		this.children = children;
	}

}
