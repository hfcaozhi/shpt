<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/Web/common/page/master.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>主页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="城管执法">

<link href="resources/css/default.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="resources/scripts/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="resources/scripts/themes/icon.css" />

<script type="text/javascript"
	src="resources/scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="resources/scripts/jquery.easyui.js"></script>
<script type="text/javascript" src='resources/scripts/outlook2.js'></script>
<script type="text/javascript" src='resources/scripts/index.js'></script>

<script type="text/javascript">

var userId = "${session_user_id.id}";

	var _menus = {   
		"menus" : [ {
			"menuid" : "1",
			"icon" : "icon-sys",
			"menuname" : "巡逻队员查看",
			"url" : "portal_show_user.html",
			"menus" : [ {
				"menuname" : "巡逻队员1",
				"url" : "portal_show_user.html"
			}, {
				"menuname" : "巡逻队员2",
				"url" : "portal_show_user.html"
			}, {
				"menuname" : "巡逻队员3",
				"url" : "portal_show_user.html"
			}, {
				"menuname" : "巡逻队员4",
				"url" : "portal_show_user.html"
			} ]
		}, {
			"menuid" : "2",
			"icon" : "icon-sys",
			"menuname" : "巡逻车辆查看",
			"url" : "portal_show_user.html",
			"menus" : [ {
				"menuname" : "巡逻车辆1",
				"url" : "portal_show_user.html"
			}, {
				"menuname" : "巡逻车辆2",
				"url" : "portal_show_user.html"
			}, {
				"menuname" : "巡逻车辆3",
				"url" : "portal_show_user.html"
			}, {
				"menuname" : "巡逻车辆4",
				"url" : "portal_show_user.html"
			} ]
		}, {
			"menuid" : "4",
			"icon" : "icon-sys",
			"menuname" : "全球眼",
			"menus" : [ {
				"menuname" : "监控1",
				"url" : "megaeyes.html"
			} ]
		}, {
			"menuid" : "5",
			"icon" : "icon-sys",
			"menuname" : "城管内容管理",
			"menus" : [ {
				"menuname" : "队员信息",
				"url" : "personal_info.html"
			}, {
				"menuname" : "执法车信息",
				"url" : "member.html"
			}, {
				"menuname" : "组织结构信息",
				"url" : "organization_tructure.html"
			}, {
				"menuname" : "城管事件信息",
				"url" : "unchecked_event.html"
			}, {
				"menuname" : "被处罚人信息",
				"url" : "punish_info.html"
			} ]
		} ]
	};
	//设置修改密码窗口
	function openPwd() {
		$('#w').window({
			title : '修改密码',
			width : 300,
			modal : true,
			shadow : true,
			closed : true,
			height : 160,
			resizable : false
		});
		
	}
	//关闭修改密码窗口
	function close() {

		$('#w').window('close');
	}

	//修改密码
	function serverLogin() {
		var $newpass = $('#txtNewPass');
		var $rePass = $('#txtRePass');

		if ($newpass.val() == '') {
			msgShow('系统提示', '请输入密码！', 'warning');
			return false;
		}
		if ($rePass.val() == '') {
			msgShow('系统提示', '请在一次输入密码！', 'warning');
			return false;
		}

		if ($newpass.val() != $rePass.val()) {
			msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
			return false;
		}

		$.post(ctx+'/join/userUpdatePassJS.action?pass=' + $newpass.val(), function(
				msg) {
			msgShow('系统提示',  msg.message, 'info');
			$newpass.val('');
			$rePass.val('');
			close();
		});

	}

	$(function() {

		openPwd();
		//
		$('#editpass').click(function() {
			$('#w').window('open');
		});

		$('#btnEp').click(function() {
			serverLogin();
		});

		$('#loginOut').click(function() { 
			$.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {

				if (r) {
					location.href = ctx+'/j_spring_security_logout';
				}
			});

		});
	});
</script>
</head>

<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<noscript>
		<div
			style="position: absolute; z-index: 100000; height: 2046px; top: 0px; left: 0px; width: 100%; background: white; text-align: center;">
			<img src="resources/images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
	<div region="north" border="false"
	 	style="overflow: hidden; height: 65px; background: url(resources/images/index.png) #7f99be repeat-x center 50%; line-height: 20px; color: #fff; font-family: Verdana, 微软雅黑, 黑体">

		<div style="float: right;vertical-align:middle; padding-right: 20px; height:20px;margin-top: 38px;"
			class="head" >
			<div style="float: left; border:0px solid red; height:20px;"  >
				欢迎： ${session_user_id.nickName }&nbsp;|&nbsp;
				<!-- 
				<img
					src="resources/images/table3_verticalline_10.gif" width="2px"
					height="10px"  />  -->
			 </div>
			<div id="menu_sys" style="float: left;">
			</div>
			<div style="float: left;">
			<!-- 
				<img src="resources/images/table3_verticalline_10.gif" width="2px"
					height="10px" />  -->&nbsp;|&nbsp;
				<a href="javascript:void(0);" id="loginOut"
					style="text-decoration: none;">安全退出</a>
			</div>
		</div> 
		<span style="padding-left: 10px; font-size: 26px;"> <img
			src="resources/images/logo1.png" width="50px" height="60px"
			align="absmiddle" /> 执法局信息化智能平台
		</span>

	</div>
	<div region="south" style="height: 30px; background: #D2E0F2;">
		<div class="footer">&nbsp;中国电信上海理想信息产业（集团）有限公司</div>
	</div>
	<div region="west" title="导航菜单" style="width: 180px;"
		id="west">
		<div class="easyui-accordion" fit="true" border="false">
			<!--  导航内容 -->

		</div>

	</div>
	<!-- overflow-y:hidden -->
	<div id="mainPanle" region="center"
		style="background: #eee;overflow:hidden;">
		<div id="tabs" class="easyui-tabs" fit="true" border="false"
			closable="true">
			<div title="欢迎使用" style="padding: 20px; overflow: hidden;" id="home" fit="true">
				<h1>欢迎使用!祝你工作愉快！</h1>
			</div>
		</div>
	</div>


	<!--修改密码窗口--> 
	<div id="w" class="easyui-window" title="修改密码" collapsible="false"
		minimizable="false" maximizable="false" icon="icon-save"
		style="width: 300px; height: 150px; padding: 5px; background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc;">
				<table cellpadding=3>
					<tr>
						<td>新密码：</td>
						<td><input id="txtNewPass" type="Password" class="txt01" /></td>
					</tr>
					<tr>
						<td>确认密码：</td>
						<td><input id="txtRePass" type="Password" class="txt01" /></td>
					</tr>
				</table>
			</div>
			<div region="south" border="false"
				style="text-align: right; height: 30px; line-height: 30px;">
				<a id="btnEp" class="easyui-linkbutton" icon="icon-ok"
					href="javascript:void(0)"> 确定</a> <a class="easyui-linkbutton"
					icon="icon-cancel" href="javascript:close()">取消</a>
			</div>
		</div>
	</div> 
 
	<div id="mm" class="easyui-menu" style="width: 150px;">
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>

</body>
</html>
