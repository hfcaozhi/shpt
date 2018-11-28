package com.bless.ospm.dto;

import java.util.ArrayList;
import java.util.List;

import com.bless.ospm.model.base.Menu;

public class MenuDto {
	private Menu menu;
	private List<MenuDto> children = new ArrayList<MenuDto>();

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public List<MenuDto> getChildren() {
		return children;
	}

	public void setChildren(List<MenuDto> children) {
		this.children = children;
	}
	
}
