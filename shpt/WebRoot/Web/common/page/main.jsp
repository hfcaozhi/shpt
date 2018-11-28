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

<script type="text/javascript" src="<%=root%>/Web/common/js/main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户权限管理系统</title>
</head>
<!-- 设置了class就可在进入页面加载layout -->
<body class="easyui-layout">
	<!-- 正上方panel -->
    <div region="north" style="height:100px;padding:10px;" href="<%=root%>/Web/common/page/top.html"></div>
    <!-- 正左边panel -->
    <div region="west" title="菜单栏" split="true" style="width:280px;padding1:1px;overflow:hidden;">
    	<div class="easyui-accordion" fit="true" border="false">
    		<!-- selected -->
			<div title="用户权限管理" selected="true">
			<ul>
				<li><a href="javascript:addTab('tabId_loginInfo','用户管理','<%=root%>/ospm/loginInfo/goLoginInfoMain.do');">用户管理</a></li>
				<li><a href="javascript:addTab('tabId_privilege','权限管理','<%=root%>/ospm/loginInfo/goPrivilegeMain.jhtml');">权限管理</a></li>
			</ul>
			</div>
		</div>
   	</div>
    <!-- 正中间panel -->
    <div region="center" title="功能区" >
    	<div class="easyui-tabs" id="centerTab" fit="true" border="false">
			<div title="欢迎页" style="padding:20px;overflow:hidden;"> 
				<div style="margin-top:20px;">
					<h3>你好，欢迎来到权限管理系统</h3>
				</div>
			</div>
		</div>
    </div>
    <!-- 正下方panel -->
    <div region="south" style="height:50px;" align="center">
    	<label>
    		作者：白糖<br/>
    		时间：2011-5-17
    	</label>
    </div>
</body>
</html>