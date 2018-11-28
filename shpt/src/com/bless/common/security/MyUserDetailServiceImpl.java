package com.bless.common.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bless.login.exception.LoginServiceException;
import com.bless.ospm.dao.UserDao;
import com.bless.ospm.dao.UserMenuDao;
import com.bless.ospm.dao.UserRoleRefDao;
import com.bless.ospm.model.base.Resource;
import com.bless.ospm.model.base.RoleResourceRef;
import com.bless.ospm.model.base.UserMenu;
import com.bless.ospm.model.base.UserRoleRef;


//2
public class MyUserDetailServiceImpl implements UserDetailsService {
	Logger log = LoggerFactory.getLogger(MyUserDetailServiceImpl.class);
	private UserDao userDao;
	private UserRoleRefDao userRoleRefDao;
	private UserMenuDao userMenuDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public UserMenuDao getUserMenuDao() {
		return userMenuDao;
	}

	public void setUserMenuDao(UserMenuDao userMenuDao) {
		this.userMenuDao = userMenuDao;
	}

	public UserRoleRefDao getUserRoleRefDao() {
		return userRoleRefDao;
	}

	public void setUserRoleRefDao(UserRoleRefDao userRoleRefDao) {
		this.userRoleRefDao = userRoleRefDao;
	}

	//登录验证
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Map proMap= new HashMap();
		proMap.put("userName", username);
		List<com.bless.ospm.model.base.User> users = (List<com.bless.ospm.model.base.User>) this.userDao.findByPropertiesEq(com.bless.ospm.model.base.User.class, proMap, null, 1, 1);
		User userdetail = null;
		if(users!=null&&users.size()>0){
			Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(users.get(0));
			
			boolean enables = true;
			boolean accountNonExpired = true;
			boolean credentialsNonExpired = true;
			boolean accountNonLocked = true;
			//封装成spring security的user
			userdetail = new User(users.get(0).getUserName(), users.get(0).getPassword(), enables, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);	
		}else{
			throw new LoginServiceException("密码错误");
		}
		
		return userdetail;
	}
	
	//取得用户的权限
	private Set<GrantedAuthority> obtionGrantedAuthorities(com.bless.ospm.model.base.User user) {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		
		HashMap proMap = new HashMap();
		proMap.put("uid", user.getId());
		List<UserRoleRef> urr = (List<UserRoleRef>) userRoleRefDao.findByHql("FROM UserRoleRef where user.id=:uid ", proMap, 99999, 1);
		
		if(urr!=null&&urr.size()>0){
			for(UserRoleRef u: urr){
				Iterator<RoleResourceRef> it = u.getRole().getRoleResourceRefs().iterator();
				while(it.hasNext()){
					RoleResourceRef rrr = it.next();
					authSet.add(new GrantedAuthorityImpl(rrr.getResource().getName()));
				}
			}
		}
		//还要把该用户的菜单加载进来，因为给了菜单，我们就认为菜单相关联的权限也一并给了该用户
		List<UserMenu> ums = (List<UserMenu>) userMenuDao.findByHql("FROM UserMenu where user.id=:uid", proMap, 0, 0);
		if(ums!=null){
			for(UserMenu um : ums){
				Resource r = um.getMenu().getResource();
				if(r!=null){
					authSet.add(new GrantedAuthorityImpl(r.getName()));
				}
			}
		}
		return authSet;
	}
}
