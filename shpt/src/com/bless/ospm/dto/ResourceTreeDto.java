package com.bless.ospm.dto;

import java.util.ArrayList;
import java.util.List;

import com.bless.ospm.model.base.Resource;

/**
 * 资源树
 * 
 * @author abina
 * 
 */
public class ResourceTreeDto {
	private Resource resource;
	private List<Operate> op;
	private List<ResourceTreeDto> children = new ArrayList<ResourceTreeDto>();


	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public List<Operate> getOp() {
		return op;
	}

	public void setOp(List<Operate> op) {
		this.op = op;
	}

	public List<ResourceTreeDto> getChildren() {
		return children;
	}

	public void setChildren(List<ResourceTreeDto> children) {
		this.children = children;
	}

}
