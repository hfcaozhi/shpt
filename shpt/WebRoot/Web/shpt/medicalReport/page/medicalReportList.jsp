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
			src="${ctx }/resources/scripts/easyui1.3/jquery-1.8.0.min.js">
</script>
		<script type="text/javascript"
			src="${ctx }/resources/scripts/easyui1.3/jquery.easyui.min.js">
</script>
		<script type="text/javascript"
			src="${ctx}/resources/scripts/easyui1.3/locale/easyui-lang-zh_CN.js">
</script>
		<script type="text/javascript"
			src="${ctx }/resources/scripts/jquery-ui.js">
</script>
		<script type="text/javascript" src="${ctx }/resources/scripts/tree.js">
</script>
		<link rel="stylesheet"
			href="${ctx }/Web/common/css/zTreeStyle/zTreeStyle.css"
			type="text/css">
		<script type="text/javascript"
			src="${ctx }/Web/common/js/jquery.ztree.core-3.5.js">
</script>
		<script type="text/javascript"
			src="${ctx }/Web/common/js/jquery.ztree.excheck-3.5.js">
</script>
		<!--<SCRIPT type="text/javascript" src="${ctx }/resources/scripts/jquery-1.4.2.min.js"></SCRIPT>
-->
		<script>
$(function() {
	 var medicalId=$("#medicalId").val();
	$.ajax( {
		url : "${ctx}/join/medicalReportJS.action?medicalId=" + medicalId,
		dataType : 'json',
		async : false,
		success : function(data) {

			alert(data);
		}
	});

});
</script>
	</head>
	<body>
	<input type="hidden" value="${medicalId}" id="medicalId"/>
		<div style="height: 500px; width: 100%;">

			<div style="width: 100%; float: right">
				<div
					style="width: 20%; float: left; border: 1px solid #ccc; height: 500px">
					<table class="table">
						<tr>
							<th>
								历年体检信息
							</th>
						</tr>
						<tr>
							<td>
								<a href="#">2012年体检信息</a>
							</td>
						</tr>
						<tr>
							<td>
								<a href="#">2011年体检信息</a>
							</td>
						</tr>
						<tr>
							<td>
								<a href="#">2010年体检信息</a>
							</td>
						</tr>
					</table>
				</div>
				<div
					style="width: 79%; float: left; border: 1px solid #ccc; height: 500px">
					<center>
						<p>
							<h5>
								<strong>小明</strong>2013年的体检信息
							</h5>
						</p>
					</center>
					<table>
						<tr>
							<td>
								<a href="#">体检信息</a>
							</td>
							<td>
								<a href="#">评估意见</a>
							</td>
							<td>
								<a href="#">审核意见</a>
							</td>
						</tr>
					</table>
					<div id="myTabContent" class="" style="">

						<div class="" id="profile" style="">
							<div style="width: 35%;">
								<table>
									<tr>
										<td id="medical_info" style="">
											<p>
												很好，没什么毛病
											</p>
										</td>
									</tr>
									<tr>
										<td id="assess_info" style="display: none;">
											<p>
												很好，没什么毛病
											</p>
										</td>
									</tr>
									<tr>
										<td id="auditing_info" style="display: none;">
											<p>
												很好，没什么毛病
											</p>
										</td>
									</tr>
								</table>
							</div>
						</div>

					</div>

				</div>

			</div>

		</div>


	</body>
</html>
