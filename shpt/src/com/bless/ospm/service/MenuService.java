package com.bless.ospm.service;

import java.util.List;

import com.bless.common.service.BaseService;
import com.bless.ospm.dto.MenuDto;
import com.bless.ospm.dto.TreeJSDto;
import com.bless.ospm.model.base.Menu;

public interface MenuService extends BaseService {
	/**
	 * 将菜单转化成树状结构
	 * 
	 * 逐级往下关联菜单，如果有些菜单无法关联上，则丢失。
	 * 
	 * @return
	 */
	public MenuDto convert2Tree(List<Menu> menus);
	/**
	 * 查询所有的菜单
	 * @param menus
	 * @return
	 */
	public MenuDto allMenu(List<Menu> menus);
	/**
	 * 删除菜单信息，只修改状态，并不是真的删除
	 * @param menu
	 * @return
	 */
	public String delMenu(Menu menu);
	/**
	 * 获得所有的菜单列表
	 * @return
	 */
	public List<Menu> allMenu();
	/**
	 * 获取所有的菜单信息
	 * @return
	 */
	public TreeJSDto menuTree();
	/**
	 * 修改菜单信息
	 * @param menu
	 * @return
	 */
	public String updateMenu(Menu menu);
}
