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
	//添加
	$('#add').click(function() {
		$("#userEdit").form("clear");
		//relaod();

			url = ctx + "/join/diseaseCodeaddJS.action";
			$('#openWindow').window( {
				title : "添加疾病代码"
			});
			$('#openWindow').window('open');
		});

	//修改
	$('#edit').click(function() {
		$("#userEdit").form("clear");
		//relaod();
			var item = $('#dg').datagrid('getSelected');
			if (item) {
				url = ctx + "/join/diseaseCodeUpdateJS.action";
				$('#openWindow').window( {
					title : "修改疾病代码"
				});
				$('#openWindow').window('open');
				$('input[name=pass]').val('           ');
				$('#userEdit').form('load', {
					id : item.id,
					name : item.name,
					type : item.type,
					description : item.description
				});
			} else {
				alert("请先选中一行再操作");
			}

		});
	var nameExist = false;
	$('input[name=name]').focusout(function() {
		var name = $(this).val();
		$.post(ctx + '/join/diseaseCodecountJS.action', {
			"name" : name
		}, function(msg) {
			if (msg > 0) {
				$('#nameState').html('已存在');
				nameExist = true;
			} else {
				$('#nameState').html('');
				nameExist = false;
			}
		});
	});
	//保存
	$('#save').click(function() {
		if (nameExist == true) {
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
					$.messager.show( {
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
	//删除
	$('#del').click(
			function() {
				$.messager.confirm('系统提示', '您确定要删除该条记录吗?', function(r) {
					if (r) {
						$.post(ctx + '/join/diseaseCodedeleteJS.action?id='
								+ $('#dg').datagrid('getSelected').id,
								function(msg) {
									var msg = eval('(' + msg + ')');
									$.messager.alert('提示', msg.message);
									$('#dg').datagrid('reload');
								});
					}
				});

			});
	//动态加载所有的数据
	$("#dg").datagrid( {
		url : '${ctx}/join/diseaseCodeJS.action',
		onLoadSuccess : function(data) {
			$("#dg").datagrid("selectRow", 0);
		}
	});

	//移到右边
	$('#removehis').click(function() {
		//获取选中的选项，删除并追加给对方
		$('#his option:selected').appendTo('#hisall');
		});
	//移到左边
	$('#addhis').click(function() {
		$('#hisall option:selected').appendTo('#his');
	});
	//全部移到右边
	$('#remove_all').click(function() {
		//获取全部的选项,删除并追加给对方
		$('#his option').appendTo('#hisall');
		});
	//全部移到左边
	$('#add_all').click(function() {
		$('#hisall option').appendTo('#his');
	});
	//双击选项
	$('#his').dblclick(function() { //绑定双击事件
				//获取全部的选项,删除并追加给对方
		$("option:selected", this).appendTo('#hisall'); //追加给对方
			});
	//双击选项
	$('#hisall').dblclick(function() {
		$("option:selected", this).appendTo('#his');
	});
	//保存健康指标的修改
	$('#savehis').click(
			function() {
				var value = '&hislist=';

				$("#his option").each(function(i, obj) {
					if (i > 0) {
						value += ',';
					}
					value += $(obj).val();
				});

				$('#hisEdit').form(
						'submit',
						{
							url : '${ctx}/join/diseaseCodehisEditJS.action?id='
									+ $("#id_his").val() + value,
							success : function(result) {
								var result = eval('(' + result + ')');
								if (result.errorMsg) {
									$.messager.show( {
										title : 'Error',
										msg : result.errorMsg
									});
								} else {
									$.messager.alert('提示', result.message);
									$('#Indicator').window('close');
									//$('#dg').datagrid('reload');
						}
					}
						});
			});
});
//修改健康指标
function showIndicator() {
	$("#hisall").empty();
	$("#his").empty();
	$("#id_his").val($('#dg').datagrid('getSelected').id);
	$("#diseasename").html($('#dg').datagrid('getSelected').name);
	var id = $("#id_his").val();

	//给所有健康指标赋值
	$.ajax( {
		url : "${ctx}/join/diseaseCodehisallJS.action?id=" + id,
		dataType : 'json',
		async : false,
		success : function(data) {

			$.each(data, function(i, item) {
				if (item["Value"]) {
					$("<option></option>").val(item["Value"])
							.text(item["Text"]).appendTo($("#hisall"));
				} else {
					$("<option></option>").val(item["id"]).text(item["test"])
							.appendTo($("#his"));

				}
			});
		}
	});

	$("#his_name").val('根据名称首字母查找');
	$("#allhis").val('根据名称首字母查找');
	$("#Indicator").window("open");

}

//搜索数据
function search() {
	$('#dg').datagrid('load', {
		name : $("#search_name").val(),
		type : $("#search_type").val()

	});
}
//全部指标模糊查询
function allhissearch() {
	var name = $("#allhis").val();
	$.ajax( {
		url : '${ctx}/join/diseaseCodehisallsearchJS.action?name=' + name,
		dataType : 'json',
		success : function(data) {
			$("#hisall").empty();
			$.each(data, function(i, item) {
				$("<option></option>").val(item["Value"]).text(item["Test"])
						.appendTo($("#hisall"));
			});
		}
	});

}
//疾病关联指标模糊查询
function hissearch() {
	var id = $("#id_his").val();
	var name = $("#his_name").val();
	$.ajax( {
		url : '${ctx}/join/diseaseCodehissearchJS.action?name=' + name + '&id='
				+ id,
		dataType : 'json',
		success : function(data) {
			$("#his").empty();
			$.each(data, function(i, item) {
				$("<option></option>").val(item["Value"]).text(item["Test"])
						.appendTo($("#his"));
			});
		}
	});

}
//查询
function in_focus(name) {
	var a = document.getElementById(name);
	if (a.value == '根据名称首字母查找') {
		a.value = '';

	}
}
</script>
	</head>
	<body>
		<table id="dg" title="疾病代码管理" fit=true
			data-options="pageSize:10,pageList:[1,5,10],rownumbers:true,pagination:true,singleSelect:true,toolbar:'#tb'">
			<thead>
				<tr>
					<th data-options="field:'id',width:170,align:'center'">
						疾病编号
					</th>
					<th data-options="field:'name',width:170,align:'center'">
						疾病名称
					</th>
					<th data-options="field:'type',width:170,align:'center'">
						类别
					</th>
					<th data-options="field:'description',width:170,align:'center'">
						描述
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
							名称：
							<input class="inputs" id="search_name" style="width: 130px" />
							类别：
							<input class="inputs" id="search_type" style="width: 130px" />
							<a href="javascript:search()" class="easyui-linkbutton"
								iconCls="icon-search">搜索</a>
						</td>
					</tr>
				</table>
				<a id="add" href="#" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-add'">添加</a>
				<a id="showIndicator" href="javascript:showIndicator();"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-edit'">指标</a>
				<a id="edit" href="#" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-edit'">修改</a>
				<a id="del" href="#" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-remove'">删除</a>

			</div>
		</div>



		<!-- 查看健康指标 -->
		<div id="Indicator" class="easyui-window" closed=true modal=true
			style="width: 630px; height: 420px; padding: 10px; background-color: #E1ECFF; z-index: 3;"
			title="修改指标">
			<form id="hisEdit" method="post">
				<table width="100%">
					<tr>
						<td bgcolor="#ADD8E6">
							<h4>
								<p class="text-center">
									<span id="diseasename"></span>所包含健康指标
								</p>
							</h4>
						</td>
					</tr>
				</table>
				<br>
				<div style="width: 45%; float: left; border: 1px solid #ccc">
					<p>
						<label>
							现有指标：(点击可移除现有指标)
						</label>
					</p>
					<center>
						<input type="hidden" value="" id="id_his" />
						<input type="text" value="根据名称首字母查找" id="his_name"
							onchange="javascript:hissearch() "
							onfocus="javascript:in_focus('his_name')" />
						<select multiple="multiple" id="his"
							style="width: 140px; height: 150px;">

						</select>
					</center>
				</div>
				<div
					style="width: 10%; float: left; border: 1px solid #ccc; padding-top: 100px; height: 150px;">
					<input style="width: 50px;" id="removehis" value="&nbsp;&gt;"
						type="button" />
					<br />
					<input style="width: 50px;" id="remove_all" value="&gt;&gt;"
						type="button" />
					<br />
					<input style="width: 50px;" id="addhis" value="&nbsp;&lt;"
						type="button" />
					<br />
					<input style="width: 50px;" id="add_all" value="&lt;&lt;"
						type="button" />
				</div>
				<div style="width: 45%; float: left; border: 1px solid #ccc;">
					<p>
						<label>
							全部指标：(点击可添加至现有指标)
						</label>
					</p>
					<center>
						<input type="text" id="allhis" value="根据名称首字母查找"
							onchange="javascript:allhissearch()"
							onfocus="javascript:in_focus('allhis')">

						<select multiple="multiple" style="width: 140px; height: 150px;"
							id="hisall">

						</select>

					</center>
				</div>


				<div id="win-buttons" style="text-align: center; margin-top: 50px;">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-ok" id="savehis">保存</a>
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-cancel"
						onclick="javascript:$('#Indicator').window('close')">取消</a>
				</div>
			</form>


		</div>
		<div id="openWindow" class="easyui-window" closed=true modal=true
			style="width: 300px; height: 283px; padding: 10px;" title="添加用户">
			<form id="userEdit" method="post">
				<input name="id" type="hidden" />
				<table width="100%" cellpadding="0" cellspacing="0" align="center">
					<tr height="28px">
						<td>
							疾病名称：
						</td>
						<td>
							<input type="text" class="easyui-validatebox"
								data-options="required:true" style="width: 150px;" name="name" />
							<span id="nameState"
								style="height: 20px; color: red; position: relative; width: 20px;"></span>
						</td>
					</tr>
					<tr height="28px">
						<td>
							类别：
						</td>
						<td>
							<select class="easyui-combobox" data-options=""
								style="width: 150px;" name="type">
								<option value="外科">
									外科
								</option>
								<option value="内科">
									内科
								</option>
								<option value="五官科">
									五官科
								</option>
								<option value="眼科">
									眼科
								</option>
								<option value="妇科">
									妇科
								</option>
								<option value="口腔科">
									口腔科
								</option>
							</select>
						</td>
					</tr>
					<tr height="28px">
						<td>
							描述：
						</td>
						<td>
							<input type="text" class="easyui-validatebox" data-options=""
								style="width: 150px;" name="description" />
						</td>
					</tr>


				</table>
			</form>
			<div id="win-buttons" style="text-align: center; margin-top: 5px;">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-ok" id="save">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-cancel"
					onclick="javascript:$('#openWindow').window('close')">取消</a>
			</div>
		</div>

	</body>
</html>
