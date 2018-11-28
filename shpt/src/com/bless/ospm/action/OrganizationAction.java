package com.bless.ospm.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bless.common.Constants;
import com.bless.common.action.BaseAction;
import com.bless.common.cache.CacheSystem;
import com.bless.common.util.MD5;
import com.bless.common.vo.Result;
import com.bless.ospm.dto.NodeDto;
import com.bless.ospm.dto.OrgTreeDto;
import com.bless.ospm.model.base.Organization;
import com.bless.ospm.model.base.User;
import com.bless.ospm.service.OrganizationService;

public class OrganizationAction extends BaseAction{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1735291992875882554L;

	Logger log = LoggerFactory.getLogger(OrganizationAction.class);
	
	private OrganizationService organizationService;
	private CacheSystem cacheSystem;

	private String orgArray;
	private Long uid;
	private Long id;
	private Long parentId;
	private String name;
	private String description;
	
	/**
	 * 结构组织页面
	 */
	public String toOrgPage(){
		log.info("method toOrgPage() called.");
		return SUCCESS;
	}
	
	
	/**
	 * 
	 * Function  : 结构组织数据查询
	 * @return
	 * @throws IOException 
	 */
	public void orgJS() throws IOException{
		log.info("method orgJS() called.");
		OrgTreeDto dto = this.organizationService.orgAll();
		String message = JSONObject.fromObject(dto).toString();
		response.setCharacterEncoding(Constants.ENCODING_UTF8);
		//这里如果用text/html的话则显示不出来
		response.setContentType("text/json");
		response.getWriter().print(message);
//		log.info(message);
//		this.writeMsg(message);
		
	}

	/**
	 * 查询用户组织
	 * @throws IOException
	 */
	public void orgUserJS() throws IOException{
		log.info("method orgUserJS()");
		List<NodeDto> nodes = this.organizationService.findUserOrg(this.uid);
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[] { "hibernateLazyInitializer", "handler" });
		String message = JSONArray.fromObject(nodes,config).toString();
		log.info(message);
		this.writeMsg(message);
		
	}
	/**
	 * 修改用户组织
	 * @throws IOException
	 */
	public void orgUpdateUserJS() throws IOException{
		log.info("method orgUpdateUserJS()");
		Result rs = new Result();
		try {
			this.organizationService.updateUserOrg(this.uid, orgArray);
			rs.setMessage("操作成功");
		} catch (RuntimeException e) {
			e.printStackTrace();
			rs.setMessage("操作失败,请稍候重试");
		}
		String message = JSONObject.fromObject(rs).toString();
		log.info(message);
		this.writeMsg(message);
	}
	/**
	 * 修改结构组织
	 * @throws IOException
	 */
	public void orgUpdateJS() throws IOException{
		log.info("orgUpdateJS()"+id+"="+name+"="+description+"="+parentId);
		Result rs = new Result();
		try {
			Organization organization = new Organization();
			organization.setDescription(description);
			organization.setName(name);
			if(this.parentId!=null&&this.parentId!=0){
				organization.setParentId(parentId);
			}
			organization.setId(id);
			rs.setMessage(this.organizationService.updateOrg(organization));
			relaod();
		} catch (RuntimeException e) {
			e.printStackTrace();
			rs.setStatus(-1);
			rs.setMessage("修改失败,请稍候重试."); 
		}

		String message = JSONObject.fromObject(rs).toString();
		log.info(message);
		this.writeMsg(message);
	}
	/**
	 * 添加结构组织
	 * @throws IOException
	 */
	public void orgAddJS() throws IOException{
		log.info("orgAddJS()"+id+"="+name+"="+description+"="+parentId);
		Result rs = new Result();
		try {
			Organization organization = new Organization();
			organization.setDescription(description);
			organization.setName(name);
			organization.setParentId(parentId);
			this.organizationService.saveOrUpdate(organization);
			relaod();
			rs.setMessage("添加成功");
		} catch (RuntimeException e) {
			e.printStackTrace();
			rs.setStatus(-1);
			rs.setMessage("添加失败,请稍候重试."); 
		}
		String message = JSONObject.fromObject(rs).toString();
		log.info(message);
		this.writeMsg(message);
	}
	/**
	 * 删除结构组织
	 * @throws IOException
	 */
	public void orgDelJS() throws IOException{
		log.info("orgDelJS()"+id);
		Result rs = new Result();
		try {
			rs.setMessage(this.organizationService.delOrg(id));
			relaod();
		} catch (RuntimeException e) {
			e.printStackTrace();
			rs.setStatus(-1);
			rs.setMessage("删除失败,请稍候重试."); 
		}
		String message = JSONObject.fromObject(rs).toString();
		log.info(message);
		this.writeMsg(message);
	}
	public void relaod(){
		this.cacheSystem.synOrg();
	}
	public void setCacheSystem(CacheSystem cacheSystem) {
		this.cacheSystem = cacheSystem;
	}


	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}


	public String getOrgArray() {
		return orgArray;
	}


	public void setOrgArray(String orgArray) {
		this.orgArray = orgArray;
	}


	public Long getUid() {
		return uid;
	}


	public void setUid(Long uid) {
		this.uid = uid;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getParentId() {
		return parentId;
	}


	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}




}
