package com.bless.common.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.bless.ospm.model.base.Resource;
import com.bless.ospm.service.ResourceService;

//1 加载资源与权限的对应关系
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	Logger log = LoggerFactory.getLogger(MySecurityMetadataSource.class);
	//由spring调用
	public MySecurityMetadataSource(ResourceService resourceService) {
		this.resourceService = resourceService;
		loadResourceDefine();
	}

	private ResourceService resourceService;
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	
	public ResourceService getResourceService() {
		return resourceService;
	}

	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}
	//加载所有资源与权限的关系
	private void loadResourceDefine() {
		if(resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			List<Resource> resources = this.resourceService.findAllLeaf();
			if(resources==null)return;
			for (Resource resource : resources) {
				Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
				//以权限名封装为Spring的security Object
				ConfigAttribute configAttribute = new SecurityConfig(resource.getName());
				configAttributes.add(configAttribute);
				resourceMap.put(resource.getActionUrl(), configAttributes);
			}
		}
		
//		Set<Entry<String, Collection<ConfigAttribute>>> resourceSet = resourceMap.entrySet();
//		Iterator<Entry<String, Collection<ConfigAttribute>>> iterator = resourceSet.iterator();
		
	}
	//返回所请求资源所需要的权限
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		
		String requestUrl = ((FilterInvocation) object).getRequestUrl().split("\\?")[0];
		log.info("requestUrl is " + requestUrl);
		if(resourceMap == null) {
			loadResourceDefine();
		}
		Collection<ConfigAttribute> rs = resourceMap.get(requestUrl); 
		if(rs==null){rs = new ArrayList<ConfigAttribute>();
			rs.add(new SecurityConfig("ROLE_NO_USER"));
		}
		return rs;
	}

}
