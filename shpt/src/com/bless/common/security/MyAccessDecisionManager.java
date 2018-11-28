package com.bless.common.security;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

//3
public class MyAccessDecisionManager implements AccessDecisionManager {
	Logger log = LoggerFactory.getLogger(MyAccessDecisionManager.class);
	MyUserDetailServiceImpl myUserDetailServiceImpl;
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		if(configAttributes == null) {
			log.info("该请求资源没有在数据库表中定义，无法进行权限控制");
			return;
		}
		
		 Collection<GrantedAuthority> as = (Collection<GrantedAuthority>) myUserDetailServiceImpl.loadUserByUsername(authentication.getName()).getAuthorities(); 
		//所请求的资源拥有的权限(一个资源对多个权限)
		Iterator<ConfigAttribute> iterator = configAttributes.iterator();
		while(iterator.hasNext()) {
			ConfigAttribute configAttribute = iterator.next();
			//访问所请求资源所需要的权限
			String needPermission = configAttribute.getAttribute();
			log.info("needPermission is " + needPermission+";"+authentication.getName());
			
			if(!(authentication.getPrincipal() instanceof User)){
				authentication.setAuthenticated(false);
				throw new InsufficientAuthenticationException("");	
			}
			if(authentication.getPrincipal() instanceof User&&needPermission.equals("首页面"))return;
//			AuthenticationException
			//用户所拥有的权限authentication
//			for(GrantedAuthority ga : authentication.getAuthorities()) {
			for(GrantedAuthority ga : as) {
				if(needPermission.equals(ga.getAuthority())) {
					return;
				}
			}
		}
		log.info("没有权限访问！");
		//没有权限让我们去捕捉
		//throw new AccessDeniedException(" 没有权限访问！");
	}

	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

	public MyUserDetailServiceImpl getMyUserDetailServiceImpl() {
		return myUserDetailServiceImpl;
	}

	public void setMyUserDetailServiceImpl(
			MyUserDetailServiceImpl myUserDetailServiceImpl) {
		this.myUserDetailServiceImpl = myUserDetailServiceImpl;
	}
	
}
