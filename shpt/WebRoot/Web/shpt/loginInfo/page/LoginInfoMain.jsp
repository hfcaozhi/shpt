<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/Web/common/page/jqueryMaster.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=root%>/Web/common/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=root%>/Web/common/js/easyui/themes/icon.css">
<script type="text/javascript" src="<%=root%>/Web/common/js/easyui/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=root%>/Web/common/js/easyui/js/easyui-lang-zh_CN.js"></script>

<link rel="stylesheet" href="<%=root%>/Web/common/css/tooltip-form-style.css"></link>

<script type="text/javascript" src="<%=root%>/Web/ospm/loginInfo/js/loginInfoMain.js"></script>
</head>

<body>
<!-- 表格 -->
<table 	id="loginInfoTable"
		title="用户信息一览"  
		border="0"
		cellspacing="0"
		cellpadding="0"
		iconCls="icon-edit" 
		width="98%" 
		idField="loginId" 
		pagination="true"
		remoteSort="false" 
		singleSelect="false" 
		showFooter="false"
		striped="true" 
		url="<%=root%>/ospm/loginInfo/doLoginInfoSearch.jhtml">
	<thead>
		<tr align="center">
			<th field="ck" width="20" checkbox="true" width="20"></th>
			<th field="loginCode"  width="200">用户名</th>
			<th field="statuValue"  width="100">状态</th>
			<th field="opt" formatter='optFormater' width="150">操作</th>
		</tr>
	</thead>
</table>

<!-- 添加 -->
<div id="loginInfoAdd" icon="icon-save"
	style="padding: 5px; width: 500px; height: 300px;">
	<h5 id="loginInfoAdd_message" style="color: red;"></h5>
	<div class="ToolTip_Form" id="table_loginInfoAdd" onkeydown="if(event.keyCode==13){loginInfoAdd();}">
        <ul>
			<li>
				<label>用户名：</label>
				<input type="text" class="easyui-validatebox" id="loginInfoAdd_loginCode" maxlength="20" required="true"></input>
			</li>
			<li>
				<label>密码：</label>
				<input type="password" class="easyui-validatebox" id="loginInfoAdd_password" maxlength="20" required="true"></input>
			</li>
			<li>
				<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="loginInfoAdd();">提交</a>
				<a href="#" class="easyui-linkbutton" icon="icon-redo" onclick="loginInfoAddReset();">重置</a>
			</li>
		</ul>
	</div>
</div>

<!-- 编辑 -->
<div id="loginInfoEdit" icon="icon-save"
	style="padding: 5px; width: 500px; height: 300px;">
	<h5 id="loginInfoEdit_message" style="color: red;"></h5>
	<div class="ToolTip_Form" id="table_loginInfoEdit" onkeydown="if(event.keyCode==13){loginInfoEdit();}">
		<input type="hidden" id="loginInfoEdit_loginId"></input>
        <ul>
			<li>
				<label>用户名：</label>
				<label id="loginInfoEdit_loginCode"></label>
			</li>
			<li>
				<label>密码：</label>
				<input type="password" class="easyui-validatebox" id="loginInfoEdit_password" maxlength="20" required="true"></input>
			</li>
			<li>
				<label>重复密码：</label>
				<input type="password" class="easyui-validatebox" id="loginInfoEdit_repassword" maxlength="20" required="true"></input>
			</li>
			<li>
				<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="loginInfoEdit();">提交</a>
			</li>
		</ul>
	</div>
</div>
</body>
</html>
