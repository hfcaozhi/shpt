package com.bless.common.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bless.ospm.dto.OrgTreeDto;
import com.bless.ospm.dto.ParentDto;
import com.bless.ospm.model.base.Member;
import com.bless.ospm.model.base.Menu;
import com.bless.ospm.model.base.Organization;
import com.bless.ospm.model.base.Resource;
import com.bless.ospm.model.base.Role;
import com.bless.ospm.service.MemberService;
import com.bless.ospm.service.MenuService;
import com.bless.ospm.service.OrganizationService;
import com.bless.ospm.service.ResourceService;
import com.bless.ospm.service.RoleService;

/**
 * 由spring管理bean，所以系统中共用一个。这里的map数据也就是程序内用户共享的了。
 * @author lala
 *
 */
public class CacheSystem {
	Logger log = LoggerFactory.getLogger(CacheSystem.class);
	
	private  OrganizationService organizationService;
	private MemberService memberService;
	private RoleService roleService;
	private ResourceService resourceService;
	private MenuService menuService;
	private  Map<Long, Organization> organizations = new HashMap<Long, Organization>();
	private List<Member> menbers = new ArrayList<Member>();
	private List<Role> roles = new ArrayList<Role>();
	private List<Resource> menuResources = new ArrayList<Resource>();
	private List<Resource> resources = new ArrayList<Resource>();
	private List<Menu> menus = new ArrayList<Menu>();
	private OrgTreeDto orgTreeDto=null;
	
	/**
	 * 根据组织系统编号，得到该组织实体
	 * 
	 * @param orgId
	 * @return
	 */
	public synchronized Organization getOrganization(Long orgId) {
		if (organizations.isEmpty()) {
			synOrganization();
		}

		return organizations.get(orgId);
	}

	/***
	 * 将组织结构数据加载到内存中，以便整个系统范围使用。 当组织数据有便动时，调用该方法，同步数据。
	 */
	public synchronized  void synOrganization() {
		log.info("method synOrganization() called.");
		organizations.clear();
		List<Organization> os = (List<Organization>) organizationService
				.findByHql("From Organization", null, 0, 0);
		if (os != null) {
			for (Organization o : os) {
				organizations.put(o.getId(), o);
			}
		}
	}
	/**
	 * 将人员数据加载到内存中
	 */
	public synchronized  void synMember(){
		log.info("synMember()");
		this.menbers.clear();
		this.menbers = (List<Member>)this.memberService.findByHql("from Member", new HashMap(),0,0);
	}
	/**
	 * 将角色数据加载到内存中
	 */
	public synchronized  void synRole(){
		log.info("synRole()");
		this.roles.clear();
		this.roles = this.roleService.roleAll();
	}
	/**
	 * 将是资源是叶子节点数据加载到内存中
	 */
	public synchronized void synMenuResource(){
		log.info("synMenuResource()");
		this.menuResources.clear();
		this.menuResources = this.resourceService.findAllLeaf();
	}
	/**
	 * 将作为菜单父类的数据加载到内存中
	 */
	public synchronized void synMenu(){
		log.info("synMenu()");
		this.menus.clear();
		this.menus = this.menuService.allMenu();
	}
	/**
	 * 将所有的资源数据加载到内存中
	 */
	public synchronized void synResouce(){
		log.info("synResouce()");
		this.resources.clear();
		this.resources = resourceService.resouceAll();
	}
	/**
	 * 将组织结构数据加载到内存中
	 */
	public synchronized void synOrg(){
		this.orgTreeDto = new OrgTreeDto();
		this.orgTreeDto = this.organizationService.orgAll();
	}
	/**
	 * 获取组织结构数据,用于前台下拉列表
	 * @return
	 */
	public OrgTreeDto getOrg(){ 
		if(this.orgTreeDto==null)
			this.synOrg();
		return this.orgTreeDto;
	}
	/**
	 * 获取权限资源数据,用于前台下拉列表
	 * @return
	 */
	public List<Resource> getResouce(){
		if(this.resources.isEmpty())
			this.synResouce();
		return this.resources;
	}
	/**
	 * 获取菜单资源数据,用于前台下拉列表
	 * @return
	 */
	public List<Menu> getMenu(){
		if(this.menus.isEmpty())
			this.synMenu();
		return this.menus;
	}
	/**
	 * 获取权限资源数据,用于前台下拉列表
	 * @return
	 */
	public List<Resource> getResource(){
		if(this.menuResources.isEmpty())
			this.synMenuResource();
		return this.menuResources;
	}
	/**
	 * 获得所有的角色数据,用于前台下拉列表
	 * @return
	 */
	public List<Role> getRole(){
		log.info("getRole()");
		if(this.roles.isEmpty())
			this.synRole();
		return this.roles;
	}
	
	/**
	 * 获得所有的人员数据,用于前台下拉列表
	 * @return
	 */
	public List<Member> getMember(){
		log.info("getMember()");
		if(this.menbers.isEmpty())
			this.synMember();
		return this.menbers;
	}
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
	
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	/**
	 * 得到所有的组织数据，一般用于前台下拉列表框
	 * @return
	 */
	public  Map<Long, Organization> getOrganization() {
		if(organizations.isEmpty())synOrganization();
		return organizations;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	
}
