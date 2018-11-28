package com.bless.ospm.action;

import java.io.IOException;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bless.common.Constants;
import com.bless.common.action.BaseAction;
import com.bless.common.cache.CacheSystem;
import com.bless.ospm.model.base.Resource;
import com.bless.ospm.service.ResourceService;

public class ResourceAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2197829822078541961L;

	Logger log = LoggerFactory.getLogger(ResourceAction.class);

	private ResourceService resourceService;
	private Resource resource;
	private CacheSystem cacheSystem;
	/** 组织编号 */
	private Long oid;

	public String toResourcePage() {
		return SUCCESS;
	}

	/**
	 * 
	 * Function : 用户列表
	 * 
	 * @return
	 * @throws IOException
	 */
	public void resourceJS() throws IOException {
		log.info("method resourceJS() called.");
//		Page<Resource> page = wrapPage();
//		
//		HashMap proMap = new HashMap();
//		
//		page = resourceService
//				.findByHqlPage(
//						"FROM Resource ",
//						null, page);
//		
//		for(Resource u :page.getRs()){
//			u.setRoleResourceRefs(null);
//		}
//
		response.setCharacterEncoding(Constants.ENCODING_UTF8);
		response.setContentType("text/json");
//		Result rs = new Result();
//		rs.setPage(page);
//		rs.setStatus(0);
//
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{"hibernateLazyInitializer","handler"});
		
//		String message = JSONObject.fromObject(rs,config).toString();
		String message = JSONObject.fromObject(resourceService.findResourceTree(),config).toString();
		log.info(message);
		response.getWriter().print(message);
	}

	// 添加权限
	public void resourceAddJS() throws IOException {
		log.info("resourceAddJS()" + this.resource);
		String message = "添加成功";
		try{
			this.resourceService.insert(this.resource);
			reload();
		}catch(RuntimeException e){
			e.printStackTrace();
			message = "添加失败,请稍候重试.";
		}
		response.getWriter().print(message);
	}

	// 修改权限
	public void resourceUpdateJS() throws IOException {
		log.info("resourceUpdateJS()" + this.resource);
		String message;
		try{
			message = this.resourceService.updateResouce(this.resource);
			reload();
		}catch(RuntimeException e){
			e.printStackTrace();
			message = "修改失败,请稍候重试.";
		}
		response.getWriter().print(message);

	}

	// 删除权限
	public void resourceDelJS() throws IOException {
		log.info("resourceDelJS()" + this.resource);
		String message = "删除成功";
		try{
			this.resourceService.delResource(this.resource);
			reload();
		}catch(RuntimeException e){
			e.printStackTrace();
			message = "删除失败,请稍候重试.";
		}
		response.getWriter().print(message);
	}
	public void resourceTreeJS() throws IOException {
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[] { "hibernateLazyInitializer", "handler" });
		String message = JSONObject.fromObject(
				resourceService.findResourceTree(), config).toString();
		log.info(message);
		this.writeMsg(message);
	}

	// 加载所有的权限资源
	public void resourceAllTreeJS() throws IOException {
		log.info("resourceAllTreeJS()");
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[] { "hibernateLazyInitializer", "handler" });

		String message = JSONObject.fromObject(resourceService.resourceTree(),
				config).toString();
		log.info(message);
		this.writeMsg(message);
	}
	/**
	 * 刷新内存中权资源的数据
	 */
	private void reload(){
		this.cacheSystem.synMenuResource();
		this.cacheSystem.synResouce();
	}

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public void setCacheSystem(CacheSystem cacheSystem) {
		this.cacheSystem = cacheSystem;
	}

}
