package com.bless.ospm.service;

import com.bless.common.service.BaseService;
import com.bless.ospm.model.base.User;

public interface UserService extends BaseService {
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public String addUser(User user);
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	public String updateUser(User user);
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public String delUser(Long id);
	/**
	 * 修改人员表中的用户名
	 * @param login
	 * @param id
	 */
	public void updateMember(String login,Long id);
	/**
	 * 查询该用户是否已经存在
	 * @param userName
	 * @return
	 */
	public int isRegItemExist(String userName);
	/**
	 * 获取该用户的角色名称
	 * @param id
	 * @return
	 */
	public String getRoleName(String id);
}
