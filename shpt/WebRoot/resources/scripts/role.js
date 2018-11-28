/* 修改还是新建的url*/
	 var url = "/ospm/loginInfo/doGetPrivilegeTree.action";
	 var url_privilege="/join/";
	 var rolePrivilegeTree;
	 //保存权限编号的数组
	 var result = new Array;

	$(function(){
		var $menus = $('div.zm_accordion ul li span');
		//鼠标点击时移动
		$menus.click(function(){
			 if($(this).next('div').next('ul').is(':visible')){
				$(this).removeClass('zm_move_f');
				$(this).next('div').next('ul').slideUp(500);
				return;
			}
			$(this).addClass('zm_move_f');
			$(this).next('div').next('ul').slideDown(500); 
		});
		$('#foot').click(function(){
			if($('#treeChild').is(':hidden')){
				$(this).addClass('zm_move_d');
				$('#treeChild').slideDown(500);
				return;
			}
			$(this).removeClass('zm_move_d');
			$('#treeChild').slideUp(500);
		});
		$('#add').click(function(){
			var item = $('#dg').datagrid('getSelected');
			if(item){ 
				url = ctx+"/join/roleAddJS.action";
				$('#editWindow').window({title:"添加角色"});
				$('#editWindow').window('open');
				getPrivilegeTree();
				
			}else{
				alert("请先选中一行再操作");
			}
		});
		$('#edit').click(function(){
			
			var item = $('#dg').datagrid('getSelected');
			if(item){
				url = ctx+"/join/roleUpdateJS.action";
				$('#editWindow').window({title:"修改角色"});
				$('#editWindow').window('open');
				$('#fedit').form('load',{
					id:item.id,
					name:item.name,
					description:item.description,
					status:item.status
				});
				getPrivilegeTree();
			}else{
				alert("请先选中一行再操作");
			}
			
		});
		$('#del').click(function(){
			
			$.messager.confirm('系统提示', '您确定要删除该条记录吗?', function(r) {

				if (r) {
					$.post(ctx+'/join/roleDelJS.action?id=' + $('#dg').datagrid('getSelected').id, function(
							msg) {//{"message":"","status":0,"page":null}
						
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
		$('#show').click(function(){
			$('#showWindow').window('open');
		});

		//加载完数据后，默认选中第一行
		$("#dg").datagrid({
			url:ctx+'/join/roleJS.action',
			onLoadSuccess:function(data){$("#dg").datagrid("selectRow",0);}
		});

	});

	function saveRole(){
		
		$('#fedit').form('submit',{  
	        url: url,  
	        onSubmit: function(){  
	            return $(this).form('validate');  
	        },  
	        success: function(result){  
	            var result = eval('('+result+')');  
	            if (result.errorMsg){  
	                $.messager.show({  
	                    title: 'Error',  
	                    msg: result.errorMsg  
	                });  
	            } else {  
	                $('#editWindow').window('close');      // close the dialog  
	                $('#dg').datagrid('reload');    // reload the user data  
	            }  
	        }  
	    });  
	}

	//动态加载数据
	function showResource(){
		$('#dg').datagrid('load', {  
		    name: $("#dg").datagrid("getSelected").name
		}); 
		$('#dg').datagrid('reload'); 
	}


	//搜索数据
	function search(){
		
		$('#dg').datagrid('load', {  
		    name: $("#search_name").val()
		}); 
		
	}
	//加载权限树
	function getPrivilegeTree(){
		
		$.post(ctx+'/join/resourceJS.action', function(
				msg) {
			//生成权限树
			console.log("privilege tree is "+msg);
			$('<div></div>').win(msg);
//			var obj = {"root":"div.zm_body","ops":[{"id":3,"name":"首页面","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":0},{"id":36,"name":"系统管理","children":[{"id":33,"name":"修改密码","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":0},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":1},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":2},{"id":37,"name":"权限管理","children":[{"id":19,"name":"权限首页","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":0},{"id":20,"name":"权限数据","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":0},{"id":24,"name":"添加权限","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":0},{"title":"add","name":"新增","checked":0,"disabled":1},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":1},{"id":25,"name":"修改权限","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":0},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":1},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":2},{"id":26,"name":"删除权限","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":0},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":1},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":3},{"id":35,"name":"权限树","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":0}],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":1},{"title":"update","name":"修改","checked":0,"disabled":1},{"title":"delete","name":"删除","checked":0,"disabled":1},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":-1},{"id":38,"name":"角色管理","children":[{"id":14,"name":"角色首页","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":0},{"id":15,"name":"角色数据","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":0},{"id":27,"name":"添加角色","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":0},{"title":"add","name":"新增","checked":0,"disabled":1},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":1},{"id":28,"name":"修改角色","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":0},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":1},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":2},{"id":29,"name":"删除角色","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":0},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":1},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":3}],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":1},{"title":"update","name":"修改","checked":0,"disabled":1},{"title":"delete","name":"删除","checked":0,"disabled":1},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":-1},{"id":39,"name":"用户管理","children":[{"id":17,"name":"用户首页","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":0},{"id":18,"name":"用户数据","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":0},{"id":30,"name":"添加用户","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":0},{"title":"add","name":"新增","checked":0,"disabled":1},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":1},{"id":31,"name":"修改用户","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":0},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":1},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":2},{"id":32,"name":"删除用户","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":0},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":1},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":3}],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":1},{"title":"update","name":"修改","checked":0,"disabled":1},{"title":"delete","name":"删除","checked":0,"disabled":1},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":-1},{"id":40,"name":"菜单管理","children":[{"id":4,"name":"菜单数据","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":0},{"id":16,"name":"菜单首页","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":0},{"id":21,"name":"添加菜单","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":0},{"title":"add","name":"新增","checked":0,"disabled":1},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":1},{"id":22,"name":"修改菜单","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":0},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":1},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":2},{"id":23,"name":"删除菜单","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":0},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":1},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":3}],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":1},{"title":"update","name":"修改","checked":0,"disabled":1},{"title":"delete","name":"删除","checked":0,"disabled":1},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":-1}],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":1},{"title":"update","name":"修改","checked":0,"disabled":1},{"title":"delete","name":"删除","checked":0,"disabled":1},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":-1},{"id":41,"name":"城管内容管理","children":[{"id":42,"name":"全球眼管理","children":[{"id":8,"name":"全球眼数据","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":0},{"id":43,"name":"添加全球眼","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":0},{"title":"add","name":"新增","checked":0,"disabled":1},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":1},{"id":44,"name":"修改全球眼","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":0},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":1},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":2},{"id":45,"name":"删除全球眼","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":0},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":1},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":3}],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":1},{"title":"update","name":"修改","checked":0,"disabled":1},{"title":"delete","name":"删除","checked":0,"disabled":1},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":-1},{"id":49,"name":"事件管理","children":[{"id":6,"name":"事件首页","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":0},{"id":7,"name":"事件数据","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":0},{"id":50,"name":"添加事件","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":0},{"title":"add","name":"新增","checked":0,"disabled":1},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":1},{"id":51,"name":"删除事件","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":0},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":1},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":3},{"id":52,"name":"修改事件","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":0},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":1},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":2}],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":1},{"title":"update","name":"修改","checked":0,"disabled":1},{"title":"delete","name":"删除","checked":0,"disabled":1},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":-1},{"id":53,"name":"人员管理","children":[{"id":2,"name":"人员数据","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":0},{"id":9,"name":"人员首页","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":0},{"id":46,"name":"添加人员","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":0},{"title":"add","name":"新增","checked":0,"disabled":1},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":1},{"id":47,"name":"修改人员","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":0},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":1},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":2},{"id":48,"name":"删除人员","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":0},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":1},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":3}],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":1},{"title":"update","name":"修改","checked":0,"disabled":1},{"title":"delete","name":"删除","checked":0,"disabled":1},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":-1},{"id":54,"name":"被外罚人管理","children":[{"id":10,"name":"被处罚人首页","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":0},{"id":11,"name":"被处罚人数据","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":0},{"id":55,"name":"添加被处罚人","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":0},{"title":"add","name":"新增","checked":0,"disabled":1},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":1},{"id":56,"name":"修改被处罚人","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":0},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":1},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":2},{"id":57,"name":"删除被处罚人","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":0},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":1},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":3}],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":1},{"title":"update","name":"修改","checked":0,"disabled":1},{"title":"delete","name":"删除","checked":0,"disabled":1},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":-1},{"id":58,"name":"组织结构管理","children":[{"id":12,"name":"组织结构首页","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":0},{"id":13,"name":"组织结构数据","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":0},{"id":59,"name":"添加组织结构","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":0},{"title":"add","name":"新增","checked":0,"disabled":1},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":1},{"id":60,"name":"修改组织结构","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":0},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":1},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":2},{"id":61,"name":"删除组织结构","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":0},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":1},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":3}],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":1},{"title":"update","name":"修改","checked":0,"disabled":1},{"title":"delete","name":"删除","checked":0,"disabled":1},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":-1},{"id":62,"name":"执法车管理","children":[{"id":5,"name":"执法车数据","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":0},{"id":63,"name":"添加执法车","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":0},{"title":"add","name":"新增","checked":0,"disabled":1},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":1},{"id":64,"name":"修改执法车","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":0},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":1},{"title":"delete","name":"删除","checked":0,"disabled":0},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":2},{"id":65,"name":"删除执法车","children":[],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":0},{"title":"add","name":"新增","checked":0,"disabled":0},{"title":"update","name":"修改","checked":0,"disabled":0},{"title":"delete","name":"删除","checked":0,"disabled":1},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":3}],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":1},{"title":"update","name":"修改","checked":0,"disabled":1},{"title":"delete","name":"删除","checked":0,"disabled":1},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":-1}],"opreate":[{"title":"read","name":"读取","checked":0,"disabled":1},{"title":"add","name":"新增","checked":0,"disabled":1},{"title":"update","name":"修改","checked":0,"disabled":1},{"title":"delete","name":"删除","checked":0,"disabled":1},{"title":"all","name":"全部","checked":0,"disabled":1}],"type":-1}]};
//			$('<div></div>').win(obj);
			$('input[type=checkbox]').click(function(){
				choose($(this));	
			 
		})});	
	}
	//当用户选中或取消某个权限时，更新权限列表.权限列表用于提交给后台，保存数据库
	function updatePrivilege(rid){
		updatePrivilege(rolePrivilegeTree.ops,rid);
		
	}
	/**
	 * 
	 * @param items ResourceTreeItem  id name type opreate<Operate> children<ResourceTreeItem>
	 * @param rid 资源编号
	 * @param checked 状态 0 未选中 1选中
	 * @param type 类型：0读取、1新增、2修改、3删除、4全部
	 */
	function execUpdateP(items,rid,checked,type){
		
		for(var i=0;i<items.length;i++){
			var it = items[i];
			if(it.id==rid){//找到了该编号
				if(it.type==0){//查看
					it.opreate[0].checked=checked;
				}else if(it.type==1){//添加
					it.opreate[1].checked=checked;
				}else if(it.type==2){//修改
					it.opreate[2].checked=checked;
				}else if(it.type==3){//删除
					it.opreate[3].checked=checked;
				}else if(it.type==-1){//非叶结点
					it.opreate[3].checked=checked;
				}
			}else{
				execUpdateP(it.children);
			}
			
		}
	}
	