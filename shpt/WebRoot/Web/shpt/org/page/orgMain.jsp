<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/Web/common/page/master.jsp"%>
<html>
<head>
<title>组织结构</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${ctx }/resources/css/jquery.jOrgChart.css" />
<link rel="stylesheet" href="${ctx }/resources/css/custom.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx }/resources/scripts/easyui1.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${ctx }/resources/scripts/easyui1.3/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${ctx }/resources/scripts/easyui1.3/demo/demo.css">
<script type="text/javascript"
	src="${ctx }/resources/scripts/prettify.js"></script>
<!-- jQuery includes -->
<script type="text/javascript"
	src="${ctx }/resources/scripts/easyui1.3/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/scripts/easyui1.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/scripts/easyui1.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/scripts/jquery-ui.js"></script>
<script src="${ctx }/resources/scripts/jquery.jOrgChart.js"></script>
<script src="${ctx }/resources/scripts/org.js"></script>
</head>

<body>
	<div>
		<a href="#" class="easyui-linkbutton"
			data-options="plain:true,iconCls:'icon-add'" id="add">添加</a> <a
			href="#" class="easyui-linkbutton"
			data-options="plain:true,iconCls:'icon-edit'" id="update">修改</a> <a
			href="#" class="easyui-linkbutton"
			data-options="plain:true,iconCls:'icon-remove'" id="del">删除</a>
	</div>
	<div id="chart" align="center" class="chart"></div>
	<ul id="org" style="display: none;"></ul>
	<div id="orgWindow" class="easyui-window" closed=true modal=true
		style="width: 300px; height: 245px; padding: 10px;" title="添加组织结构">
		<form id="orgEdit" method="post">
		<input type="hidden" name="id"/>
		<table>
			<tr height="28px">
				<td>组织名称：</td>
				<td><input type="text" class="easyui-validatebox"
					data-options="required:true,validType:'length[1,20]'" name="name"
					style="width: 150px" /></td>
			</tr>
			<tr height="28px">
				<td>父节点：</td>
				<td><input class="easyui-combotree" name="parentId" id="orgParentId"
					data-options="url:'${ctx }/join/cacheOrgParentJS.action'"
					style="width: 152px;"></td>
			</tr>
			<tr height="28px">
				<td>说明：</td>
				<td><textarea class="easyui-validatebox"
						style="height: 80px; width: 150px" name="description"></textarea></td>
			</tr>
		</table>
		<div id="win-buttons" style="text-align: center; margin-top: 5px;">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" id="save">保存</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#orgWindow').window('close')">取消</a>
		</div>
		</form>
	</div>
</body>
</html>
