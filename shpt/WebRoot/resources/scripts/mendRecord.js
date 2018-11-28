$(function(){
	var url;
	$('#addMendRecord').click(function(){
		$('#mr_commit').form("clear")
		$('#add_mend_record_car_id').val(carId);
		url = ctx+"/join/mendRecordAddJS.action";
		$("#mendRecordWindow").window({title:"添加执法车维修信息"});
		$("#mendRecordWindow").window("open");
	});
	$('#editMendRecord').click(function(){
		$('#mr_commit').form("clear");
		$('#add_mend_record_car_id').val(carId);
		var item = $('#dg').datagrid('getSelected');
		if (item) {
			$('#mr_commit').form('load', {
				id:item.id,
				mendUser:item.mendUser,
				cost:item.cost,
				discription:item.description,
				createTime:item.createTime
			});
			$("#mendRecordWindow").window({title:"修改执法车维修信息"});
			url = ctx+"/join/mendRecordUpdateJS.action";
			$("#mendRecordWindow").window("open");
		} else {
			alert("请先选中一行再操作");
		}
		
	});
	$('#delMendRecord').click(function(){
		$.messager.confirm('系统提示', '您确定要删除该条记录吗?', function(r) {
			if (r) {
				$.post(ctx+'/join/mendRecordDelJS.action',{'id':$('#dg').datagrid('getSelected').id}, function(
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
	//加载执法车维修数据
	$("#dg").datagrid({
		url:ctx+'/join/mendRecordJS.action?carId=' + carId,
		onLoadSuccess:function(data){$("#dg").datagrid("selectRow",0);}
	});
	//加载执法车信息
	$.post(ctx+'/join/carJS.action?id=' + carId, function(
			msg) {
		if(!msg){
			$.messager.alert('提示','加载车辆信息失败'); 
		}else{
			carNo = msg.carNo;
			$("#car_no").html(msg.carNo);
			$("#mId").html(msg.id);
			$('#mCarNo').html(msg.carNo);
			$('#mOrg').html(msg.orgName);
		}
		
	});
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
					$('#mendRecordWindow').window('close');
					$('#dg').datagrid('reload');
				}
			}
		});
	});
});
function search(){
	$('#dg').datagrid('load', {  
	    name: $("#search_name").val(),
	    createTime:$("#search_createTime").combotree("getValue")
	}); 
	
}
