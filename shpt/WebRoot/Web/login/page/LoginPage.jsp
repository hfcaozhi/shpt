<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/Web/common/page/jqueryMaster.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- jquery easyui -->
<link rel="stylesheet" type="text/css" href="<%=root%>/Web/common/js/easyui/themes/dayun/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=root%>/Web/common/js/easyui/themes/icon.css">
<script type="text/javascript" src="<%=root%>/Web/common/js/easyui/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=root%>/Web/common/js/easyui/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=root%>/Web/common/js/easyui/validate/easyui_validate.js"></script>

<script type="text/javascript" src="<%=root%>/Web/login/js/loginPage.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页</title>
</head>
<body>
<div id="login" style="padding: 5px; width: 400px; height: 220px;">
<table onkeydown="if(event.keyCode==13){doLogin();}" id="loginTable">
	<tr>
		<td align="right"><b style="font: 12px">用户名：</b></td>
		<td><input type="text" class="easyui-validatebox" id="loginCode" style="width:200px;" maxlength="20" required="true"></input></td>
	</tr>
	<tr>
		<td align="right"><b style="font: 12px">密码：</b></td>
		<td><input type="password" class="easyui-validatebox" id="password" style="width:200px;" maxlength="20" required="true"></input></td>
	</tr>
	<tr>
		<td></td>
		<td id="login_msg" style="color: red;font: 12px">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="doLogin();">确定</a>
			<a href="#" class="easyui-linkbutton" icon="icon-redo" onclick="doReset();">重置</a>
		</td>
	</tr>
</table>
</div>

</body>
</html>