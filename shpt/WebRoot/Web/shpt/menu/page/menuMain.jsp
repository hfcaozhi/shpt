<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/Web/common/page/master.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${ctx }/resources/scripts/easyui1.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${ctx }/resources/scripts/easyui1.3/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${ctx }/resources/scripts/easyui1.3/demo/demo.css">
<link rel="stylesheet" type="text/css"
	href="${ctx }/resources/css/role.css">

<script type="text/javascript"
	src="${ctx }/resources/scripts/easyui1.3/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/scripts/easyui1.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/scripts/easyui1.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/scripts/jquery-ui.js"></script>
<script type="text/javascript" src="${ctx }/resources/scripts/tree1.js"></script>
<script>
	$(function() {
		var path = '${ctx }/join/menuAllJS.action';
		loadTree(path);
		//加载数据
		//打开添加菜单信息窗口
		$('#add').click(function() {
			reload();
			clearText();
			$('#addWindow').window({title:"添加菜单"});
			$('#addWindow').window('open');
		});
		//打开修改菜单信息窗口
		$('#update').click(
				function() {
					reload();
					clearText();
					var $bg = $('div.zm_background_click');
					var menuId = $bg.attr('menuid');
					var codeId = $bg.attr('codeid');
					var resourceid = $bg.attr('resourceid');
					var parentid = $bg.attr('parentid');
					var menuName = $bg.find('>span.name').html();
					var menuDiscription = $bg.find(">div.zm_folder_title")
							.find("div.opreate_a").html();
					if (menuId == null || menuId.length <= 0) {
						$.messager.alert('系统提示', '请先选择再进行操作', 'warning');
						return;
					}
					$('#addWindow').window({title:"修改菜单"});
					if (codeId > 0) {
						$('#menuCodeId').val(codeId);
					}
					if (resourceid > 0) {
						$('#resource').combobox('setValue', resourceid);
					}
					if (parentid > 0) {
						$('#menuParent').combotree('setValue', parentid);
					}
					if (menuName != null && menuName.length > 0) {
						$('#menuName').val(menuName);
					}
					if (menuDiscription != null && menuDiscription.length > 0) {
						$('#menuDescription').val(menuDiscription);
					}
					$('#addWindow').window('open');
				});
		//删除菜单信息
		$('#del').click(function() {
			var $bg = $('div.zm_background_click');
			menuId = $bg.attr('menuid');
			isdel = $bg.attr('isdel');
			if (menuId == null || menuId.length <= 0) {
				$.messager.alert('系统提示', '请先选择再进行操作', 'warning');
				return;
			}
			if (isdel!=null&&isdel==1) {
				$.messager.alert('系统提示', '该菜单不能够被删除!', 'error');
				return;
			}
			$.messager.confirm('系统提示', '您确定要删除该条记录吗?', function(r) {
				if (r) {
					var url = '${ctx }/join/menuDelJS.action';
					url += '?menu.id=' + menuId;
					$.ajax({
						url : url,
						dataType : 'text',
						success : function(msg) {
							loadTree(path);
							$.messager.alert('提示', msg, 'info');
							$(reload).combotree('reload');
						}
					});

				}
			});

		});
		//保存菜单信息
		$('#save').click(function() {
			var discription = $('#menuDescription').val();
			var name = $('#menuName').val();
			if (name == "" || name.length <= 0) {
				return;
			}
			var codeNo = $('#menuCodeId').val();
			var parent = $('#menuParent').val();
			var parentId = $('#menuParent').combotree('getValue');
			if (parentId == "" || parentId.length <= 0) {
				return;
			}
			var resourceId = $('#resource').combobox('getValue');
			if (resourceId == "" || resourceId.length <= 0) {
				resourceId = 0;
			}

			var description = $('#menuDescription').val();
			var url = '${ctx}/join/menuAddJS.action';
			var data = {
				"menu.name" : name,
				"menu.description" : description,
				"menu.parentId" : parentId,
				"menu.codeNo" : codeNo,
				"menu.porder" : 1,
				"menu.resource.id" : resourceId,
				"menu.status" : 0
			};
			if ($('div.panel-title').html() == '修改菜单') {
				var $bg = $('div.zm_background_click');
				menuId = $bg.attr('menuid');
				if (parentId == menuId) {
					$.messager.alert('系统提示', '请重新选择父节点!', 'error');
					return;
				}
				url = '${ctx}/join/menuUpdateJS.action';
				data = {
					"menu.name" : name,
					"menu.id" : menuId,
					"menu.description" : description,
					"menu.parentId" : parentId,
					"menu.codeNo" : codeNo,
					"menu.porder" : 1,
					"menu.resource.id" : resourceId,
					"menu.status" : 0
				};
				$.messager.confirm('系统提示', '您确定要修改该条记录吗?', function(r) {
					if (r) {
						post(url, data, path, '#addWindow');
					}
				});

			} else {
				post(url, data, path, '#addWindow');
			}
		});
	});
	//清空文本
	function clearText() {
		$('#menuName').val('');
		$('#menuCodeId').val('');
		$('#menuParent').val('');
		$('#menuDescription').val('');
		$('#resource').combobox('setValue', '');
		$('#menuParent').combotree('setValue', '');
	}
	//刷新下拉列表数据
	function reload(){
		$('#menuParent').combotree('reload');
		$('#resource').combobox('reload');
	}
</script>
</head>
<body>
	<div class="zm_content">
		<div class="zm_body_title">菜单管理</div>
		<table cellpadding="0px" cellspacing="0px" class="zm_body_search"
			align="center">
			<tr>
				<td><a href="#" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-add'" id="add">添加</a> <a
					href="#" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-edit'" id="update">修改</a> <a
					href="#" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-remove'" id="del">删除</a></td>
			</tr>
		</table>
	</div>

	<div id="addWindow" class="easyui-window" closed=true modal=true
		style="width: 300px; height: 278px; padding: 10px;" title="添加菜单">
		<table width="100%" cellpadding="0" cellspacing="0" align="center">
			<tr height="28px">
				<td>菜单名称：</td>
				<td><input type="text" class="easyui-validatebox" data-options="required:true,validType:'length[1,20]'" id="menuName" style="width:150px"/></td>
			</tr>
			<tr height="28px">
				<td>菜单编号：</td>
				<td><input type="text" class="easyui-numberbox inputs"
					id="menuCodeId" /></td>
			</tr>
			<tr height="28px">
				<td>父节点：</td>
				<td>
				<input class="easyui-combotree" id="menuParent"
					data-options="url:'${ctx }/join/cacheMenuJS.action',required:true"
					style="width: 152px;"></td>
			</tr>
			<tr height="28px">
				<td>资源路径：</td>
				<td><input class="easyui-combobox" id="resource"
						data-options="url:'${ctx }/join/cacheMenuResourceJS.action',valueField:'id',textField:'text'"
						style="width: 152px;"/>
					</td>
			</tr>
			<tr height="28px">
				<td>说明：</td>
				<td><textarea class="easyui-validatebox" style="height:80px;width:150px" id="menuDescription"></textarea></td>
			</tr>
		</table>
		<div id="win-buttons" style="text-align: center; margin-top: 5px;">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" id="save">保存</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#addWindow').window('close')">取消</a>
		</div>
	</div>
</body>
</html>
