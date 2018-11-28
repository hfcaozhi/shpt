package com.bless.ospm.service;

import java.util.List;

import com.bless.common.service.BaseService;
import com.bless.ospm.model.base.UserMenu;

public interface UserMenuService extends BaseService{
	/**
	 * 查询用户菜单
	 * @param id
	 * @return
	 */
	public List<UserMenu> byUserMenu(Long id);
	/**
	 * 修改用户菜单
	 * @param menuArray
	 * @param userId
	 */
	public void updateUserMenus(String menuArray,Long userId);
}
