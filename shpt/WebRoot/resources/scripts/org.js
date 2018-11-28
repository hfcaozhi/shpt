(function($) {
	$.widget("ui.orgwin", {// //创建一个div标签
		createDiv : function(clazz) {
			var $div = $('<div></div>');
			if (clazz != null) {
				$div.addClass(clazz);
			}
			return $div;
		},// 创建一个ul标签
		createUl : function() {
			return $('<ul></ul>');
		},// 创建一个li标签
		createLi : function() {
			var $li = $('<li></li>');
			return $li;
		},// 创建子标签
		initChildUl : function(data) {
			var $ul = this.createUl();
			for ( var i = 0; i < data.length; i++) {
				$ul.append(this.initLi(data[i]));
			}
			return $ul;
		},
		initLi : function(data) {
			var $li = this.createLi();
			var $div = this.createDiv();
			$div.addClass('text_box');
			$div.html(data.text);
			$div.attr("id", data.id);
			$div.attr('parentId', data.parentId);
			$div.attr('description', data.description);
			$li.append($div);
			if (data.children.length > 0) {
				$li.append(this.initChildUl(data.children));
			}
			return $li;
		},// 初始化所有的控件
		_init : function() {
			$('#org').empty();
			$('#org').append(this.initLi(this.options));

		}
	});
})(jQuery);
$(function() {
	loadOrgData();
	var url;
	// 添加
	$('#add').click(function() {
		url = ctx + "/join/orgAddJS.action";
		clearText();
		$('#orgWindow').window({
			title : "添加结构组织"
		});
		$('#orgWindow').window('open');
	});
	// 修改
	$('#update').click(function() {
		url = ctx + "/join/orgUpdateJS.action";
		clearText();
		var div = $('div.check_text_box');
		var id = getId();
		$('#orgEdit').form('load', {
			id : id,
			name : div.html(),
			description : div.attr('description')
		});
		var parentId = div.attr('parentId');
		if(parentId==0){
			$('#orgParentId').combotree('disable');
			$('#orgParentId').combotree('setValue','');
		}else{
			$('#orgParentId').combotree('setValue',parentId);
		}
		$('#orgWindow').window({
			title : "修改结构组织"
		});
		$('#orgWindow').window('open');
	});
	$('#del').click(function() {
		var id = getId();
		var parentId = $('div.check_text_box').attr('parentId');
		if(parentId==0){
			$.messager.alert('系统提示', '该组织不能够被删除!', 'error');
			return;
		}
		$.messager.confirm('系统提示', '您确定要删除该条记录吗?', function(r) {
			if (r) {
				$.post(ctx + '/join/orgDelJS.action', {
					"id" : id
				}, function(msg) {
					$.messager.alert('提示', msg.message);
					loadOrgData();
				});
			}
		});
	});
	// 保存数据
	$('#save').click(function() {
		$('#orgEdit').form('submit', {
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
					loadOrgData();
					$('#orgWindow').window('close');
				}
			}
		});
	});
});
// 清空文本数据
function clearText() {
	$('input[name=name]').val('');
	$('textarea[name=description]').val('');
	$('#orgParentId').combotree('setValue', '1');
	$('#orgParentId').combotree('enable');
}
function getId() {
	var id = $('div.check_text_box').attr('id');
	if (id == null) {
		$.messager.alert('提示', "请您先选择");
		return;
	} else {
		return id;
	}
}
// 加载组织结构数据
function loadOrgData() {
	$.post(ctx + '/join/orgJS.action', function(data) {
		var data = $('<div></div>').orgwin(data);
		$('#chart').empty();
		$("#org").jOrgChart({
			chartElement : '#chart'
		});
		// 默认选择第一个
		$('div.text_box').first().addClass('check_text_box');
		$('div.text_box').click(function() {
			$('div.check_text_box').removeClass('check_text_box');
			$(this).addClass('check_text_box');
		});
	});

}