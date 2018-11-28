package com.bless.ospm.service.impl;

import java.util.HashMap;
import java.util.List;

import com.bless.common.dao.BaseDao;
import com.bless.common.service.impl.BaseServiceImpl;
import com.bless.common.util.MD5;
import com.bless.ospm.Constant4Ospm;
import com.bless.ospm.dao.UserDao;
import com.bless.ospm.model.base.Role;
import com.bless.ospm.model.base.User;
import com.bless.ospm.model.base.UserRoleRef;
import com.bless.ospm.service.UserService;

public class UserServiceImpl extends BaseServiceImpl implements UserService{
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
		setBaseDao(userDao);
	}

	@Override
	public void setBaseDao(BaseDao baseDao) {
		// TODO Auto-generated method stub
		this.dao = baseDao;
	}

	@Override
	public String addUser(User user) {
		this.userDao.saveOrUpdate(user);
		if(user.getRoleId()!=null){
			HashMap map = new HashMap();
			map.put("userName", user.getUserName());
			List<User> users = (List<User>)this.userDao.findByHql("from User where userName=:userName", map, 0, 0);
			UserRoleRef urr = new UserRoleRef();
			urr.setUser(users.get(0));
			urr.setRole(new Role(user.getRoleId()));
			this.userDao.saveOrUpdate(urr);
		}
		return "添加成功";
	}

	@Override
	public String updateUser(User user) {
		User newUser = (User)this.userDao.load(User.class, user.getId());
		newUser.setUserName(user.getUserName());
		newUser.setNickName(user.getNickName());
		newUser.setStatus(user.getStatus());
		if(user.getPassword()!=null&&!user.getPassword().trim().equals("")){
			newUser.setPassword(MD5.getMd5(user.getPassword()));
		}
		this.userDao.update(newUser);
		if(user.getRoleId()!=null){
			HashMap map = new HashMap();
			map.put("roleId", new Role(user.getRoleId()));
			map.put("userId", user);
			this.userDao.cudByHql("update UserRoleRef set role=:roleId where user=:userId", map);
		}
		return "修改成功";
	}

	@Override
	public String delUser(Long id) {
		HashMap map = new HashMap();
		map.put("status", Constant4Ospm.STATUS_CODE_DEL);
		map.put("id", id);
		this.userDao.cudByHql("update User set status=:status where id=:id", map);
		return "删除成功";
	}

	@Override
	public void updateMember(String login, Long id) {
		if(id!=0){
			HashMap map = new HashMap();
			map.put("login", login);
			map.put("id", id);
			this.userDao.cudByHql("update Member set login=:login where id=:id", map);
		}
		
	}

	@Override
	public int isRegItemExist(String userName) {
		HashMap map = new HashMap();
		map.put("userName", userName);
		Object obj = this.getCountByHql("from User where userName like :userName",map , 0, 0);
		return Integer.parseInt(obj.toString());
	}
	/**
	 * 获取该用户的角色名称
	 * @param id
	 * @return
	 */
	@Override
	public String getRoleName(String id) {
		String sql="SELECT role.`name` from `user`  "+
                    " inner join user_role_ref on user_role_ref.user_id=`user`.id "+
					" inner join role on role.id=user_role_ref.role_id "+
					" where user.id  =:id ";
		HashMap map = new HashMap();
		
		map.put("id", id);
		List<String> listRoleName = (List<String>) this.findBySql(sql, map, 0, 0);
		if (listRoleName.size()>0) {
			return listRoleName.get(0);
		}
		return null;
	}

}
