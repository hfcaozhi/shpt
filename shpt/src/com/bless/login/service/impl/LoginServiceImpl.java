package com.bless.login.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.bless.common.Constants;
import com.bless.common.dao.BaseDao;
import com.bless.common.service.impl.BaseServiceImpl;
import com.bless.common.util.MD5;
import com.bless.login.exception.LoginServiceException;
import com.bless.login.service.LoginService;
import com.bless.ospm.dao.UserDao;
import com.bless.ospm.dto.LoginInfoEditDto;
import com.bless.ospm.model.base.Resource;
import com.bless.ospm.model.base.User;

public class LoginServiceImpl extends BaseServiceImpl implements LoginService{
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
		setBaseDao(userDao);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doLogin(User loginInfo, HttpSession session) {
		if(loginInfo.getUserName()==null)throw new LoginServiceException("帐号或密码错误");
		//使用条件查询获取帐号密码
		Map<String, Object> proMap = new HashMap<String, Object>();
		proMap.put("userName", loginInfo.getUserName().trim());
		//密码需要MD5转换
		proMap.put("password", MD5.getMd5(loginInfo.getPassword()));
		//用户状态：启用
		proMap.put("status", new Byte("0"));
		List<User> lstLoginInfo = (List<User>) userDao.findByPropertiesEq(User.class, proMap, null, 0, 0);
		//如果帐号不存在则抛出异常
		if(lstLoginInfo==null||lstLoginInfo.size()!=1){
			throw new LoginServiceException("帐号或密码错误");
		}
		//否则保存帐号信息到session中
		session.setAttribute(Constants.SESSION_LOGIN, lstLoginInfo.get(0));
	}

	@Override
	public List<Resource> getUserPrivilege(User loginInfo) {
		List<Resource> ps = new ArrayList<Resource>();
		
		return ps;
	}

	@Override
	public JSONObject insertLoginInfo(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateLoginInfo(LoginInfoEditDto loginInfoEditDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateLoginInfoStatus(String loginId, Byte i) {
		HashMap map = new HashMap();
		map.put("s", i);
		userDao.cudByHql("update User set status=:s", map);
		return null;
	}

	@Override
	public String insertLoginInfo(User loginInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBaseDao(BaseDao baseDao) {
		// TODO Auto-generated method stub
		this.dao = baseDao;
		
	}
	
	
}
