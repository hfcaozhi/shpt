package com.bless.ospm.action;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bless.common.Constants;
import com.bless.common.Message;
import com.bless.common.action.BaseAction;
import com.bless.login.service.LoginService;
import com.bless.ospm.dto.LoginInfoEditDto;
import com.bless.ospm.model.base.User;

public class LoginInfoAction extends BaseAction{
	private static final long serialVersionUID = -1590232654593040993L;
	
	Logger log = LoggerFactory.getLogger(LoginInfoAction.class);
	
	private LoginService loginService;

	private User loginInfo;
	private LoginInfoEditDto loginInfoEditDto;
	
	/**
	 * 
	 * Function  : 进入帐号管理主页
	 * @author   : 
	 * @return
	 */
	public String goLoginInfoMain(){
		return SUCCESS;
	}
	
	/**
	 * 
	 * Function  : 执行帐号添加
	 * @author   : bless<505629625@qq.com>
	 * @return
	 * @throws IOException 
	 */
	public void doLoginInfoAdd() throws IOException{
		log.info("method doLoginInfoAdd() called.");
		//执行添加操作
		String result = loginService.insertLoginInfo(loginInfo);
		if(result!=null){
			//如果有返回消息，则将消息发回页面
			response.setCharacterEncoding(Constants.ENCODING_UTF8);
			response.getWriter().print(JSONObject.fromObject(new Message(result)));
		}
	}
	
	/**
	 * 
	 * Function  : 执行帐号查询
	 * @author   : bless<505629625@qq.com>
	 * @throws IOException
	 */
	public void doLoginInfoSearch() throws IOException{
		log.info("method doLoginInfoSearch() called.");
		JSONObject datas = loginService.insertLoginInfo(request);
		
		response.setCharacterEncoding(Constants.ENCODING_UTF8);
		response.getWriter().print(datas.toString());
	}
	
	/**
	 * author  : Bless
	 * about version ：1.00
	 * create time   : 2011-5-19-下午05:07:35
	 * Description ：修改用户帐号信息
	 * @throws IOException 
	 */
	public void doLoginInfoEdit() throws IOException{
		log.info("method doLoginInfoEdit() called.");
		String result = loginService.updateLoginInfo(loginInfoEditDto);
		if(result!=null){
			//如果有返回消息，则将消息发回页面
			response.setCharacterEncoding(Constants.ENCODING_UTF8);
			response.getWriter().print(JSONObject.fromObject(new Message(result)));
		}
	}
	
	/**
	 * author  : Bless
	 * about version ：1.00
	 * create time   : 2011-5-19-下午05:07:35
	 * Description ：删除用户帐号信息
	 * @throws IOException 
	 */
	public void doLoginInfoDelete() throws IOException{
		log.info("method doLoginInfoDelete() called.");
		String loginId = request.getParameter("loginId");
		
		String result = loginService.updateLoginInfoStatus(loginId, new Byte("3"));
		if(result!=null){
			//如果有返回消息，则将消息发回页面
			response.setCharacterEncoding(Constants.ENCODING_UTF8);
			response.getWriter().print(JSONObject.fromObject(new Message(result)));
		}
	}
	
	/**
	 * author  : Bless
	 * about version ：1.00
	 * create time   : 2011-5-19-下午05:07:35
	 * Description ：启用用户帐号信息
	 * @throws IOException 
	 */
	public void doLoginInfoAvailable() throws IOException{
		log.info("method doLoginInfoAvailable() called.");
		String loginId = request.getParameter("loginId");
		
		String result = loginService.updateLoginInfoStatus(loginId, new Byte("0"));
		if(result!=null){
			//如果有返回消息，则将消息发回页面
			response.setCharacterEncoding(Constants.ENCODING_UTF8);
			response.getWriter().print(JSONObject.fromObject(new Message(result)));
		}
	}
	
	/**
	 * author  : Bless
	 * about version ：1.00
	 * create time   : 2011-5-19-下午05:07:35
	 * Description ：禁用用户帐号信息
	 * @throws IOException 
	 */
	public void doLoginInfoInvalid() throws IOException{
		log.info("method doLoginInfoInvalid() called.");
		String loginId = request.getParameter("loginId");
		
		String result = loginService.updateLoginInfoStatus(loginId, new Byte("1"));
		if(result!=null){
			//如果有返回消息，则将消息发回页面
			response.setCharacterEncoding(Constants.ENCODING_UTF8);
			response.getWriter().print(JSONObject.fromObject(new Message(result)));
		}
	}
	
	/*
	 * -----------getter,setter--------------
	 */
	
	public LoginInfoEditDto getLoginInfoEditDto() {
		return loginInfoEditDto;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public User getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(User loginInfo) {
		this.loginInfo = loginInfo;
	}

	public void setLoginInfoEditDto(LoginInfoEditDto loginInfoEditDto) {
		this.loginInfoEditDto = loginInfoEditDto;
	}
}
