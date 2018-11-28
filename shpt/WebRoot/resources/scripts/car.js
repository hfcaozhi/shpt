function newRefuel(number) {
	$("#frm_refuel").form("clear");
	var item = $('#dg').datagrid('getSelected');
	$("#add_refuel_car_no").val(item.carNo);
	$("#add_refuel_carId").val(item.id);
	
	$("#addRefuelWindow").window("open");
}
$(function() {
	var url;
	$('#add').click(function() {
		$("#carInfo").form("clear");
		checkBox();
		url=ctx+"/join/carAddJS.action";
		$('#carInfoWindow').window({title:"添加执法车信息"});
		$('#carInfoWindow').window('open');
	});
	$('#edit').click(function() {
		$("#carInfo").form("clear");
		checkBox();
		var item = $('#dg').datagrid('getSelected');
		if (item) {
			url = ctx+"/join/carUpdateJS.action";
			$('#carInfo').form('load', {
				id : item.id,
				oid:item.org,
				carType:item.carType,
				fuelCardNo:item.fuelCardNo,
				carNo:item.carNo,
				gpsNo:item.gpsNo,
				desc:item.desc,
				innerFcardNo:item.innerFcardNo,
				thirdNo:item.thirdNo
			});
			$('#carInfoWindow').window({title:"修改执法车信息"});
			$('#carInfoWindow').window('open');
		} else {
			alert("请先选中一行再操作");
		}
		
	});
	//保存执法车信息
	$('#saveCarInfo').click(function(){
		var carNo = $('#carNo').val();
		var fuelCardNo = $('#fuelCardNo').val();
		if (carNo == '') {
			$.messager.alert('系统提示', '请输入车牌号！', 'warning');
			return;
		}
		if(fuelCardNo == ''){
			$.messager.alert('系统提示', '请输入车油卡号！', 'warning');
			return;
		}
		$('#carInfo').form('submit', {
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
					$('#carInfoWindow').window('close');
					$('#dg').datagrid('reload');
				}
			}
		});
	});
	$('#del').click(function() {
		$.messager.confirm('系统提示', '您确定要报废该条记录吗?', function(r) {

			if (r) {
				$.post(ctx+'/join/carDelJS.action?id=' + $('#dg').datagrid('getSelected').id, function(
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
	//年检
//	$('#aj').click(function() {
//		$.messager.confirm('系统提示', '您确定要提醒年检吗?', function(r) {
//
//			if (r) {
//				$.post(ctx+'/join/carUpdateStatusJS.action?id=' + $('#dg').datagrid('getSelected').id, function(
//						msg) {//{"message":"","status":0,"page":null}
//					rolePrivilegeTree = msg;	
//					if(msg.status!=0){
//						$.messager.alert('提示',msg.message); 
//					}else{
//						$.messager.alert('提示','已经提醒'); 
//						$('#dg').datagrid('reload');
//					}
//					
//					 
//				}); 
//			}
//		});
//	});
	//提交维修记录
	$('#saveMendRecord').click(function(){
		var cost = $('#mendRecordCost').val();
		var createTime = $('#mendRecordCreateTime').datebox('getValue');
		var mendUser = $('#mendUser').val();
		if(mendUser == ''){
			$.messager.alert('系统提示', '请输入维修人员！', 'warning');
			return;
		}
		if(createTime==''){
			$.messager.alert('系统提示', '请输入维修时间！', 'warning');
			return;
		}
		if(cost==''){
			$.messager.alert('系统提示', '请输入维修金额！', 'warning');
			return;
		}
		$('#mr_commit').form('submit', {
			url : ctx+"/join/mendRecordAddJS.action",
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
					$('#mendRecordWindow').window('close');
					$('#dg').datagrid('reload');
				}
			}
		});
	});
	
	//提交加油信息
	$("#frm_refuel").form({
		url: ctx+"/join/fuelRecordAddJS.action",
        onSubmit: function(){
        	return true;
        },
        success:function(data){
                //{"message":"","status":0,"page":null}
        	var msg = eval('(' + data + ')'); 
        	if(data.status==0){
        		//$.messager.alert('提示',msg.message);
        		$("#addRefuelWindow").window("close");
        		$('#dg').datagrid('reload');
        	}else{
        		$.messager.alert('提示',msg.message);
        	}
        }
	});
	
	//添加加油信息
	$("#btnFuelConfirm").click(function () {
		console.log("add fuel record ...");
		//检验字段
		var $name = $('#add_refuel_name');

		var $cost = $('#add_refuel_cost');

		var $createtime = $('#add_refuel_createtime');
		
		if ($name.val() == '') {
			$.messager.alert('系统提示', '请输入人员姓名！', 'warning');
			return false;
		}
		
		if ($cost.val() == '') {
			$.messager.alert('系统提示', '请输入加油金额！', 'warning');
			return false;
		}
		//var v = $('#dd').datebox('getValue');	
		if ($createtime.datebox('getValue') == '') {
			$.messager.alert('系统提示', '请输入加油日期！', 'warning');
			return false;
		}
		
		$('#frm_refuel').submit(); 
	});
});


//初始化
$(document).ready(function(){
	//加载执法车数据
	$("#dg").datagrid({
//		url:ctx+'/join/carJS.action?carId='+carId,
		url:ctx+'/join/carJS.action',
		onLoadSuccess:function(data){$("#dg").datagrid("selectRow",0);}
	});
});

function openMendRecord(){
	$('#mr_commit').form("clear");
	url = ctx+"/join/mendRecordAddJS.action";
	var item = $('#dg').datagrid('getSelected');
	$("#add_mend_record_car_id").val(item.id);
	$("#add_mend_record_car_no").val(item.carNo);
	$("#mendRecordWindow").window("open");
}
//搜索数据
function search(){
	var select_org = $("#search_orgid").datebox("getValue");
	if(isNaN(select_org))select_org=-1;
	$('#dg').datagrid('load', {  
		carNo:$("#search_carNo").val(),
		fuelCardNo:$("#search_card_no").val(),
		oid:select_org
	}); 
	
}
//清空文本
function checkBox(){
	$('#oid').combotree('setValue', 1);
	$('#carType').combobox('setValue', '小型车');
}
//安检
//function check(){
//	url:ctx+'/join/carUpdateStatusJS.action?id=' + $('#dg').datagrid('getSelected').id;
//}
