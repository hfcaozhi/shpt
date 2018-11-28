package com.bless.ospm.service.impl;

import net.sf.json.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"/com/bless/common/spring/springApplicationContext.xml",
		"/com/bless/*/spring/spring-*-*.xml",
		"/com/bless/common/spring/applicationContext-security.xml" })
public class ResourceService {
	@Autowired
	com.bless.ospm.service.ResourceService service;

	@Test
	public void findResourceTree() {
		JSONObject jo = new JSONObject();
		System.out.println(jo.fromObject(service.findResourceTree()));
	}
}
