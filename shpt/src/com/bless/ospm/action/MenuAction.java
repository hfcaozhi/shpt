package com.bless.ospm.action;

import java.io.IOException;
import java.util.ArrayList;
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
import com.bless.common.vo.Result;
import com.bless.ospm.dto.MenuDto;
import com.bless.ospm.dto.NodeDto;
import com.bless.ospm.dto.TreeJSDto;
import com.bless.ospm.model.base.Menu;
import com.bless.ospm.model.base.UserMenu;
import com.bless.ospm.service.MenuService;
import com.bless.ospm.service.UserMenuService;

public class MenuAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 271031480205846303L;

	Logger log = LoggerFactory.getLogger(MenuAction.class);

	private UserMenuService userMenuService;
	private MenuService menuService;
	private Long userId;
	private MenuDto result;
	private Menu menu;
	private String menuArray; 
	private CacheSystem cacheSystem;
	
	public String toMenuPage() {
		return SUCCESS;
	}

	/**
	 * 
	 * Function : 菜单名单
	 * 
	 * @return
	 * @throws IOException
	 */
	public void menuJS() throws IOException {
		log.info("method menuJS() called.");
		Page page = wrapPage();
		page.setPageSize(1000);
		// 执行添加操作
		HashMap proMap = new HashMap();
		// proMap.put("uid",
		// Long.parseLong(session.getAttribute(Constant4Ospm.SESSION_USER_ID).toString()));
		proMap.put("status", new Byte("0"));
		if (userId != null) {
			proMap.put("uid", userId);
			page = userMenuService.findByHqlPage(
					"FROM UserMenu where user.id=:uid and menu.status=:status",
					proMap, page);
		} else {
			page = userMenuService.findByHqlPage(
					"FROM UserMenu where menu.status=:status", proMap, page);
		}
		List<Menu> menus = new ArrayList<Menu>();
		List<UserMenu> ums = page.getRs();
		for (UserMenu m : ums) {
			// m.getMenu().setUserMenus(null);
			Menu _m = new Menu();
			_m.setId(m.getMenu().getId());
			_m.setCodeNo(m.getMenu().getCodeNo());
			_m.setDescription(m.getMenu().getDescription());
			_m.setName(m.getMenu().getName());
			_m.setParentId(m.getMenu().getParentId());
			_m.setPorder(m.getMenu().getPorder());
			_m.setStatus(m.getMenu().getStatus());
			if (m.getMenu().getResource() != null) {
				m.getMenu().getResource().setMenus(null);
				m.getMenu().getResource().setRoleResourceRefs(null);
			}

			_m.setResource(m.getMenu().getResource());
			menus.add(_m);
		}
		List<MenuDto> result = new ArrayList<MenuDto>();
		result.add(menuService.convert2Tree(menus));
		page.setRs(result);
		response.setCharacterEncoding(Constants.ENCODING_UTF8);
		response.setContentType("text/json");
		Result rs = new Result();
		rs.setPage(page);
		rs.setStatus(0);

		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[] { "hibernateLazyInitializer", "handler" });

		String message = JSONObject.fromObject(rs, config).toString();

		log.info(message);
		response.getWriter().print(message);
	}

	// 查询出所有的菜单
	public void menuAllJS() throws IOException {
		log.info("menuAllJS()");
		TreeJSDto dto = this.menuService.menuTree();
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[] { "hibernateLazyInitializer", "handler" });
		String message = JSONObject.fromObject(dto,config).toString();
		this.writeMsg(message);
	}
	//修改用户菜单
	public void menuUpdateUserJS() throws IOException{
		log.info("menuUpdateUserJS()");
		Result rs = new Result();
		try {
			this.userMenuService.updateUserMenus(this.menuArray, this.userId);
			rs.setMessage("操作成功");
		} catch (RuntimeException e) {
			e.printStackTrace();
			rs.setMessage("操作失败,请稍候重试");
		}
		String message = JSONObject.fromObject(rs).toString();
		log.info(message);
		this.writeMsg(message);
	}
	//获取用户菜单
	public void menuUserJS()throws IOException {
		log.info("menuUserJS()");
		List<UserMenu> userMenus = this.userMenuService.byUserMenu(this.userId);
		List<Menu> menus = this.menuService.allMenu();
		menus.remove(0);
		List<NodeDto> nodeDtos = new ArrayList<NodeDto>();
		NodeDto nodeDto;
		for(Menu menu:menus){
			nodeDto = new NodeDto();
			nodeDto.setId(menu.getId());
			nodeDto.setName(menu.getName());
			nodeDto.setOpen(true);
			for(UserMenu userMenu:userMenus){
				if(userMenu.getMenu().getId()==menu.getId()){
					nodeDto.setChecked(true);
				}
			}
			nodeDto.setpId(menu.getParentId());
			nodeDtos.add(nodeDto);
		}
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[] { "hibernateLazyInitializer", "handler" });
		String message = JSONArray.fromObject(nodeDtos,config).toString();
		log.info(message);
		this.writeMsg(message);
	}
	// 添加菜单
	public void menuAddJS() throws IOException {
		log.info("menuAddJS()" + this.menu);
		if (this.menu.getResource().getId() == 0) {
			this.menu.setResource(null);
		}
		String message = "添加成功"; 
		try{
			this.menuService.insert(this.menu);
			reload();
		}catch(RuntimeException e){
			e.printStackTrace();
			message = "添加失败,请稍候重试.";
		}
		response.getWriter().print(message);
	}
	// 修改菜单
	public void menuUpdateJS() throws IOException {
		log.info("menuUpdateJS()" + this.menu);
		if (this.menu.getResource().getId() == 0) {
			this.menu.setResource(null);
		}
		String message;
		try{
			message = this.menuService.updateMenu(this.menu);
			reload();
		}catch(RuntimeException e){
			e.printStackTrace();
			message = "修改失败,请稍候重试.";
		}
		response.getWriter().print(message);
	}

	// 删除菜单
	public void menuDelJS() throws IOException {
		log.info("menuDelJS()" + this.menu.getId());
		String message;
		try{
			message = this.menuService.delMenu(menu);
			reload();
		}catch(RuntimeException e){
			e.printStackTrace();
			message = "添加失败,请稍候重试.";
		}
		response.getWriter().print(message);
	}
	/**
	 * 刷新内存中菜单的数据
	 */
	private void reload(){
		this.cacheSystem.synMenu();
	}
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public void setUserMenuService(UserMenuService userMenuService) {
		this.userMenuService = userMenuService;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public MenuDto getResult() {
		return result;
	}

	public String getMenuArray() {
		return menuArray;
	}

	public void setMenuArray(String menuArray) {
		this.menuArray = menuArray;
	}

	public void setResult(MenuDto result) {
		this.result = result;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public void setCacheSystem(CacheSystem cacheSystem) {
		this.cacheSystem = cacheSystem;
	}

}
