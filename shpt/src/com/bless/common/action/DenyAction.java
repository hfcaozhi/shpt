package com.bless.common.action;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bless.common.Constants;
import com.bless.common.vo.Result;

/**
 * 
 * @author  : Bless
 * @version : 1.00
 * Create Time : 2011-5-16-下午01:36:43
 * Description : 
 *             登录Action 
 * History：
 *  Editor       version      Time               Operation    Description*
 *  
 *
 */
public class DenyAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -256099788957895610L;
	Logger log = LoggerFactory.getLogger(DenyAction.class);

	public void deny(){
		log.info("返回【无返回权限】信息");
//		throw new RuntimeException("error");
		response.setStatus(200);
		response.setCharacterEncoding(Constants.ENCODING_UTF8);
		response.setContentType("text/html");
		Result rs = new Result();
		rs.setStatus(-1);
		rs.setMessage("{'message':'无访问权限'}");
		String message = JSONObject.fromObject(rs).toString();
		try {
			response.getWriter().print(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
