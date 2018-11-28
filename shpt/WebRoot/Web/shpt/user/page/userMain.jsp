<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/Web/common/page/master.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
<link rel="stylesheet" type="text/css"
	href="${ctx }/resources/css/tree.css">

<script type="text/javascript"
	src="${ctx }/resources/scripts/easyui1.3/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/scripts/easyui1.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/scripts/easyui1.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/scripts/jquery-ui.js"></script>
<script type="text/javascript" src="${ctx }/resources/scripts/tree.js"></script>
<link rel="stylesheet"
	href="${ctx }/Web/common/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript"
	src="${ctx }/Web/common/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript"
	src="${ctx }/Web/common/js/jquery.ztree.excheck-3.5.js"></script>
<script>
	$(function() {
		//添加
		$('#add').click(function() {
			$("#userEdit").form("clear");
			relaod();
			$('#cc').combobox('setValue', '0');
			url = ctx + "/join/userAddJS.action";
			$('#openWindow').window({
				title : "添加用户"
			});
			$('#openWindow').window('open');
		});
		//修改
		$('#edit').click(function() {
			$("#userEdit").form("clear");
			relaod();
			var item = $('#dg').datagrid('getSelected');
			if (item) {
				url = ctx + "/join/userUpdateJS.action";
				$('#openWindow').window({
					title : "修改用户"
				});
				$('#openWindow').window('open');
				$('input[name=pass]').val('           ');
				$('#userEdit').form('load', {
					id : item.id,
					userName : item.userName,
					nickName : item.nickName,
					status : item.status
				});
				if (item.roleId > 0) {
					$('#role').combobox('setValue', item.roleId);
				}
			} else {
				alert("请先选中一行再操作");
			}

		});
		var nameExist = false;
		$('input[name=userName]').focusout(function(){
			var name = $(this).val();
			$.post(ctx + '/join/userCountJS.action',{"userName":name}, function(msg) {
				if(msg>0){
					$('#nameState').html('已存在');
					nameExist=true;
				}else{
					$('#nameState').html('');
					nameExist=false;
				}
			});
		});
		//保存
		$('#save').click(function() {
			if(nameExist==true){
				return;
			}
			$('#userEdit').form('submit', {
				url : url,
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(result) {
					var result = eval('(' + result + ')');
					if (result.errorMsg) {
						$.messager.show({
							title : 'Error',
							msg : result.errorMsg
						});
					} else {
						$.messager.alert('提示', result.message);
						$('#openWindow').window('close');
						$('#dg').datagrid('reload');
					}
				}
			});
		});
		//删除
		$('#del').click(
				function() {
					$.messager.confirm('系统提示', '您确定要删除该条记录吗?', function(r) {
						if (r) {
							$.post(ctx + '/join/userDelJS.action?id='
									+ $('#dg').datagrid('getSelected').id,
									function(msg) {
										$.messager.alert('提示', msg.message);
										$('#dg').datagrid('reload');
									});
						}
					});

				});
		//动态加载所有的数据
		$("#dg").datagrid({
			url : '${ctx}/join/userJS.action',
			onLoadSuccess : function(data) {
				$("#dg").datagrid("selectRow", 0);
			}
		});
		//保存组织
		$('#saveOrgUser').click(function(){
			var zTree = $.fn.zTree.getZTreeObj("orgDemo");
			var check = zTree.getCheckedNodes(true);
			var orgArray="";
			for(var i=0;i<check.length;i++){
				orgArray+=check[i].id;
				if(i!=check.length-1){
					orgArray+=",";
				}
			}
			var data = {"uid":$('#dg').datagrid('getSelected').id,
						"orgArray":orgArray};
			$.post(ctx + '/join/orgUpdateUserJS.action',data, function(msg) {
				$.messager.alert('提示', msg.message);
				$("#orgResource").window("close");
			});
		});
		//保存用户菜单
		$('#saveMenuUser').click(function(){
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			var check = zTree.getCheckedNodes(true);
			var menuArray="";
			for(var i=0;i<check.length;i++){
				menuArray+=check[i].id;
				if(i!=check.length-1){
					menuArray+=",";
				}
			}
			var data = {"userId":$('#dg').datagrid('getSelected').id,
						"menuArray":menuArray};
			$.post(ctx + '/join/menuUpdateUserJS.action',data, function(msg) {
				$.messager.alert('提示', msg.message);
				$("#menuResource").window("close");
			});
			
		});
	});
	//动态加载菜单数据
	function showMenu() {
		var setting = {
				check : {
					enable : true
				},
				data : {
					simpleData : {
						enable : true
					}
				}
			};
			$.post(ctx + '/join/menuUserJS.action',{"userId":$('#dg').datagrid('getSelected').id}, function(msg) {
				$.fn.zTree.init($("#treeDemo"), setting, msg);
				$("#menuResource").window("open");
			});
		
		
	}
	//动态加载组织数据
	function showOrg(){
		var setting = {
				check : {
					enable : true
				},
				data : {
					simpleData : {
						enable : true
					}
				}
			};
			$.post(ctx + '/join/orgUserJS.action',{"uid":$('#dg').datagrid('getSelected').id}, function(msg) {
				$.fn.zTree.init($("#orgDemo"), setting, msg);
				$("#orgResource").window("open");
			});
	}
	//动态加载权限数据
	function showRole() {
		$.post(ctx + '/join/resourceJS.action', function(msg) {
			//生成权限树
			console.log("privilege tree is " + msg);
			$('<div></div>').win(msg);
			$('input[type=checkbox]').click(function() {
				choose($(this));
			})
		});
		$("#roleResource").window("open");
	}
	//搜索数据
	function search() {
		$('#dg').datagrid('load', {
			userName : $("#search_name").val()
		});
	}
	//刷新下拉列表数据
	function relaod(){
		$('#member').combobox('reload');
		$('#role').combobox('reload');
	}
	
