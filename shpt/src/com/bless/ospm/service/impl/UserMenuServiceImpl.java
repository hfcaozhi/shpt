package com.bless.ospm.service.impl;

import java.util.HashMap;
import java.util.List;

import com.bless.common.dao.BaseDao;
import com.bless.common.service.impl.BaseServiceImpl;
import com.bless.ospm.dao.UserMenuDao;
import com.bless.ospm.model.base.Menu;
import com.bless.ospm.model.base.User;
import com.bless.ospm.model.base.UserMenu;
import com.bless.ospm.service.UserMenuService;

public class UserMenuServiceImpl extends BaseServiceImpl implements UserMenuService{
	private UserMenuDao userMenuDao;
	
	
	public void setUserMenuDao(UserMenuDao userMenuDao) {
		this.userMenuDao = userMenuDao;
		setBaseDao(userMenuDao);
	}
	
	@Override
	public void setBaseDao(BaseDao baseDao) {
		this.dao = baseDao;
	}
	@Override
	public List<UserMenu> byUserMenu(Long id) {
		HashMap map = new HashMap();
		map.put("uid",id);
		map.put("status", new Byte("0"));
		return (List<UserMenu>)this.userMenuDao.findByHql("FROM UserMenu where user.id=:uid and menu.status=:status", map, 0, 0);
	}

	@Override
	public void updateUserMenus(String menuArray, Long userId) {
		
		HashMap map = new HashMap();
		User user = new User(userId);
		map.put("user", user);
		this.userMenuDao.cudByHql("delete from UserMenu where user=:user", map);
		if(menuArray!=null&&menuArray.length()>0){
			String[] array = menuArray.split(",");
			Menu menu;
			UserMenu userMenu;
			for(String id:array){
				menu = new Menu();
				menu.setId(Long.parseLong(id));
				userMenu = new UserMenu();
				userMenu.setUser(user);
				userMenu.setMenu(menu);
				this.userMenuDao.saveOrUpdate(userMenu);
			}
		}
	}
	
}
