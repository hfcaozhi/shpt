<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/Web/common/page/master.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
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
		var path = '${ctx }/join/resourceAllTreeJS.action';
		loadTree(path);
		//打开添加菜单信息窗口
		$('#add').click(function() {
			$('#parent').combotree('reload');
			clearText();
			$('#addWindow').window({
				title : "添加权限"
			});
			$('#addWindow').window('open');
		});
		//打开修改菜单信息窗口
		$('#update').click(
				function() {
					$('#parent').combotree('reload');
					clearText();
					var $bg = $('div.zm_background_click');
					var id = $bg.attr('menuid');
					var parent = $bg.attr('parentid');
					var name = $bg.find('>span.name').html();
					var url = $bg.find(">div.zm_folder_title").find(
							"div.opreate_a").html();
					var discription = $bg.find(">div.zm_folder_title").find(
							"div.opreate_b").html();
					var leal = $bg.attr('resourceid');
					if (id == null || id.length <= 0) {
						$.messager.alert('系统提示', '请先选择再进行操作', 'warning');
						return;
					}
					if (name != null && name.length > 0) {
						$('#name').val(name);
					}
					if (url != null && url.length > 0) {
						$('#url').val(url);
					}
					if (parent != null && parent > 0) {
						$('#parent').combotree('setValue', parent);
					}
					if (discription != null && discription.length > 0) {
						$('#discription').val(discription);
					}
					if (leal != null && leal.length > 0) {
						if (leal == 0) {
							$('#leafNo').attr('checked', 'checked');
						} else {
							$('#leaf').attr('checked', 'checked');
						}
					}
					$('#addWindow').window({
						title : "修改权限"
					});
					$('#addWindow').window('open');
				});
		//删除菜单信息
		$('#del').click(function() {
			var $bg = $('div.zm_background_click');
			id = $bg.attr('menuid');
			if (id == null || id.length <= 0) {
				$.messager.alert('系统提示', '请先选择再进行操作', 'warning');
				return;
			}
			$.messager.confirm('系统提示', '您确定要删除该条记录吗?', function(r) {
				if (r) {
					var url = '${ctx }/join/resourceDelJS.action';
					url += '?resource.id=' + id;
					$.ajax({
						url : url,
						dataType : 'text',
						success : function(msg) {
							loadTree(path);
							$.messager.alert('系统提示', msg, 'info');
						}
					});

				}
			});

		});
		$('input[type=radio]').click(function() {
			if ($(this).attr('checked') == 'checked') {
				$('input[type=radio]').attr('checked', false);
				$(this).attr('checked', 'checked');
			}
		});
		//保存菜单信息
		$('#save').click(function() {
			var name = $('#name').val();
			var actionurl = $('#url').val();
			var parent = $('#parent').combotree('getValue');
			var leaf = $('[name=leaf][checked=checked]').val();
			var discription = $('#discription').val();
			if (name == "" || name.length <= 0) {
				return;
			}
			var url = '${ctx}/join/resourceAddJS.action';
			var data = {
				"resource.name" : name,
				"resource.actionUrl" : actionurl,
				"resource.description" : discription,
				"resource.parentId" : parent,
				"resource.isLeaf" : leaf,
				"resource.status" : 0
			};
			if ($('div.panel-title').html() == '修改权限') {
				var $bg = $('div.zm_background_click');
				id = $bg.attr('menuid');
				if (id == parent) {
					$.messager.alert('系统提示', '请重新选择父节点!', 'error');
					return;
				}
				url = '${ctx}/join/resourceUpdateJS.action';
				data = {
					"resource.name" : name,
					"resource.id" : id,
					"resource.actionUrl" : actionurl,
					"resource.description" : discription,
					"resource.parentId" : parent,
					"resource.isLeaf" : leaf,
					"resource.status" : 0
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
		$('#name').val('');
		$('#url').val('');
		$('#leaf').attr('checked', 'checked');
		$('#discription').val('');
		$('#parent').combotree('setValue', '');
	}
</script>
</head>
<body>
	<div class="zm_content">
		<div class="zm_body_title">权限管理</div>
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
		style="width: 300px; height: 305px; padding: 10px;" title="添加权限">
		<table width="100%" cellpadding="0" cellspacing="0" align="center">
			<tr height="28px">
				<td>资源名称：</td>
				<td><input class="easyui-validatebox"
					data-options="required:true" id="name"
					style="width: 150px"></td>
			</tr>
			<tr height="28px">
				<td>资源连接：</td>
				<td><input class="easyui-validatebox" id="url"
					style="width: 150px" /></td>
			</tr>
			<tr height="28px">
				<td>父节点：</td>
				<td><input class="easyui-combotree" id="parent"
					data-options="url:'${ctx }/join/cacheResouceJS.action',required:true"
					style="width: 152px;"></td>
			</tr>
			<tr height="28px">
				<td>叶子节点：</td>
				<td><input type="radio" id="leaf" name="leaf" checked="checked"
					value="1" />是<input type="radio" id="leafNo" name="leaf" value="0" />否</td>
			</tr>
			<tr height="28px">
				<td>说明：</td>
				<td><textarea class="easyui-validatebox"
						style="height: 80px; width: 150px" id="discription"></textarea></td>
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
