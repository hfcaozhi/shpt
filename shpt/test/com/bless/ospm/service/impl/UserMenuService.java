package com.bless.ospm.service.impl;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bless.ospm.action.Page;
import com.bless.ospm.model.base.Member;
import com.bless.ospm.model.base.Menu;
import com.bless.ospm.model.base.UserMenu;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"/com/bless/common/spring/springApplicationContext.xml",
		"/com/bless/*/spring/spring-*-*.xml",
		"/com/bless/common/spring/applicationContext-security.xml" })
public class UserMenuService {
	@Autowired
	com.bless.ospm.service.UserMenuService service;
	
	
	@Test
	public void findPage(){
		HashMap map = new HashMap();
		map.put("uid", 1L);
		Page<UserMenu> page = new Page();
		page.setPageSize(1);
		page = service.findByHqlPage("FROM UserMenu where user.id=:uid", map, page);
		
		List<UserMenu> rs = page.getRs();
		for(UserMenu m :page.getRs()){
//			m.setUser(null);
//			m.getMenu().setUserMenus(null);
		}
		
		if(rs==null)System.out.println("no result find.");
		else{
			System.out.println("find result size is "+rs.size()+";"+page.getPageCount());
			String message = JSONObject.fromObject(page).toString();
			System.out.println(message);
		}
	}
}
