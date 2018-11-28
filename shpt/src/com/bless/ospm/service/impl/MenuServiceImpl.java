package com.bless.ospm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bless.common.dao.BaseDao;
import com.bless.common.service.impl.BaseServiceImpl;
import com.bless.ospm.Constant4Ospm;
import com.bless.ospm.dao.MenuDao;
import com.bless.ospm.dto.MenuDto;
import com.bless.ospm.dto.TreeItemDto;
import com.bless.ospm.dto.TreeJSDto;
import com.bless.ospm.model.base.Menu;
import com.bless.ospm.service.MenuService;

public class MenuServiceImpl extends BaseServiceImpl implements MenuService {
	Logger log = LoggerFactory.getLogger(MenuServiceImpl.class);
	private MenuDao menuDao;

	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
		setBaseDao(menuDao);
	}

	@Override
	public void setBaseDao(BaseDao baseDao) {
		this.dao = baseDao;
	}

	@Override
	public MenuDto convert2Tree(List<Menu> menus) {
		MenuDto root = new MenuDto();
		// 首先，找到根节点。先从总菜单开始找。
		Menu rootMenu = new Menu();
		rootMenu.setCodeNo(Constant4Ospm.MENU_ROOT);
		rootMenu.setId(1L);
		root.setMenu(rootMenu);
		convert(root, menus);
		return root;
	}

	private void convert(MenuDto root, List<Menu> menus) {

		for (Menu m : menus) {
			if (m.getParentId().longValue() == root.getMenu().getId()) {
				MenuDto dto = new MenuDto();
				dto.setMenu(m);
				root.getChildren().add(dto);
			}
		}

		for (MenuDto md : root.getChildren()) {
			convert(md, menus);
		}

	}

	@Override
	public String delMenu(Menu menu) {
		menu = (Menu) this.menuDao.load(Menu.class, menu.getId());
		menu.setStatus(Constant4Ospm.STATUS_CODE_UNUSE);
		this.menuDao.saveOrUpdate(menu);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", Constant4Ospm.STATUS_CODE_UNUSE);
		map.put("parentId", menu.getId());
		this.cudByHql(
				"update Menu set status=:status where parentId=:parentId", map);
		return "";
	}

	@Override
	public List<Menu> allMenu() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", Constant4Ospm.STATUS_CODE_NORMAL);
		List<Menu> menus = (List<Menu>) this.findByHql(
				"from Menu where status=:status", map, 0, 0);
		return menus;
	}

	@Override
	public MenuDto allMenu(List<Menu> menus) {
		MenuDto root = new MenuDto();
		// 首先，找到根节点。先从总菜单开始找。
		Menu rootMenu = new Menu();
		rootMenu.setCodeNo(Constant4Ospm.MENU_ROOT);
		rootMenu.setId(menus.get(0).getId());
		rootMenu.setName(menus.get(0).getName());
		root.setMenu(rootMenu);
		menus.remove(0);
		convert(root, menus);
		return root;
	}

	@Override
	public TreeJSDto menuTree() {
		List<Menu> menus = this.allMenu();
		TreeJSDto tree = new TreeJSDto();
		if (menus.size() > 0) {
			tree.setRoot("div.zm_content");
			TreeItemDto ops = new TreeItemDto();
			long menuParent = menus.get(0).getId();
			ops.setId(menuParent);
			ops.setName(menus.get(0).getName());
			ops.setFolderA("描述");
			ops.setFolderB("菜单编号");
			if (menus.get(0).getResource() != null) {
				ops.setResourceId(menus.get(0).getResource().getId());
			}
			ops.setParentId(menus.get(0).getParentId());
			menus.remove(0);
			childTree(ops, menus,menuParent);
			tree.setOps(ops);
		}
		return tree;
	}

	private void childTree(TreeItemDto ops, List<Menu> menus,long menuParent) {

		for (Menu m : menus) {
			if (m.getParentId().longValue() == ops.getId()) {
				TreeItemDto child = new TreeItemDto();
				child.setCodeId(m.getCodeNo());
				child.setFolderA(m.getDescription());
				child.setFolderB(m.getCodeNo() + "");
				child.setParentId(m.getParentId());
				if(m.getParentId()==1){
					child.setIsDel(1);
				}
				if (m.getResource() != null) {
					child.setResourceId(m.getResource().getId());
				}
				child.setId(m.getId());
				child.setMenuId(m.getId());
				child.setName(m.getName());
				ops.getChildren().add(child);
			}
		}

		for (TreeItemDto md : ops.getChildren()) {
			childTree(md, menus,menuParent);
		}

	}

	@Override
	public String updateMenu(Menu menu) {
		HashMap map = new HashMap();
		map.put("codeNo", menu.getCodeNo());
		map.put("description", menu.getDescription());
		map.put("id", menu.getId());
		map.put("name", menu.getName());
		map.put("parentId", menu.getParentId());
		map.put("porder", menu.getPorder());
		map.put("resource", menu.getResource());
		map.put("status", menu.getStatus());
		this.menuDao.cudByHql("update Menu set codeNo=:codeNo,porder=:porder,description=:description,name=:name,parentId=:parentId,resource=:resource,status=:status where id=:id", map);
		return "修改成功";
	}

}
