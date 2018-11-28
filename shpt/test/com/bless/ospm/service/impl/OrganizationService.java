package com.bless.ospm.service.impl;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bless.ospm.action.Page;
import com.bless.ospm.model.base.Organization;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"/com/bless/common/spring/springApplicationContext.xml",
		"/com/bless/*/spring/spring-*-*.xml",
		"/com/bless/common/spring/applicationContext-security.xml" })
public class OrganizationService {
	@Autowired
	com.bless.ospm.service.OrganizationService service;
	
	public void findPage(){
		HashMap map = new HashMap();
		map.put("status", new Byte("0"));
		Page<Organization> page = new Page();
		page.setPageSize(1000);
		page = service.findByHqlPage("FROM Organization ", null, page);
		
		List<Organization> rs = page.getRs();
		
		if(rs==null)System.out.println("no result find.");
		else{
			System.out.println("find result size is "+rs.size()+";"+page.getPageCount());
			String message = JSONObject.fromObject(page).toString();
			System.out.println(message);
		}
	}
	@Test
	public void findAllByUserId(){
		HashMap proMap = new HashMap();
		proMap.put("uid", 1L);
//		List<Organization> os = (List<Organization>) service.findByHql(" FROM Organization o where o.parentId in (select uor.organization from UserOrgRef uor where uor.user.id=:uid) ", proMap, 0, 0);
		List<Organization> os = service.findChildrenByUserId(1L);
		System.out.println(os.size());
		Assert.assertNotNull(os);
	}
	@Test
	public void findAll()
	{
		List<Organization>  orgs =  (List<Organization>) service.findAll(Organization.class);
		System.out.println(orgs.size());
	}
	
}
