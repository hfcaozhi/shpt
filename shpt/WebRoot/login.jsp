<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/Web/common/page/master.jsp"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">

<title>登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<LINK href="images/Default.css" type=text/css rel=stylesheet>
<LINK href="images/xtree.css" type=text/css rel=stylesheet>
<LINK href="images/User_Login.css" type=text/css rel=stylesheet>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script>
	function post() {
		$('form1').submit();
	}
</script>
</head>

<body id=userlogin_body>
<div align="center" >
	<form name="form1" action="j_spring_security_check" method="post">


		<DIV></DIV>

		<DIV id=user_login>
			<DL>
				<DD id=user_top>
					<UL>
						<LI class=user_top_l></LI>
						<LI class=user_top_c></LI>
						<LI class=user_top_r></LI>
					</UL>
				<DD id=user_main>
					<UL>
						<LI class=user_main_l >
						<img src="images/logo.png" width=100px; height=100px; style="padding-left: 20px;padding-bottom:10px;" />
					</LI> 
						<LI class=user_main_c>
							<!-- tt -->
							<DIV class=user_main_box>
								<UL>
									<LI style="WIDTH: 60px;">&nbsp;</LI>
									<LI><c:if test="${not empty login_message}">
											<span style="color: red">${login_message }</span>
										</c:if></LI>
								</UL>
								<UL>
									<LI class=user_main_text>用户名：</LI>
									<LI ><INPUT class=TxtUserNameCssClass
										id=TxtUserName maxLength=20 name=username></LI>
								</UL>
								<UL>
									<LI class=user_main_text>密 码：</LI>
									<LI class=user_main_input><INPUT class=TxtPasswordCssClass
										id=TxtPassword type=password maxLength=20 name=password></LI>
								</UL>
								<UL>
									<LI class=user_main_input></LI>

								</UL>
							</DIV> <!-- tt end -->
						</LI>
						<LI class=user_main_r><INPUT class=IbtnEnterCssClass
							id=IbtnEnter
							style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px"
							onclick='post()' type=image src="images/user_botton.gif"
							name=IbtnEnter></LI>
					</UL>
				<DD id=user_bottom>
					<UL>
						<LI class=user_bottom_l></LI>
						<LI class=user_bottom_c></LI>
						<LI class=user_bottom_r></LI>
					</UL>
				</DD>
			</DL>
		</DIV>
		<SPAN id=ValrUserName style="DISPLAY: none; COLOR: red"></SPAN> <SPAN
			id=ValrPassword style="DISPLAY: none; COLOR: red"></SPAN> <SPAN
			id=ValrValidateCode style="DISPLAY: none; COLOR: red"></SPAN>
		<DIV id=ValidationSummary1 style="DISPLAY: none; COLOR: red"></DIV>

		<DIV></DIV>
	</form>
</div>
</body>
</html>
