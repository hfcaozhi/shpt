var carNo;
var url;
$(function(){
	$('#addFuel').click(function(){
		$('#frm_refuel').form("clear");
		byCheck();
		url = ctx+"/join/fuelRecordAddJS.action";
		$('#addRefuelWindow').window({title:'添加执法车加油信息'});
		$('#addRefuelWindow').window('open');
	});
	$('#editFuel').click(function(){
		$('#frm_refuel').form("clear");
		url = ctx+"/join/fuelRecordUpdateJS.action";
		byCheck();
		var item = $('#dg').datagrid('getSelected');
		if (item) {
			$('#frm_refuel').form('load', {
				name:item.name,
				fuelCardInner:item.fuelCardInner,
				cardType:item.cardType,
				fuelCardNo:item.fuelCardNo,
				createTime:item.createTime,
				mileage:item.mileage,
				l:item.l,
				cost:item.cost,
				memo:item.memo,
				id:item.id,
				cardValue:item.cardValue
			});
			$('#addRefuelWindow').window({title:'修改执法车加油信息'});
			$('#addRefuelWindow').window('open');
		} else {
			alert("请先选中一行再操作");
		}
	});
	$('#delFuel').click(function(){
		$.messager.confirm('系统提示', '您确定要删除该条记录吗?', function(r) {
			if (r) {
				$.post(ctx+'/join/fuelRecordDelJS.action',{'id':$('#dg').datagrid('getSelected').id}, function(
						msg) {//{"message":"","status":0,"page":null}
					rolePrivilegeTree = msg;	
					if(msg.status!=0){
						$.messager.alert('提示',msg.message); 
					}else{
						$.messager.alert('提示','删除成功'); 
						$('#dg').datagrid('reload');
					}
				});
			}
		});
	});
	//加载执法车数据
	$("#dg").datagrid({
		url:ctx+'/join/mendRecordJS.action?carId=' + carId,
		onLoadSuccess:function(data){$("#dg").datagrid("selectRow",0);}
	});
	$.post(ctx+'/join/carJS.action?id=' + carId, function(
			msg) {
		if(!msg){
			$.messager.alert('提示','加载车辆信息失败'); 
		}else{
		}
		
	});
	//提交加油信息
	$("#btnFuelConfirm").click(function () {
		console.log("add fuel record ...");
		//检验字段
		var $name = $('#add_refuel_name');

		var $cost = $('#add_refuel_cost');

		var $createtime = $('#add_refuel_createtime');
		
		if ($name.val() == '') {
			$.messager.alert('系统提示', '请输入人员姓名！', 'warning');
			return;
		}
		
		if ($cost.val() == '') {
			$.messager.alert('系统提示', '请输入加油金额！', 'warning');
			return;
		}
		if ($createtime.datebox('getValue') == '') {
			$.messager.alert('系统提示', '请输入加油日期！', 'warning');
			return;
		}
		$('#frm_refuel').form('submit', {
			url : url,
			onSubmit : function() {
				return true;
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
					$('#addRefuelWindow').window('close');
					$('#dg').datagrid('reload');
				}
			}
		});
	});
	

});



//搜索数据
function search(){
	
	$('#dg').datagrid('load', {  
	    name: $("#search_name").val(),
	    createTime:$("#search_createTime").combotree("getValue")
	}); 
	
}
function byCheck(){
	$('input[name=carNo]').val(carNo);
	$('#add_refuel_carId').val(carId);
}
