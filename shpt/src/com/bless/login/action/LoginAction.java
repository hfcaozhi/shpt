package com.bless.login.action;

import java.util.Enumeration;
import com.bless.common.action.BaseAction;
import com.bless.login.service.LoginService;
import com.bless.ospm.model.base.User;

/**
 * 
 * @author  : lala
 * @version : 1.00
 * Create Time : 2011-5-16-下午01:36:43
 * Description : 
 *             登录Action 
 * History：
 *  Editor       version      Time               Operation    Description*
 *  
 *
 */
public class LoginAction extends BaseAction{
	private static final long serialVersionUID = -8263264432046589057L;
	//注入Service
	private LoginService loginService;

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	//form bean
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * author  : Bless
	 * about version ：1.00
	 * create time   : 2011-5-16-下午01:37:09
	 * Description ：登录操作
	 */
	public void doLogin(){
		loginService.doLogin(user, session);
	}
	
	/**
	 * Function  : 注销操作
	 * @author   : bless<505629625@qq.com>
	 */
	public String doLogout(){
		Enumeration<?> enums = session.getAttributeNames();
		while(enums.hasMoreElements()){
			session.removeAttribute(enums.nextElement().toString());
		}
		return SUCCESS;
	}
	
}
