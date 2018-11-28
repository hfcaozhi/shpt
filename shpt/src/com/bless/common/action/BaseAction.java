package com.bless.common.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.bless.common.Constants;
import com.bless.common.util.DateJsonValueProcessor;
import com.bless.ospm.Constant4Ospm;
import com.bless.ospm.action.Page;
import com.bless.ospm.model.base.User;
import com.bless.ospm.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware,
		ServletContextAware, ServletResponseAware {
	private static final long serialVersionUID = -1040212988363452551L;

	protected HttpServletRequest request;
	protected ServletContext application;
	protected HttpServletResponse response;
	protected HttpSession session;

	UserService userService;

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		session = this.request.getSession();
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		this.application = arg0;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 从session中得到用户信息
	 */
	public User getUserFromSession() {
		HashMap proMap = new HashMap();
		proMap.put("id", ((User) session
				.getAttribute(Constant4Ospm.SESSION_USER_ID)).getId());

		List<User> rs = (List<User>) userService.findByPropertiesEq(User.class,
				proMap, null, 1, 1);
		if (rs == null || rs.size() == 0)
			return null;
		return rs.get(0);
	}

	public Page wrapPage() {
		Page page = new Page();
		if (request.getParameter("page_no") != null) {
			page.setPageNo(Integer.parseInt(request.getParameter("page_no")));
		} else if (request.getParameter("page") != null) {
			page.setPageNo(Integer.parseInt(request.getParameter("page")));
		}

		if (request.getParameter("page_size") != null) {
			page.setPageSize(Integer.parseInt(request.getParameter("page_size")));
		} else if (request.getParameter("rows") != null) {
			page.setPageSize(Integer.parseInt(request.getParameter("rows")));
		}

		return page;
	}

	/**
	 * 将数据封闭成easyui所使用的json格式
	 * 
	 * @param page
	 * @return
	 */
	public String getDataGridMessage(Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		//注册值处理器，处理JSON中的Date
		DateJsonValueProcessor jsonProcessor = new DateJsonValueProcessor();
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, jsonProcessor);
		map.put("rows", JSONArray.fromObject(page.getRs(),jsonConfig));
		map.put("total", page.getTotal());
		JSONObject jo = JSONObject.fromObject(map);
		return jo.toString();
	}
	public void writeMsg(String message) throws IOException{
		response.setCharacterEncoding(Constants.ENCODING_UTF8);
		response.setContentType("text/html");
		response.getWriter().print(message);
	}
	
}
