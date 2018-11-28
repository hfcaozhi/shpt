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
      //动态加载所有的数据
		$("#dg").datagrid({
			url : '${ctx}/join/memberJS.action',
			onLoadSuccess : function(data) {
				$("#dg").datagrid("selectRow", 0);
			}
		});
		 $('#view').click(function() {
		   var medicalId=$('#dg').datagrid('getSelected').medicalId;
		   location.href='${ctx}/join/toMedicalReportJS.action?medicalId='+medicalId;
		});
	
});
//搜索数据
function search() {
	$('#dg').datagrid('load', {
		name : $("#search_name").val(),
		medicalId : $("#search_medicalId").val()

	});
}
</script>
	</head>
	<body>
		<table id="dg" title="体检人员信息" fit=true
			data-options="pageSize:10,pageList:[1,5,10],rownumbers:true,pagination:true,singleSelect:true,toolbar:'#tb'">
			<thead>
				<tr>
					<th data-options="field:'medicalId',width:100,align:'center'">
						体检号
					</th>
					<th data-options="field:'name',width:70,align:'center'">
						姓名
					</th>
					<th data-options="field:'unit',width:120,align:'center'">
						单位
					</th>
					<th data-options="field:'mobPhone',width:100,align:'center'">
						手机
					</th>
					<th data-options="field:'sex',width:70,align:'center'">
						性别
					</th>
					<th data-options="field:'age',width:70,align:'center'">
						年龄
					</th>
					<th data-options="field:'marriage',width:70,align:'center'">
						婚姻
					</th>
					<th data-options="field:'nationality',width:70,align:'center'">
						国籍
					</th>
					
					
			</thead>
			<tbody>
			</tbody>
		</table>
		<div id="tb" style="padding: 5px; height: auto">
			<div>

				<table cellpadding="0px" cellspacing="0px"
					style="width: 500px; margin-left: 8px;">
					<tr>
						<td>
							体检人员姓名：
							<input class="inputs" id="search_name" style="width: 130px" />
							体检号：
							<input class="inputs" id="search_medicalId" style="width: 130px" />
							<a href="javascript:search()" class="easyui-linkbutton"
								iconCls="icon-search">查询</a>
						</td>
					</tr>
				</table>
				<a id="view" href="#" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-add'">查看</a>
				

			</div>
		</div>


	</body>
</html>
