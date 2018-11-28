package com.bless.ospm.service.impl;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bless.ospm.action.Page;
import com.bless.ospm.model.base.Member;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"/com/bless/common/spring/springApplicationContext.xml",
		"/com/bless/*/spring/spring-*-*.xml",
		"/com/bless/common/spring/applicationContext-security.xml" })
public class MemberService {
	@Autowired
	com.bless.ospm.service.MemberService memberService;
	
	public void find(){
		HashMap map = new HashMap();
		map.put("status", new Byte("0"));
		List<Member> rs = (List<Member>) memberService.findByPropertiesEq(Member.class, map, null, 0, 0);
		
		if(rs==null)System.out.println("no result find.");
		else{
			System.out.println("find result size is "+rs.size());
		}
	}
	
	public void findPage(){
		Page<Member> page = new Page<Member>();
		page.setPageSize(1000);
		HashMap proMap = new HashMap();
		proMap.put("status", new Byte("0"));
		proMap.put("uid", 1L);
		page = memberService.findByHqlPage("FROM Member where  organization in (FROM Organization o where o.parentId in (select uor.organization from UserOrgRef uor where uor.user.id=:uid) or o.id in (select uor.organization from UserOrgRef uor where uor.user.id=:uid))  and status=:status", proMap, page);
		System.out.println(page.getRs().size());
	}
	
	@Test
	public void findPageByOid(){
		Page<Member> page = new Page<Member>();
		page.setPageSize(1000);
		HashMap proMap = new HashMap();
		proMap.put("oid", 2L);
		proMap.put("status", new Byte("0"));
		page = memberService.findByHqlPage("FROM Member where organization in (FROM Organization o where o.parentId in (:oid) or o.id =:oid)  and status=:status", proMap, page);
		System.out.println(page.getRs().size());
	}
	@Test
	public void findAll()
	{
		List<Member> lists =  (List<Member>) memberService.findAll(Member.class);
		System.out.println(lists.size());
		System.out.println(lists.get(0).getName());
		System.out.println(lists.get(1).getName());
	}
	
	
}
