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
	src="${ctx }/resources/scripts/easyui1.3/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/scripts/easyui1.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/scripts/easyui1.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/scripts/jquery-ui.js"></script>
<script type="text/javascript" src="${ctx }/resources/scripts/tree.js"></script>
<link rel="stylesheet"
	href="${ctx }/Web/common/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript"
	src="${ctx }/Web/common/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript"
	src="${ctx }/Web/common/js/jquery.ztree.excheck-3.5.js"></script>
<script>
	$(function() {
		
		//动态加载所有的数据
		$("#dg").datagrid({
			url : '${ctx}/join/healthIndicatorJS.action',
			onLoadSuccess : function(data) {
				$("#dg").datagrid("selectRow", 0);
			}
		});
		//添加
		$('#add').click(function() {
			$("#userEdit").form("clear");
			//relaod();
			
			url = ctx + "/join/healthIndicatoraddJS.action";
			$('#openWindow').window({
				title : "添加健康指标"
			});
			$('#openWindow').window('open');
		});
		//修改
		$('#edit').click(function() {
			$("#userEdit").form("clear");
			//relaod();
			var item = $('#dg').datagrid('getSelected');
			if (item) {
				url = ctx + "/join/healthIndicatorupdateJS.action";
				$('#openWindow').window({
					title : "修改疾病代码"
				});
				$('#openWindow').window('open');
				$('input[name=pass]').val('           ');
				$('#userEdit').form('load', {
					id : item.id,
					name : item.name,
					unit : item.unit,
					maxvalue:item.maxvalue,
					minvalue:item.minvalue,
					description : item.description
				}
				);
			} else {
				alert("请先选中一行再操作");
			}

		});
		//判断名字是否重复
		var nameExist = false;
		$('input[name=name]').focusout(function(){
			var name = $(this).val();
			$.post(ctx + '/join/healthIndicatorcountJS.action',{"name":name}, function(msg) {
				if(msg>0){
					$('#nameState').html('已存在');
					nameExist=true;
				}else{
					$('#nameState').html('');
					nameExist=false;
				}
			});
		});
			//删除
		$('#del').click(
				function() {
					$.messager.confirm('系统提示', '您确定要删除该条记录吗?', function(r) {
						if (r) {
							$.post(ctx + '/join/healthIndicatordeleteJS.action?id='
									+ $('#dg').datagrid('getSelected').id,
									function(msg) {
									var msg = eval('(' + msg + ')');
										$.messager.alert('提示', msg.message);
										$('#dg').datagrid('reload');
									});
						}
					});

				});
		//保存
		$('#save').click(function() {
			if(nameExist==true){
				return;
			}
			$('#userEdit').form('submit', {
				url : url,
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(result) {
					var result = eval('(' + result + ')');
					if (result.errorMsg) {
						$.messager.show({
							title : 'Error',
							msg : result.errorMsg
						});
					} else {
						$.messager.alert('提示', result.message);
						$('#openWindow').window('close');
						$('#dg').datagrid('reload');
					}
				}
			});
		});
		
	
	});
		//搜索数据
	function search() {
		$('#dg').datagrid('load', {
			name : $("#search_name").val(),
			id : $("#search_id").val()
			
		});
	}
</script>
</head>
<body>
	<table id="dg" title="疾病代码管理" fit=true
		data-options="pageSize:10,pageList:[1,5,10],rownumbers:true,pagination:true,singleSelect:true,toolbar:'#tb'">
		<thead>
			<tr>
				<th data-options="field:'id',width:170,align:'center'">健康指标编号</th>
				<th data-options="field:'name',width:170,align:'center'">健康指标名称</th>
				<th data-options="field:'maxvalue',width:170,align:'center'">最大值</th>
				<th data-options="field:'minvalue',width:170,align:'center'">最小值</th>
				<th data-options="field:'unit',width:170,align:'center'">单位</th>
				<th data-options="field:'description',width:170,align:'center'">描述</th>
		</thead>
		<tbody>
		</tbody>
	</table>
	<div id="tb" style="padding: 5px; height: auto">
		<div>

			<table cellpadding="0px" cellspacing="0px"
				style="width: 500px; margin-left: 8px;">
				<tr>
					<td>编号：<input class="inputs" id="search_id" style="width: 130px" />
						名称：<input class="inputs" id="search_name" style="width: 130px" /> <a href="javascript:search()" class="easyui-linkbutton"
						iconCls="icon-search">搜索</a></td>
				</tr>
			</table>
			<a id="add" href="#" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-add'">添加</a> <a id="edit"
				href="#" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-edit'">修改</a> <a id="del"
				href="#" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-remove'">删除</a>

		</div>
	</div>
	
	
	
	
	<div id="openWindow" class="easyui-window" closed=true modal=true
		style="width: 300px; height: 283px; padding: 10px;" title="添加健康指标">
		<form id="userEdit" method="post">
			<input name="id" type="hidden" />
			<table width="100%" cellpadding="0" cellspacing="0" align="center">
				<tr height="28px">
					<td>疾病名称：</td>
					<td><input type="text" class="easyui-validatebox"
						data-options="required:true" style="width: 150px;" name="name"/>
						<span id="nameState" style="height:20px;color:red;position:relative;width:20px;"></span>
						</td>
				</tr>
				
				<tr height="28px">
					<td>最大值：</td>
					<td><input type="text" class="easyui-validatebox"
						data-options="" style="width: 150px;" name="maxvalue" /></td>
				</tr>
				<tr height="28px">
					<td>最小值：</td>
					<td><input type="text" class="easyui-validatebox"
						data-options="" style="width: 150px;" name="minvalue" /></td>
				</tr>
				<tr height="28px">
					<td>单位：</td>
					<td><input type="text" class="easyui-validatebox"
						data-options="" style="width: 150px;" name="unit" /></td>
				</tr>
				<tr height="28px">
					<td>描述：</td>
					<td><input type="text" class="easyui-validatebox"
						data-options="" style="width: 150px;" name="description" /></td>
				</tr>
				
				
				
			</table>
		</form>
		<div id="win-buttons" style="text-align: center; margin-top: 5px;">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" id="save">保存</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#openWindow').window('close')">取消</a>
		</div>
	</div>

</body>
</html>
