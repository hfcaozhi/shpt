package com.bless.ospm.action;

import java.io.IOException;
import java.util.HashMap;

import net.sf.json.JSONObject;

import com.bless.common.action.BaseAction;
import com.bless.common.util.MD5;
import com.bless.common.vo.Result;
import com.bless.ospm.Constant4Ospm;
import com.bless.ospm.model.base.Role;
import com.bless.ospm.model.base.User;
import com.bless.ospm.service.UserService;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

public class UserAction extends BaseAction{
	/**1
	 * 
	 */
	private static final long serialVersionUID = -8035077973837327699L;

	Logger log = LoggerFactory.getLogger(UserAction.class);

	private UserService userService;
	/** 组织编号 */
	private Long oid;
	private String pass; 
	private Long id;
	private String userName;
	private String nickName;
	private Byte status;
	private Long role;
	private Long member;
	public String toUserPage() {
		return SUCCESS;
	}

	/**
	 * 
	 * Function : 用户列表
	 * 
	 * @return
	 * @throws IOException
	 */
	public void userJS() throws IOException {
		log.info("method userJS() called."+this.userName);
		Page<User> page = wrapPage();
		HashMap proMap = new HashMap();
		if(userName!=null&&!userName.trim().equals("")){
			userName = userName.trim();
			proMap.put("userName", "%"+userName+"%");
			page = userService
					.findByHqlPage(
							"FROM User where userName like:userName",
							proMap, page);
			
		}else{
			page = userService.findByHqlPage("FROM User ", proMap, page);
	
		}
		Role role;

		for (User u : page.getRs()) {
			u.setUserMenus(null);
			u.setUserOrgRefs(null);
			if(u.getUserRoleRefs()!=null&&u.getUserRoleRefs().size()>0){
				role = u.getUserRoleRefs().iterator().next().getRole();
				u.setRoleId(role.getId());
				u.setRoleName(role.getName());
			}
			u.setUserRoleRefs(null);
		}
		Result rs = new Result();
		rs.setPage(page);
		rs.setStatus(0);
		this.writeMsg(getDataGridMessage(rs.getPage()));
	}
	// 添加用户
	public void userAddJS() throws IOException {
		Result rs = new Result();
		try {
			User user = new User();
			user.setNickName(this.nickName);
			user.setUserName(this.userName);
			user.setPassword(MD5.getMd5(this.pass));
			user.setStatus(this.status);
			user.setRoleId(this.role);
			log.info("userAddJS() "+user.toString());
			rs.setMessage(this.userService.addUser(user));
			this.userService.updateMember(this.userName, this.member);
		} catch (RuntimeException e) {
			e.printStackTrace();
			rs.setStatus(-1);
			rs.setMessage("添加失败,请稍候重试."); 
		}

		String message = JSONObject.fromObject(rs).toString();
		log.info(message);
		this.writeMsg(message);
	}

	// 修改用户
	public void userUpdateJS() throws IOException {
		Result rs = new Result();
		try {
			User user = new User();
			user.setId(this.id);
			user.setNickName(this.nickName);
			user.setUserName(this.userName);
			user.setStatus(this.status);
			user.setRoleId(this.role);
			user.setPassword(this.pass);
			log.info("userAddJS() "+user.toString());
			rs.setMessage(this.userService.updateUser(user));
			this.userService.updateMember(this.userName, this.member);
		} catch (RuntimeException e) {
			e.printStackTrace();
			rs.setStatus(-1);
			rs.setMessage("修改失败,请稍候重试.");
		}

		String message = JSONObject.fromObject(rs).toString();
		log.info(message);
		this.writeMsg(message);
	}

	// 删除用户
	public void userDelJS() throws IOException {
		log.info("userDelJs()"+this.id);
		Result rs = new Result();
		try {
			rs.setMessage(this.userService.delUser(id));
			rs.setStatus(0);
		} catch (RuntimeException e) {
			e.printStackTrace();
			rs.setStatus(-1);
			rs.setMessage("删除失败,请稍候重试.");
		}
		String message = JSONObject.fromObject(rs).toString();
		log.info(message);
		this.writeMsg(message);
	}
	/**
	 * 查询该用户名是否存在
	 */
	public void userCountJS() throws IOException{
		log.info("method userLoadJS()"+this.userName);
		int count = this.userService.isRegItemExist(this.userName);
		log.info(count+"");
		response.getWriter().print(count);
	}
	/**
	 * 修改密码
	 * 
	 * @throws IOException
	 */
	public void userUpdatePassJS() throws IOException {
		log.info("method userUpdatePassJS() called.");
		HashMap proMap = new HashMap();

		proMap.put("pass", MD5.getMd5(pass));
		proMap.put("uid", ((User) session
				.getAttribute(Constant4Ospm.SESSION_USER_ID)).getId());
		Result rs = new Result();
		try {
			userService.cudByHql(
					"update User set password=:pass where id=:uid", proMap);
			rs.setMessage("修改成功");
		} catch (RuntimeException e) {
			e.printStackTrace();
			rs.setStatus(-1);
			rs.setMessage("修改失败,请稍候重试");
		}
		String message = JSONObject.fromObject(rs).toString();
		log.info(message);
		this.writeMsg(message);
	}
	
	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Long getRole() {
		return role;
	}

	public void setRole(Long role) {
		this.role = role;
	}

	public Long getMember() {
		return member;
	}

	public void setMember(Long member) {
		this.member = member;
	}
}
