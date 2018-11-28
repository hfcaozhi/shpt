$(document).ready(function() {
	// 加载菜单
	$.get(ctx + '/join/menuJS.action?userId='+userId, function(msg) {
		if (msg.status != 0) {
			alert('初始化失败请联系管理员');
		} else {
			_menus = {
				"menus" : []
			};
			// 生成menu
			if(msg.page.rs.length>0){
				for(var i=0;i<msg.page.rs.length;i++)
					_menus.menus.push(generateMenu(msg.page.rs[i]));
			}
			
			//console.log(_menus);
			
			InitLeftMenu();
			tabClose();
			tabCloseEven();
			initSysMenu(_menus); 
		}

	});
});
/**
 * 生成菜单
 * @param _m
 * @returns {___anonymous566_712}
 */
function generateMenu(_m) {

	var m = {
		"menuid" : "" + _m.menu.id,
		"icon" : "icon-sys",
		"menuname" : "" + _m.menu.name, 
		"url" : (_m.menu.resource!=null)?(ctx +_m.menu.resource.actionUrl):"",
		"codeNo":""+_m.menu.codeNo,
		"menus" : []
	};

	if (_m.children.length > 0) {
		for ( var i = 0; i < _m.children.length; i++) {
			m.menus.push(generateMenu(_m.children[i]));
		}
	}
	return m;
}

/**
 * 根据总菜单生成导航菜单
 */
function getNavMenu(_m){
	var result=null;
	 $.each(_m.menus, function(i, n) {
		if(result!=null)return false;
		if (n.codeNo == 1001) {
			result = n;
		}else{
			result = getNavMenu(n);
		}
	});
	return result; 
}

/**
 * 根据总菜单生成系统管理菜单
 */
function getSysMenu(_m){ 
	var result=null;
	 $.each(_m.menus, function(i, n) {
		if(result!=null)return false;
		
		if (n.codeNo == 1002) {
			result = n;
		}else{
			result = getSysMenu(n);
		}
	});
	return result; 
}

/**
 * 显示系统管理菜单，如果有该权限的话 
 */
function initSysMenu(_m) {
	var m = getSysMenu(_m); 
	
	var html='<a href="javascript:void(0)" id="mb2" class="easyui-menubutton" style="margin-top: -3px; color: white;" menu="#mm2">系统管理  </a>';
	html+='<div id="mm2" style="width: 100px;">';
	//循环生成菜单项
	for(var i=0;i<m.menus.length;i++){
		var men = m.menus[i];
		if(men.menuname=="密码管理"){
			var clazz = 'class=\"icon icon-edit menu-item\"';
			html+="<div "+clazz+" onclick=\"javascript:$('#w').window('open');\">"+men.menuname+"</div>";	
		}else{
			html+="<div "+clazz+" onclick=\"addTab('"+men.menuname+"','"+men.url+"')\">"+men.menuname+"</div>";
		}
		 
	} 
	 
	html+='</div>';
	//var tt = '<a href="javascript:void(0)" id="mb2" class="easyui-menubutton" style="margin-top: -3px; color: white;" menu="#mm2">系统管理  </a><div id="mm2" style="width: 100px;"><div class="icon icon-redo" onclick="#">角色管理</div></div>';
	/*
	 * <a href="javascript:void(0)" id="mb2"
				class="easyui-menubutton" style="margin-top: -3px; color: white;"
				menu="#mm2">系统管理  </a>
				<div id="mm2" style="width: 100px;">
				<div class="icon icon-redo" onclick="addTab('角色管理','${ctx}/join/toRolePage.action')">角色管理</div>
				<div class="icon icon-redo" onclick="addTab('菜单管理','${ctx}/join/toMenuPage.action')">菜单管理</div>
				<div class="icon icon-redo" onclick="addTab('用户管理','${ctx}/join/toUserPage.action')">用户管理</div>
				<div class="icon icon-redo" onclick="addTab('权限管理','${ctx}/join/toResourcePage.action')">权限管理</div>
					<div id="editpass" class="icon icon-edit">修改密码</div>
				</div> 
	 * */  
	$("#menu_sys").html(html);
	$("#menu_sys").trigger("create");
	
	$("#mb2").menubutton({  
	    iconCls: 'icon-edit',  
	    menu: '#mm2'  
	});  
	
}
