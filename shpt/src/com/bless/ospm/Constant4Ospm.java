package com.bless.ospm;

public class Constant4Ospm {

	public static final String PAGE_SIZE = "rows";
	public static final String PAGE_NO = "page";
	public static final String TOTAL = "total";
	public static final String SEARCH_RESULT = "search_result";

	/** 用户登录之后，保存在session中的系统编号 */
	public static final String SESSION_USER_ID = "session_user_id";
	/** 用户登录之后，保存在session中的用户组织列表 */
	public static final String SESSION_ORG = "session_org";

	/* 菜单固定代码 */
	
	/**
	 * 总菜单
	 */
	public static final long MENU_ROOT = 0;
	
	/**
	 * 导航菜单
	 */
	public static final long MENU_NAV_CODE = 1001;

	/**
	 * 系统管理菜单
	 */
	public static final long MENU_SYS_CODE = 1002;
	
	/**
	 * 状态标识 -正常
	 */
	public static final String STATUS_NORMAL = "正常";
	/**
	 * 状态标识 -禁用
	 */
	public static final String STATUS_UNUSE = "禁用";
	/**
	 * 状态标识 -已删除
	 */
	public static final String STATUS_DEL = "已删除";
	
	/**
	 * 状态标识 -正常
	 */
	public static final Byte STATUS_CODE_NORMAL = new Byte("0");
	
	/**
	 * 状态标识 -禁用
	 */
	public static final Byte STATUS_CODE_UNUSE = new Byte("1");
	
	/**
	 * 状态标识 -已删除
	 */
	public static final Byte STATUS_CODE_DEL = new Byte("2");
	
}
