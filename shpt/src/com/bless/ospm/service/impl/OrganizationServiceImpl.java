package com.bless.ospm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.aspectj.weaver.patterns.OrTypePattern;

import com.bless.common.dao.BaseDao;
import com.bless.common.service.impl.BaseServiceImpl;
import com.bless.ospm.dao.OrganizationDao;
import com.bless.ospm.dto.MenuDto;
import com.bless.ospm.dto.NodeDto;
import com.bless.ospm.dto.OrgTreeDto;
import com.bless.ospm.model.base.Menu;
import com.bless.ospm.model.base.Organization;
import com.bless.ospm.model.base.User;
import com.bless.ospm.model.base.UserMenu;
import com.bless.ospm.model.base.UserOrgRef;
import com.bless.ospm.service.OrganizationService;
import com.mysql.jdbc.log.Log;

public class OrganizationServiceImpl extends BaseServiceImpl implements
		OrganizationService {
	private OrganizationDao organizationDao;

	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
		setBaseDao(organizationDao);
	}

	@Override
	public void setBaseDao(BaseDao baseDao) {
		this.dao = baseDao;
	}

	@Override
	public List<Organization> findChildrenByUserId(Long uid) {
		HashMap proMap = new HashMap();
		proMap.put("uid", uid);
		
		List<Organization> os = (List<Organization>) organizationDao.findByHql(" FROM Organization o where o.parentId in (select uor.organization from UserOrgRef uor where uor.user.id=:uid) or o.id in (select uor.organization from UserOrgRef uor where uor.user.id=:uid) ", proMap, 0, 0);
		return os;
	}

	@Override
	public List<NodeDto> findUserOrg(Long uid) {
		List<Organization> organizations = (List<Organization>)this.findByHql("from Organization", null, 0, 0);
		HashMap map = new HashMap();
		map.put("uid", uid);
		List<UserOrgRef> orgRefs = (List<UserOrgRef>)this.findByHql("from UserOrgRef where user.id=:uid", map, 0, 0);
	    System.out.println(orgRefs.size()+"=="+uid);
		List<NodeDto> nodeDtos = new ArrayList<NodeDto>();
		NodeDto nodeDto;
		for(Organization organization:organizations){
			nodeDto = new NodeDto();
			nodeDto.setId(organization.getId());
			nodeDto.setName(organization.getName());
			nodeDto.setOpen(true);
			for(UserOrgRef orgRef:orgRefs){
				if(orgRef.getOrganization().getId()==organization.getId()){
					nodeDto.setChecked(true);
				}
			}
			nodeDto.setpId(organization.getParentId());
			nodeDtos.add(nodeDto);
		}
		return nodeDtos;
	}

	@Override
	public String updateUserOrg(Long uid, String orgArray) {
		HashMap map = new HashMap();
		User user = new User(uid);
		map.put("user", user);
		this.organizationDao.cudByHql("delete UserOrgRef where user=:user", map);
		if(orgArray!=null&&orgArray.length()>0){
			String[] array = orgArray.split(",");
			Organization organization;
			UserOrgRef userOrgRef;
			for(String id:array){
				organization = new Organization();
				organization.setId(Long.parseLong(id));
				userOrgRef = new UserOrgRef();
				userOrgRef.setUser(user);
				userOrgRef.setOrganization(organization);
				this.organizationDao.saveOrUpdate(userOrgRef);
			}
		}
		return "修改成功";
	}

	@Override
	public String updateOrg(Organization organization) {
		HashMap map = new HashMap();
		map.put("id", organization.getId());
		map.put("name", organization.getName());
		map.put("parentId", organization.getParentId());
		map.put("description", organization.getDescription());
		this.organizationDao.cudByHql("update Organization set parentId=:parentId,description=:description,name=:name where id=:id", map);
		return "修改成功";
	}

	@Override 
	public OrgTreeDto orgAll() {
		OrgTreeDto dto = new OrgTreeDto();
		List<Organization> organizations = (List<Organization>)this.organizationDao.findByHql("from Organization order by parentId asc", null, 0, 0);
		if(organizations.size()>0){
			dto.setDescription(organizations.get(0).getDescription());
			dto.setId(organizations.get(0).getId());
			dto.setText(organizations.get(0).getName());
			organizations.remove(0);
			this.orgChild(dto, organizations);
		}
		return dto;
	}
	private void orgChild(OrgTreeDto root,List<Organization> organizations){
		OrgTreeDto dto;
		for(Organization organization:organizations){
			if(organization.getParentId().longValue()==root.getId()){
				dto = new OrgTreeDto();
				dto.setDescription(organization.getDescription());
				dto.setId(organization.getId());
				dto.setText(organization.getName());
				dto.setParentId(organization.getParentId());
				root.getChildren().add(dto);
			}
		}
		for (OrgTreeDto ot : root.getChildren()) {
			orgChild(ot, organizations);
		}
	}

	@Override
	public String delOrg(Long id) {
		HashMap map = new HashMap();
		map.put("parentId", id);
		this.organizationDao.cudByHql("delete Organization where parentId=:parentId", map);
		this.organizationDao.delete(Organization.class, id);
		return "删除成功";
	}
}