</script>
</head>
<body>
	<table id="dg" title="用户管理" fit=true
		data-options="pageSize:10,pageList:[1,5,10],rownumbers:true,pagination:true,singleSelect:true,toolbar:'#tb'">
		<thead>
			<tr>
				<th data-options="field:'userName',width:170,align:'center'">用户名</th>
				<th data-options="field:'nickName',width:170,align:'center'">昵称</th>
				<th
					data-options="field:'status',width:100,align:'center',formatter:function(value,row,index)
					{if(value==0)return '正常';else if(value==1)return '禁用';else return '已删除';}">状态</th>
				<th
					data-options="field:'id',width:100,align:'center',formatter:function(value,row,index)
					{return '<a href=javascript:showMenu();>查看菜单</a>';}">用户菜单管理</th>
					<th
					data-options="field:'item',width:100,align:'center',formatter:function(value,row,index)
					{return '<a href=javascript:showOrg();>查看组织</a>';}">用户组织管理</th>
				<th
					data-options="field:'roleName',width:100,align:'center',formatter:function(value,row,index)
					{if(value=='')return '未赋予角色';else return '<a href=javascript:showRole();>'+value+'</a>';}">查看角色</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	<div id="tb" style="padding: 5px; height: auto">
		<div>

			<table cellpadding="0px" cellspacing="0px"
				style="width: 500px; margin-left: 8px;">
				<tr>
					<td>用户名：<input class="inputs" id="search_name"
						style="width: 130px" /> <a href="javascript:search();"
						class="easyui-linkbutton" iconCls="icon-search">搜索</a></td>
				</tr>
			</table>
			<a id="add" href="#" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-add'">添加</a> <a id="edit"
				href="#" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-edit'">修改</a> <a id="del"
				href="#" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-remove'">删除</a>

		</div>
	</div>
	
	<!-- 查看菜单-->
	<div id="menuResource" class="easyui-window" closed=true modal=true
		style="width: 450px; height: 440px; padding: 10px; background-color: #E1ECFF;"
		title="用户菜单管理">
		<table width="100%" cellpadding="0" cellspacing="0" align="center">
			<tr>
				<td colspan="2"><hr /></td>
			</tr>
			<tr>
				<td colspan="2" height="320px">
					<ul id="treeDemo" class="ztree"></ul>
				</td>
			</tr>
			<tr>
				<td colspan="2"><hr /></td>
			</tr>
		</table>
		<div id="win-buttons" style="text-align: center; margin-top: 5px;">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" id="saveMenuUser">保存</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel"
				onclick="javascript:$('#menuResource').window('close')">取消</a>
		</div>
	</div>
	<!-- 查看组织-->
	<div id="orgResource" class="easyui-window" closed=true modal=true
		style="width: 450px; height: 440px; padding: 10px; background-color: #E1ECFF;"
		title="用户组织管理">
		<table width="100%" cellpadding="0" cellspacing="0" align="center">
			<tr>
				<td colspan="2"><hr /></td>
			</tr>
			<tr>
				<td colspan="2" height="320px">
					<ul id="orgDemo" class="ztree"></ul>
				</td>
			</tr>
			<tr>
				<td colspan="2"><hr /></td>
			</tr>
		</table>
		<div id="win-buttons" style="text-align: center; margin-top: 5px;">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" id="saveOrgUser">保存</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel"
				onclick="javascript:$('#orgResource').window('close')">取消</a>
		</div>
	</div>
	<!-- 查看权限 -->
	<div id="roleResource" class="easyui-window" closed=true modal=true
		style="width: 450px; height: 440px; padding: 10px; background-color: #E1ECFF;"
		title="查看角色">
		<table width="100%" cellpadding="0" cellspacing="0" align="center">
			<tr>
				<td style="font-weight: blod;">名称：</td>
				<td><input type="text" class="easyui-numberbox"
					style="width: 160px;" /></td>
			</tr>
			<tr>
				<td colspan="2"><hr /></td>
			</tr>
			<tr>
				<td colspan="2" height="300px">
				</td>
			</tr>
			<tr>
				<td colspan="2"><hr /></td>
			</tr>
		</table>
		<div id="win-buttons" style="text-align: center; margin-top: 5px;">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" id="saveResource">保存</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel"
				onclick="javascript:$('#roleResource').window('close')">取消</a>
		</div>
	</div>
	<div id="openWindow" class="easyui-window" closed=true modal=true
		style="width: 300px; height: 283px; padding: 10px;" title="添加用户">
		<form id="userEdit" method="post">
			<input name="id" type="hidden" />
			<table width="100%" cellpadding="0" cellspacing="0" align="center">
				<tr height="28px">
					<td>用户名：</td>
					<td><input type="text" class="easyui-validatebox"
						data-options="required:true" style="width: 150px;" name="userName"/>
						<span id="nameState" style="height:20px;color:red;position:relative;width:20px;"></span>
						</td>
				</tr>
				<tr height="28px">
					<td>昵称：</td>
					<td><input type="text" class="easyui-validatebox"
						data-options="required:true" style="width: 150px;" name="nickName" /></td>
				</tr>
				<tr height="28px">
					<td>密码：</td>
					<td><input type="password" class="easyui-validatebox"
						data-options="required:true" style="width: 150px;" name="pass" /></td>
				</tr>
				<tr height="28px">
					<td>状态：</td>
					<td><select id="cc" class="easyui-combobox" name="status"
						style="width: 150px;">
							<option value="0">正常</option>
							<option value="1">禁用</option>
							<option value="2">已删除</option>
					</select></td>
				</tr>
				<tr height="28px">
					<td>角色：</td>
					<td><input class="easyui-combobox" id="role" name="role"
						data-options="url:'${ctx }/join/cacheRoleJS.action',valueField:'id',
					textField:'text',required:true"
						style="width: 150px;" /></td>
				</tr>
				<tr height="28px">
					<td>操作人员：</td>
					<td><input class="easyui-combobox" id="member" name="member"
						data-options="url:'${ctx }/join/cacheMemberJS.action',valueField:'id',
					textField:'text',required:true"
						style="width: 150px;" /></td>
				</tr>
			</table>
		</form>
		<div id="win-buttons" style="text-align: center; margin-top: 5px;">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" id="save">保存</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#openWindow').window('close')">取消</a>
		</div>
	</div>

</body>
</html>
