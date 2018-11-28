<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/Web/common/page/master.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
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
<script type="text/javascript" src="${ctx}/resources/scripts/easyui1.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/scripts/jquery-ui.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/scripts/tree.js"></script>
	
<script type="text/javascript"
	src="${ctx }/resources/scripts/role.js"></script>	
<script type="text/javascript">
	

</script>
</head>

<body>
	<table id="dg"  title="角色管理" fit=true
		data-options="pageSize:10,pageList:[1,5,10],rownumbers:true,pagination:true,singleSelect:true,toolbar:'#tb'">
		<thead>
			<tr>
				<th data-options="field:'name',width:200,align:'center'">角色名称</th>  
				<th data-options="field:'description',width:500,align:'center'">说明</th>
				<th data-options="field:'status',width:100,align:'center',formatter:function(value,row,index)
					{if(value==0)return '正常';else if(value==1)return '禁用';else return '已删除';}">状态</th>
				<th data-options="field:'id',width:100,align:'center',formatter:function(value,row,index)
					{return '<a href=javascript:showResource();>查看权限</a>';}">查看权限</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding: 5px; height: auto">
		<div>

			<table cellpadding="0px" cellspacing="0px" style="width: 500px;margin-left: 8px;">
				<tr>
					<td width="70px">角色名称：<input id="search_name" class="inputs"/> <a href="javascript:search();" class="easyui-linkbutton"
						iconCls="icon-search">搜索</a></td>
				</tr>
			</table>
			<a id="add" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
			<a id="edit" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">修改</a>
			<a id="del" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'">删除</a>
			
		</div>
	</div>
	<!-- 修改窗口 -->
	<div id="editWindow" class="easyui-window" closed=true modal=true
		style="width: 590px; height: 500px; padding: 10px; background-color:#E1ECFF;" title="修改角色">
		<form id="fedit" method="post">
		<input name="id" type="hidden" />
		<table width="100%" height="70%" cellpadding="2" cellspacing="2" align="center">
			<tr> 
				<td  style="font-weight:blod;">名称：</td>
				<td>
				<input class="inputs" name="name" class="easyui-validatebox" data-options="required:true" />  
				</td>
			</tr>
			<tr>
				<td style="font-weight:blod;">说明：</td>
				<td><input name="description" type="text" class="inputs" value=""/></td>
			</tr>
			<tr>
				<td style="font-weight:blod;">状态：</td>
				<td>
				<select id="cc" class="easyui-combobox" name="status" style="width:150px;">  
				    <option value="0">正常</option>  
				    <option value="1">禁用</option>  
				    <option value="2">已删除</option>  
				</select> 
				
				</td> 
			</tr>
			
			<tr>
				<td colspan="2"><hr /></td>
			</tr>
			<tr >
				<td colspan="2" >
					<div class="zm_body"></div>
				</td>
			</tr>
		</table>
		
		</form>
		<div  style="text-align: center; border:0px solid red; ">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" onclick="saveRole()">保存</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel"
				onclick="javascript:$('#editWindow').window('close')">取消</a>
		</div>
	</div>  
</body>
</html>
