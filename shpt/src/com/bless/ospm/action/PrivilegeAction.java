package com.bless.ospm.action;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;

import com.bless.common.action.BaseAction;
import com.bless.ospm.model.base.Resource;
import com.bless.ospm.service.ResourceService;

public class PrivilegeAction extends BaseAction{
	private static final long serialVersionUID = 776993055298267651L;
	Logger log = LoggerFactory.getLogger(PrivilegeAction.class);
	
	private ResourceService resourceService;

	public ResourceService getResourceService() {
		return resourceService;
	}

	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public void doGetPrivilegeTree() throws IOException{
		log.info("method doGetPrivilegeTree() called.");
		String sId = request.getParameter("id");
		int treeId = 0;
		if(sId!=null&&!"".equals(sId)){
			treeId = Integer.parseInt(sId);
		}
		List<Resource> lstPriv = resourceService.findPrivilegeTreeById(treeId);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(JSONArray.fromObject(lstPriv).toString());
	}
	
	public String goPrivilegeMain(){
		return SUCCESS;
	}
	
}
