package com.bless.login.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.bless.common.service.BaseService;
import com.bless.ospm.dto.LoginInfoEditDto;
import com.bless.ospm.model.base.Resource;
import com.bless.ospm.model.base.User;

public interface LoginService extends BaseService{
	public void doLogin(User loginInfo,HttpSession session);
	/**
	 * 得到用户的所有权限。
	 * @return
	 */
	public List<Resource> getUserPrivilege(User loginInfo);
	public JSONObject insertLoginInfo(HttpServletRequest request);
	public String updateLoginInfo(LoginInfoEditDto loginInfoEditDto);
	public String updateLoginInfoStatus(String loginId, Byte i);
	public String insertLoginInfo(User loginInfo);
}
