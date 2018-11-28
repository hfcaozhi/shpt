package com.bless.ospm.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bless.common.Constants;
import com.bless.common.action.BaseAction;
import com.bless.common.vo.Result;
import com.bless.ospm.dto.RoleDto;
import com.bless.ospm.model.base.Role;
import com.bless.ospm.service.RoleService;

public class RoleAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 271031480205846303L;

	Logger log = LoggerFactory.getLogger(RoleAction.class);

	private RoleService roleService;
	/** 角色编号*/
	private Long id;
	/**
	 * 名称
	 */
	private String name;
	
	private Byte status;
	
	private String description;
	/**
	 * 从前面返回过来的修改过状态的权限树
	 */
	private String privilegeTreeJS;

	public String toRolePage() {
		return SUCCESS;
	}

	/**
	 * 
	 * Function : 执法车列表
	 * 
	 * @return
	 * @throws IOException
	 */
	public void roleJS() throws IOException {
		log.info("method roleJS() called.");
		Page page = wrapPage();
		
		HashMap proMap = new HashMap();
		
		if(name!=null&&!name.trim().equals("")){
			name = name.trim();
			proMap.put("name", "%"+name+"%");
			page = roleService
					.findByHqlPage(
							"FROM Role where name like:name",
							proMap, page);
			
		}else{
			page = roleService
					.findByHqlPage(
							"FROM Role  ",
							proMap, page);
	
		}
		
				
		List<RoleDto> roles = new ArrayList<RoleDto>();
		for (Object r : page.getRs()) {
			RoleDto dto = new RoleDto();
			dto.setId(((Role)r).getId());
			dto.setName(((Role)r).getName());
			dto.setDescription(((Role)r).getDescription());
//			String s = Constant4Ospm.STATUS_NORMAL;
//			if(((Role)r).getStatus()==1){
//				s = Constant4Ospm.STATUS_UNUSE;
//			}else if(((Role)r).getStatus()==2){
//				s = Constant4Ospm.STATUS_DEL;
//			} 
			dto.setStatus(((Role)r).getStatus());
			
			roles.add(dto);
		}
		page.setRs(roles);

		response.setCharacterEncoding(Constants.ENCODING_UTF8);
		response.setContentType("text/json");
		Result rs = new Result();
		rs.setPage(page);
		rs.setStatus(0);

//		String message = JSONObject.fromObject(rs).toString();
		//log.info(message);
//		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("rows",JSONArray.fromObject(rs.getPage().getRs()) );
//		map.put("total",page.getTotal());
//		JSONObject jo = JSONObject.fromObject(map);
//		log.info(jo.toString());
		response.getWriter().print(getDataGridMessage(rs.getPage()));
	}

	
	//添加角色
	public void roleAddJS() throws IOException{
		log.info("method roleAddJS() called.");
		String message;
		Result rs = new Result();
		Role role = new Role();
		
		role.setName(name);
		role.setDescription(description);
		role.setStatus(status);
		
		try{
			roleService.saveOrUpdate(role);
		}catch(RuntimeException e){
			e.printStackTrace();
			rs.setStatus(-1);
			rs.setMessage("添加失败,请稍候重试.");
		}
		
		response.setCharacterEncoding(Constants.ENCODING_UTF8);
		response.setContentType("text/json");
		message = JSONObject.fromObject(rs).toString();
		log.info(message);
		response.getWriter().print(message);
	}
	//修改角色
	public void roleUpdateJS() throws IOException{
		log.info("method roleUpdateJS() called.");
		
		String message;
		Result rs = new Result();
		HashMap proMap = new HashMap();
		proMap.put("id", id);
		proMap.put("name", name);
		proMap.put("description", description);
		proMap.put("status", status);
		
		try{
			
			//保存基本信息
			roleService.cudByHql("update Role set name=:name,description=:description,status=:status where id=:id", proMap);
			//保存权限
			roleService.updatePrivilege(id,privilegeTreeJS);
			
		}catch(RuntimeException e){
			e.printStackTrace();
			rs.setStatus(-1);
			rs.setMessage("修改失败,请稍候重试.");
		}
		
		response.setCharacterEncoding(Constants.ENCODING_UTF8);
		response.setContentType("text/json");
		message = JSONObject.fromObject(rs).toString();
		log.info(message);
		response.getWriter().print(message);
	}
	//删除角色
	public void roleDelJS() throws IOException{
		log.info("method roleDelJS() called.");
		String message;
		Result rs = new Result();
		try{
			roleService.delete(Role.class, id);	
			rs.setStatus(0);
		}catch(RuntimeException e){
			e.printStackTrace();
			rs.setStatus(-1);
			rs.setMessage("删除失败,请稍候重试.");
		}
		response.setCharacterEncoding(Constants.ENCODING_UTF8);
		response.setContentType("text/json");
		message = JSONObject.fromObject(rs).toString();
		log.info(message);
		response.getWriter().print(message);
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrivilegeTreeJS() {
		return privilegeTreeJS;
	}

	public void setPrivilegeTreeJS(String privilegeTreeJS) {
		this.privilegeTreeJS = privilegeTreeJS;
	}
	
	
	
}
