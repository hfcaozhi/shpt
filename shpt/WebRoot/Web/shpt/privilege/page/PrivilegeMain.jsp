<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/Web/common/page/jqueryMaster.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<!-- ZTree树形插件 -->
<link rel="stylesheet" href="<%=root%>/Web/common/css/zTreeStyle/zTreeStyle.css" type="text/css">
<!-- <link rel="stylesheet" href="<%=root%>/Web/common/css/zTreeStyle/zTreeIcons.css" type="text/css">  -->
<link rel="stylesheet" href="<%=root%>/Web/common/css/zTreeStyle/demo.css" type="text/css">
<script type="text/javascript" src="<%=root%>/Web/common/js/jquery-ztree-2.5.min.js"></script>
<!-- easyUI插件 -->
<link rel="stylesheet" type="text/css" href="<%=root%>/Web/common/js/easyui/themes/dayun/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=root%>/Web/common/js/easyui/themes/icon.css">
<script type="text/javascript" src="<%=root%>/Web/common/js/easyui/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=root%>/Web/common/js/easyui/js/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
	
	var url = "/ospm/loginInfo/doGetPrivilegeTree.action";
	//zTree基本设置
	var setting = {
		async : true, //需要采用异步方式获取子节点数据,默认false
		asyncUrl : root + url, //当 async = true 时，设置异步获取节点的 URL 地址 ,允许接收 function 的引用
		asyncParam : ["id"], //提交的与节点数据相关的必需参数
		isSimpleData : true, //数据是否采用简单 Array 格式，默认false
		treeNodeKey : "id", //在isSimpleData格式下，当前节点id属性
		treeNodeParentKey : "parentId", //在isSimpleData格式下，当前节点的父节点id属性
		nameCol : "privName",            //在isSimpleData格式下，当前节点名称
		expandSpeed : "fast", //设置 zTree节点展开、折叠时的动画速度或取消动画(三种默认定义："slow", "normal", "fast")或 表示动画时长的毫秒数值(如：1000) 
		showLine : true, //是否显示节点间的连线
		callback : { //回调函数
			rightClick : zTreeOnRightClick
		//右键事件
		}
	};

	//显示右键菜单
	function showRMenu(type, x, y) {
		$("#rMenu ul").show();
		if (type=="root") {
			$("#m_del").hide();
			$("#m_check").hide();
			$("#m_unCheck").hide();
		}
		$("#rMenu").css({"top":y+"px", "left":x+"px", "display":"block"});
	}
	//隐藏右键菜单
	function hideRMenu() {
		$("#rMenu").hide();
	}
	
	//鼠标右键事件-创建右键菜单
	function zTreeOnRightClick(event, treeId, treeNode) {
		if (!treeNode) {
			zTree.cancelSelectedNode();
			showRMenu("root", event.clientX, event.clientY);
		} else if (treeNode && !treeNode.noR) { //noR属性为true表示禁止右键菜单
			if (treeNode.newrole && event.target.tagName != "a" && $(event.target).parents("a").length == 0) {
				zTree.cancelSelectedNode();
				showRMenu("root", event.clientX, event.clientY);
			} else {
				zTree.selectNode(treeNode);
				showRMenu("node", event.clientX, event.clientY);
			}
		}
	}

	//右键菜单-添加操作
	function addPrivilege(){
		//获取父节点信息
		var node = zTree.getSelectedNode();
		//打开对话框
		openDialog_add();
	}

	/**--------------添加操作弹出框------------------**/
	//设置弹出框的属性
	function setDialog_add(){
		$('#addPrivilege').dialog({
			buttons:[{
				text:'确定',
				iconCls:'icon-ok',
				handler:function(){
					alert('ok');
				}
			},{
				text:'取消',
				handler:function(){
					closeDialog_add();
				}
			}]
		});
	}
	//打开对话框
	function openDialog_add(){
		document.addPrivilegeForm.reset();
		$('#addPrivilege').dialog('open');
	}
	//关闭对话框
	function closeDialog_add(){
		$('#addPrivilege').dialog('close');
	}
	
	function reloadTree() {
		hideRMenu();
		zTree = $("#tree").zTree(setting, treeNodes);
	}

	function bindMouseDowm(){
		$("body").bind(//鼠标点击事件不在节点上时隐藏右键菜单
				"mousedown",
				function(event) {
					if (!(event.target.id == "rMenu" || $(event.target)
							.parents("#rMenu").length > 0)) {
						$("#rMenu").hide();
					}
				});
	}
	
	
	var zTree;
	var treeNodes = [];
	
	$(function() {
		reloadTree();
		bindMouseDowm();

		setDialog_add();
		closeDialog_add();
	});
</script>
</head>

<body>
	<h3>权限管理</h3><br/>
	<hr>
	<ul id="tree" class="tree" style="width:300px; overflow:auto;"></ul>
	
	<!-- 右键菜单div -->
	<div id="rMenu" style="position:absolute; display:none;">
	<li>
	<ul id="m_add" onclick="addPrivilege();"><li>增加</li></ul>
	<ul id="m_del" onclick="delPrivilege();"><li>删除</li></ul>
	<ul id="m_del" onclick="editPrivilege();"><li>编辑</li></ul>
	<ul id="m_del" onclick="queryPrivilege();"><li>查看</li></ul>
	</li>
	</div>
	<div id="addPrivilege" icon="icon-save" style="padding:5px;width:400px;height:300px;">
	<form action="#" method="POST" name="addPrivilegeForm" id="addPrivilegeForm">
	<table>
		<tr>
			<td>父权限Id：</td>
			<td><input name="privilege.parentId" id="add_parentId" readonly="readonly"></td>
		</tr>
		<tr>
			<td>权限名：</td>
			<td><input name="privilege.privName" id="add_privName"></td>
		</tr>
		<tr>
			<td>对应标签名：</td>
			<td><input name="privilege.tagName" id="add_tagName"></td>
		</tr>
		<tr>
			<td>标签属性名：</td>
			<td><input name="privilege.tagAttrname" id="add_tagAttrname"></td>
		</tr>
		<tr>
			<td>标签属性值：</td>
			<td><input name="privilege.tagAttrvalue" id="add_tagAttrvalue"></td>
		</tr>
		<tr>
			<td>访问路径：</td>
			<td><input name="privilege.actionUrl" id="add_actionUrl"></td>
		</tr>
		<tr>
			<td>简介：</td>
			<td><textarea name="privilege.description" id="add_description" rows="3" cols="17" ></textarea></td>
		</tr>
	</table>
	</form>
	</div>
</body>
</html>
