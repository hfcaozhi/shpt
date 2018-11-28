(function($) {
	$.widget("ui.mywindow", {// //创建一个div标签
		createDiv : function(clazz) {
			var $div = $('<div></div>');
			if (clazz != null) {
				$div.addClass(clazz);
			}
			return $div;
		},
		createDivN : function(clazz) {
			var $div = $('<div></div>');
			if (clazz != null) {
				$div.addClass(clazz);
			}
			return $div;
		},// 创建一个ul标签
		createUl : function() {
			return $('<ul></ul>');
		},// 创建一个li标签
		createLi : function(clazz) {
			var $li = $('<li></li>');
			if (clazz != null) {
				$li.addClass(clazz);
			}
			return $li;
		},// 创建一个span标签
		createSpan : function(clazz) {
			var $span = $('<span></span>');
			if (clazz != null) {
				$span.addClass(clazz);
			}
			return $span;
		},// 创建一个input标签
		createInput : function(type, state, name, id, value) {
			var $input = $('<input/>');
			$input.attr('type', type);
			return $input;
		},//
		initFolder : function(title1, title2) {
			var $bgFolderDiv = this.createDiv('zm_folder_title');
			var $folderDivA = this.createDiv('opreate_a');
			$folderDivA.html(title1);
			var $folderDivB = this.createDiv('opreate_b');
			$folderDivB.html(title2);
			//
			$bgFolderDiv.append($folderDivA);
			$bgFolderDiv.append($folderDivB);
			return $bgFolderDiv;
		},// 标题的创建
		initTitle : function() {
			var titles = this.options.ops;
			// 创建
			var $li = this.createLi('zm_menu zm_title');
			var $titleBg = this.createDiv('zm_content_color');
			var $span = this.createSpan(null);
			$span.html('&nbsp;&nbsp;');
			var $titleSpan = this.createSpan(null);
			$titleSpan.html(this.options.ops.name);
			var $folderDiv = this.createDiv('zm_folder_title');
			var $opreateDivA = this.createDiv('opreate_a opreate_content');
			$opreateDivA.html(titles.folderA);
			var $opreateDivB = this.createDiv('opreate_b opreate_content');
			$opreateDivB.html(titles.folderB);

			// 追加
			$folderDiv.append($opreateDivA);
			$folderDiv.append($opreateDivB);
			$titleBg.append($titleSpan);
			$titleBg.append($folderDiv);
			$li.append($titleBg);
			$li.append($span);
			return $li;
		},// 内容的创建
		initContent : function(parent, length, num) {
			var child = parent.children;
			var $menuLi = this.createLi('zm_menu');
			var $menuBgDiv = this.createDiv('zm_background');
			if (parent.menuId != null) {
				$menuBgDiv.attr('menuid', parent.menuId);
			}
			if(parent.isDel!=null){
				$menuBgDiv.attr('isdel', parent.isDel);
			}
			if (parent.codeId != null) {
				$menuBgDiv.attr('codeid', parent.codeId);
			}
			if (parent.parentId != null) {
				$menuBgDiv.attr('parentid', parent.parentId);
			}
			if (parent.resourceId != null) {
				$menuBgDiv.attr('resourceid', parent.resourceId);
			}
			var $bgIconSpan = this.createSpan('zm_icon zm_move_b');
			if (child.length == 0) {
				$bgIconSpan = this.createSpan('zm_icon zm_move_i');
			}
			if (num == length - 1 && child.length > 0) {
				$bgIconSpan = this.createSpan('zm_icon zm_move_c');
			}
			if (num == length - 1 && child.length == 0) {
				$bgIconSpan = this.createSpan('zm_icon zm_move_j');
			}
			var $bgContentSapn = this.createSpan('name');
			$bgContentSapn.html(parent.name);

			var $menuSpan = this.createSpan(null);
			$menuSpan.html('&nbsp;&nbsp;');

			// 追加
			$menuBgDiv.append($bgIconSpan);
			$menuBgDiv.append($bgContentSapn);
			$menuBgDiv.append(this.initFolder(parent.folderA, parent.folderB));
			$menuLi.append($menuBgDiv);
			$menuLi.append($menuSpan);
			if (child.length > 0) {
				var $menuUl = this.createUl();
				for ( var k = 0; k < child.length; k++) {
					$menuUl.append(this.initChild(child[k], child.length, k));
				}
				$menuLi.append($menuUl);
			}
			return $menuLi;
		},// 一级子节点
		initChild : function(child, parentLength, num) {
			var tow = child.children;
			var $li = this.createLi(null);
			var $bgDiv = this.createDivN('zm_background');
			if (child.menuId != null) {
				$bgDiv.attr('menuid', child.menuId);
			}
			if (child.codeId != null) {
				$bgDiv.attr('codeid', child.codeId);
			}
			if (child.parentId != null) {
				$bgDiv.attr('parentid', child.parentId);
			}
			if(parent.isDel!=null){
				$bgDiv.attr('isdel', child.isDel);
			}
			if (child.resourceId != null) {
				$bgDiv.attr('resourceid', child.resourceId);
			}
			var $bgIconSpanA = this.createSpan('zm_icon zm_move_h');
			var $bgIconSpanB = this.createSpan('zm_icon zm_move_b');

			if (tow.length == 0) {
				$bgIconSpanB = this.createSpan('zm_icon zm_move_i');
			}
			if (num == parentLength - 1 && tow.length > 0) {
				$bgIconSpanB = this.createSpan('zm_icon zm_move_c');
			}
			if (num == parentLength - 1 && tow.length == 0) {
				$bgIconSpanB = this.createSpan('zm_icon zm_move_j');
			}
			var $bgTitleSpan = this.createSpan('name');
			$bgTitleSpan.html(child.name);
			var $span = this.createSpan(null);
			$span.html('&nbsp;&nbsp;');
			// 追加
			$bgDiv.append($bgIconSpanA);
			$bgDiv.append($bgIconSpanB);
			$bgDiv.append($bgTitleSpan);
			$bgDiv.append(this.initFolder(child.folderA, child.folderB));
			$li.append($bgDiv);
			$li.append($span);

			if (tow != null && tow.length > 0) {
				var $ul = this.createUl();
				for ( var v = 0; v < tow.length; v++) {
					$ul.append(this.initChildTow(child.children[v], tow.length,
							v));
				}
				$li.append($ul);
			}
			return $li;

		},// 二级子节点
		initChildTow : function(child, parentLength, num) {
			var $li = this.createLi('zm_opreate_item zm_move_k');
			var $bgDiv = this.createDiv('zm_background  zm_background_a');
			if (child.menuId != null) {
				$bgDiv.attr('menuid', child.menuId);
			}
			if (child.codeId != null) {
				$bgDiv.attr('codeid', child.codeId);
			}
			if (child.parentId != null) {
				$bgDiv.attr('parentid', child.parentId);
			}
			if(child.isDel!=null){
				$bgDiv.attr('isdel', parent.isDel);
			}
			if (child.resourceId != null) {
				$bgDiv.attr('resourceid', child.resourceId);
			}
			var $bgIconSpanA = this.createSpan('zm_icon zm_move_h');
			var $bgIconSpanB = this.createSpan('zm_icon zm_move_i');
			if (num == parentLength - 1) {
				$bgIconSpanB = this.createSpan('zm_icon zm_move_j');
			}
			var $bgTitleSpan = this.createSpan('name');
			$bgTitleSpan.html(child.name);

			// 追加
			$bgDiv.append($bgIconSpanA);
			$bgDiv.append($bgIconSpanB);
			$bgDiv.append($bgTitleSpan);
			$bgDiv.append(this.initFolder(child.folderA, child.folderB));
			$li.append($bgDiv);
			return $li;
		},// 初始化所有的控件
		_init : function() {
			var root = this.options.root;
			var accrodion = $(root).find('div.zm_accordion');
			if (accrodion.attr('class') != null) {
				accrodion.empty();
				accrodion.remove();
			}
			this.win = this.createDiv('zm_accordion zm_accorder_border');
			this.ul = this.createUl();
			this.ul.append(this.initTitle());
			var parent = this.options.ops.children;
			var parentLength = parent.length;
			if (parentLength > 0) {
				for ( var i = 0; i < parentLength; i++) {
					this.ul
							.append(this
									.initContent(parent[i], parentLength, i));
				}
			}
			this.win.append(this.ul);
			//追加到要插入的节点里面
			$(root).append(this.win);
			var $menus = $('div.zm_background span');
			// 鼠标点击时移动
			$menus.click(function() {
				if ($(this).parent().next('span').next('ul').is(':visible')) {
					$(this).parent().next('span').next('ul').slideUp(100);
					$(this).removeClass('zm_move_f');
					return;
				}
				$(this).parent().next('span').next('ul').slideDown(100);
				$(this).addClass('zm_move_f');

			});
			//悬浮样式改变事件
			var $bg = $('div.zm_background');
			$bg.mouseover(function() {
				$(this).addClass('zm_background_over');
			});
			$bg.mouseout(function() {
				$(this).removeClass('zm_background_over');
			});
			//点击样式改变事件
			var $bg = $('div.zm_background');
			$bg.click(function() {
				$bg.removeClass('zm_background_click');
				$(this).addClass('zm_background_click');
			});
			//标题悬浮背景样式改变事件
			var $opreateContent = $('div.opreate_content');
			$opreateContent.mouseover(function() {
				$(this).addClass('zm_background_over');
			});
			$opreateContent.mouseout(function() {
				$(this).removeClass('zm_background_over');
			});
			//默认选择第一行
			$bg.first().addClass('zm_background_click');
		}
	});
})(jQuery);
//加载数据
function loadTree(url) {
	$.ajax({
		url : url,
		dataType : 'json',
		success : function(msg) {
			$('<div></div>').mywindow(msg);
		}
	});
}
//添加和修改操作
function post(url, data, path, close) {
	$.post(url, data, function(msg) {
		$.messager.alert('提示', msg, 'info');
		$(close).window('close');
		loadTree(path);
	}, 'text');
}
