package com.bless.ospm.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bless.common.Constants;
import com.bless.common.action.BaseAction;
import com.bless.common.cache.CacheSystem;
import com.bless.ospm.dto.ComboItem;
import com.bless.ospm.dto.OrgTreeDto;
import com.bless.ospm.dto.ParentDto;
import com.bless.ospm.model.base.Member;
import com.bless.ospm.model.base.Menu;
import com.bless.ospm.model.base.Organization;
import com.bless.ospm.model.base.Resource;
import com.bless.ospm.model.base.Role;

public class CacheAction extends BaseAction {
	Logger log = LoggerFactory.getLogger(CacheAction.class);
	
	CacheSystem cacheSystem;
	
	/** 组织系统编号。用于下列列表框默认选项项 */
	private String oid;
	/** 油卡类型编号。用于下列列表框默认选项项 */
	private String cardId;
	
	//成员可以拥有的组织的ID，字符串用逗号分隔。例如："1,2,3,4"
	private String orgIds; 
	
	/**
	 * 得到组织的下拉列表框JS格式
	 * 
	 * @throws IOException
	 */
	public void cacheOrgJS() throws IOException {
		log.info("method cacheOrgJS() called.");
		Map<Long,Organization> os = cacheSystem.getOrganization();
		List<ComboItem> orgs = getOrgJSONData(os,orgIds);
		response.setCharacterEncoding(Constants.ENCODING_UTF8);
		response.setContentType("text/json");
		String message = JSONArray.fromObject(orgs).toString();
		log.info(message);
		response.getWriter().print(message);
	}
	//封装数据
	public List<ComboItem> getOrgJSONData(Map<Long,Organization> os,String orgIds){
		List<ComboItem> orgJSONData = new ArrayList<ComboItem>(); 
		Map<Long,ComboItem> buffer = new HashMap<Long, ComboItem>();//暂时存储符合条件的organization
		if(null!=orgIds){
			//如果orgIds不为空，取出所有的组织的id
			String[] strs = orgIds.split(",");
			for(String str : strs){
				Long orgId = Long.parseLong(str.trim());
				//筛选复合条件的organization
				for(Long id : os.keySet()){
					if(Long.valueOf(id)==Long.valueOf(orgId)){
						ComboItem ci = new ComboItem();
						ci.setId(id+"");
						ci.setText(os.get(id).getName());
						buffer.put(id, ci);
					}
				}
			}
			//在buffer中添加子节点
			for(Long id : buffer.keySet()){
				for(Long subId:buffer.keySet()){
					if(null!=os.get(subId).getParentId()&&Long.valueOf(os.get(subId).getParentId())==Long.valueOf(id)){
						buffer.get(id).getChildren().add(buffer.get(subId));
					}
				}
			}
			//获取所有有子对象的组织的ID
			Set<Long> childrenSets = new HashSet<Long>();
			for(Long id : buffer.keySet()){
				if(buffer.get(id).getChildren().size()>0){
					childrenSets.add(id);
				}
			}
			//添加到JSONArray中的内容
			for(Long id:childrenSets){
				boolean flag  = true;
				//如何含有子对象的parentId为null,直接加入到JSONArray
				if(null==os.get(id).getParentId()){
					orgJSONData.add(buffer.get(id));
					continue;
				}
				for(Long subId:childrenSets){
					
					if (Long.valueOf(os.get(id).getParentId())==Long.valueOf(subId)){
						//如果含有子对象的parentId不等有childrensets中的任何一个值，则加入到JSONArray
						flag = false;
						break;
					}
				}
				if(flag){
					orgJSONData.add(buffer.get(id));
				}
			}
			
		}else{
			//选择所有的organization
			for(Long id : os.keySet()){
				ComboItem ci = new ComboItem();
				ci.setId(id+"");
				ci.setText(os.get(id).getName());
				buffer.put(id, ci);
				
			}
			for(Long id : buffer.keySet()){
				//添加子节点
				if(null!= os.get(id).getParentId()){
					buffer.get(os.get(id).getParentId()).getChildren().add(buffer.get(id));
				}
			}
			for(Long id : buffer.keySet()){
				//添加父节点
				if(null==os.get(id).getParentId()){
					orgJSONData.add(buffer.get(id));
				}
			}
			
		}
		
		for(ComboItem its : orgJSONData){
			System.out.println("its="+its);
		}
		return orgJSONData;
	} 
	
	
	
	
	/**
	 * 得到油卡类型的下拉列表框JS格式
	 * 
	 * @throws IOException
	 */
	public void cacheCardTypeJS() throws IOException {
		log.info("method cacheCardTypeJS() called.");

		List<ComboItem> items = new ArrayList<ComboItem>();

		ComboItem ci = new ComboItem("1", "固定");
		ComboItem ci1 = new ComboItem("2", "流动");
		items.add(ci);
		items.add(ci1);
		
		for(ComboItem item : items){
			if(item.getId().equals(cardId))item.setSelected(true);
		}
		//json-html
		response.setCharacterEncoding(Constants.ENCODING_UTF8);
		response.setContentType("text/json");
		String message = JSONArray.fromObject(items).toString();
		log.info(message);
		response.getWriter().print(message);
	}
	public void cacheOrgParentJS()throws IOException{
		log.info("method cacheOrgParentJS() called.");
		OrgTreeDto dto = this.cacheSystem.getOrg();
		String message = JSONArray.fromObject(dto).toString();
		log.info(message);
		this.writeMsg(message);
	}
	/**
	 * 获取操作人员的下拉列表框JS格式
	 * @throws IOException
	 */
	public void cacheMemberJS()throws IOException{
		log.info("cacheMemberJS()");
		List<Member> menbers = this.cacheSystem.getMember();
		List<ParentDto> dtos = new ArrayList<ParentDto>();
		ParentDto dto;
		for(Member member:menbers){
			dto = new ParentDto();
			dto.setId(member.getId());
			dto.setText(member.getName());
			dtos.add(dto);
		}
		String message = JSONArray.fromObject(dtos).toString();
		log.info(message);
		this.writeMsg(message);
	}
	/**
	 * 获取所有是叶子节点的权限资源作为下拉列表框JS格式
	 * @throws IOException
	 */
	public void cacheMenuResourceJS() throws IOException{
		log.info("cacheMenuResourceJS()");
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[] { "hibernateLazyInitializer", "handler" });
		List<Resource> resources = this.cacheSystem.getResource();
		List<ParentDto> dtos = new ArrayList<ParentDto>();
		ParentDto dto;
		for (Resource r : resources) {
			dto = new ParentDto();
			dto.setId(r.getId());
			dto.setText(r.getName());
			dtos.add(dto);
		}
		String message = JSONArray.fromObject(dtos,config).toString();
		log.info(message);
		this.writeMsg(message);
	}
	/**
	 * 获取菜单父类的下拉列表框JS格式
	 * @throws IOException
	 */
	public void cacheMenuJS()throws IOException{
		log.info("menuParentJS()");
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[] { "hibernateLazyInitializer", "handler" });
		ParentDto dto = new ParentDto();
		List<Menu> menus = this.cacheSystem.getMenu();
		if (menus.size() > 0) {
			dto.setId(menus.get(0).getId());
			dto.setText(menus.get(0).getName());
			parentItem(dto, menus);
		}
		String message = JSONArray.fromObject(dto,config).toString();
		log.info(message);
		this.writeMsg(message);
		
	}
	/**
	 * 封装菜单
	 * @param dto
	 * @param menus
	 */
	private void parentItem(ParentDto dto, List<Menu> menus) {

		for (Menu m : menus) {
			if(m.getParentId()!=null){
				if (m.getParentId().longValue() == dto.getId()) {
					ParentDto child = new ParentDto();
					child.setId(m.getId());
					child.setText(m.getName());
					dto.getChildren().add(child);
				}
			}
		}

		for (ParentDto md : dto.getChildren()) {
			parentItem(md, menus);
		}

	}
	/**
	 * 获取角色的下拉列表框JS格式
	 * @throws IOException
	 */
	public void cacheRoleJS()throws IOException{
		List<Role> roles = this.cacheSystem.getRole();
		List<ParentDto> dtos = new ArrayList<ParentDto>();
		ParentDto dto;
		for(Role role:roles){
			dto = new ParentDto();
			dto.setId(role.getId());
			dto.setText(role.getName());
			dtos.add(dto);
		}
		String message = JSONArray.fromObject(dtos).toString();
		log.info(message);
		this.writeMsg(message);
	}
	/**
	 * 获取所有资源的下拉列表框JS格式
	 * @throws IOException
	 */
	public void cacheResouceJS()throws IOException{
		log.info("cacheResouce()");
		ParentDto dto = new ParentDto();
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[] { "hibernateLazyInitializer", "handler" });
		List<Resource> resources = this.cacheSystem.getResouce();
		log.info(resources.size()+"===");
		if (resources.size() > 0) {
			dto.setId(resources.get(0).getId());
			dto.setText(resources.get(0).getName());
			resourceItem(dto, resources);
		}
		String message = JSONArray.fromObject(dto,
				config).toString();
		log.info(message);
		this.writeMsg(message);
	}
	/**
	 * 封装资源
	 * @param dto
	 * @param resources
	 */
	private void resourceItem(ParentDto dto, List<Resource> resources) {

		for (Resource r : resources) {
			if(r.getParentId()!=null){
				if (r.getParentId().longValue() == dto.getId()) {
					ParentDto child = new ParentDto();
					child.setId(r.getId());
					child.setText(r.getName());
					dto.getChildren().add(child);
				}
			}
		}

		for (ParentDto md : dto.getChildren()) {
			resourceItem(md, resources);
		}

	}
	public void setCacheSystem(CacheSystem cacheSystem) {
		this.cacheSystem = cacheSystem;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}
	
	
	

}
