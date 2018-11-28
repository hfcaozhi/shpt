(function($) {
				$.widget("ui.win",{////创建一个div标签
					createDiv : function(clazz){
						var $div = $('<div></div>');
						if (clazz != null) {
							$div.addClass(clazz);
						}
						return $div;
					},//创建一个ul标签
					createUl:function(){
						return $('<ul></ul>');
					},//创建一个li标签
					createLi:function(clazz){
						var $li = $('<li></li>');
						if(clazz!=null){
							$li.addClass(clazz);
						}
						return $li;
					},//创建一个span标签
					createSpan:function(clazz){
						var $span = $('<span></span>');
						if (clazz != null) {
							$span.addClass(clazz);
						}
						return $span;
					},//创建一个input标签
					createInput:function(type,state,name,id,value){
						var $input = $('<input/>');
						$input.attr('type',type);
						return $input;
					},//初始化opreate操作模块
					initOpreate:function(state,parentid,opreateid,part,parentPart){
						var $opreate = this.createDiv('zm_opreate');
						var obj;
						if(state.lenght!=0){
							for(var i=0;i<state.length;i++){
							obj = state[i];
							var $opreateDiv = this.createDiv(null);
							var $input = this.createInput('checkbox');
							
							if(obj.disabled!=null&&obj.disabled>0){
								$input.attr('enable','enable');
							}else{
								$input.attr('disabled','disabled');
							}
							if(obj.checked!=null && obj.checked==1){
								$input.attr('checked','checked');
							}else{
								$input.attr('checked',false);
							}
							if(obj.name!=null){
								$input.attr('name',obj.name);
							}
							if(obj.id!=null){
								$input.attr('id',obj.id);
							}
							if(obj.value!=null){
								$input.attr('value',obj.value);
							}
							if(opreateid!=null){
								if(obj.title=='all'){
								 $input.attr('opreateAll',opreateid);
								}else{
									$input.attr('opreate',opreateid);
								}
							}
							if(part!=null){
								if(obj.title!=null){
									if(obj.title=='read'){
									   $input.attr('part','read'+part);
									}
									if(obj.title=='add'){
										$input.attr('part','add'+part);
									}
									if(obj.title=='update'){
										$input.attr('part','update'+part);
									}
									if(obj.title=='delete'){
										$input.attr('part','delete'+part);
									}
								}
								
							}
							if(parentPart!=null){
								if(obj.title!=null){
									if(obj.title=='read'){
									$input.attr('parentPart','read'+parentPart);
									}
									if(obj.title=='add'){
										$input.attr('parentPart','add'+parentPart);
									}
									if(obj.title=='update'){
										$input.attr('parentPart','update'+parentPart);
									}
									if(obj.title=='delete'){
										$input.attr('parentPart','delete'+parentPart);
									}
								}
								
							}
							if(parentid!=null){
								$input.attr('parent',parentid);
							}
							$opreateDiv.append($input);
				        	$opreate.append($opreateDiv);
						  }
						}
						return $opreate;
					},//头部的创建
					initHead:function(){
						this.win = this.createDiv('zm_root');
						this.headpic = this.createDiv('zm_root_pic zm_move_d');
						this.headpic.click(function(){
							if($('div.zm_accordion').is(':hidden')){
								$(this).addClass('zm_move_d');
								$('div.zm_accordion').slideDown(500);
								return;
							}
							$(this).removeClass('zm_move_d');
							$('div.zm_accordion').slideUp(500);
						});
						this.head = this.createDiv('zm_root_opreate');
						this.headSelect = this.createDiv(null);
						this.headAdd = this.createDiv(null);
						this.headUpdate = this.createDiv(null);
						this.headDel = this.createDiv(null);
						this.headAll = this.createDiv(null);
						this.span = this.createSpan(null);
						//头部标题
						this.span.html('根模块');
						//给head添加字节点
						this.headSelect.html('读取');
						this.head.append(this.headSelect);
						this.headAdd.html('新增');
						this.head.append(this.headAdd);
						this.headUpdate.html('修改');
						this.head.append(this.headUpdate);
						this.headDel.html('删除');
						this.head.append(this.headDel);
						this.headAll.html('全部');
						this.head.append(this.headAll);
						//给win添加子节点
						this.win.append(this.headpic);
						this.win.append(this.span);
						this.win.append(this.head);
						
					},//内容的创建
					initContent:function(){
						//中间部分
						this.accordion = this.createDiv('zm_accordion');
						this.menuul = this.createUl();
						var parentLength =this.options.ops.length;
						var parent;
						var child;
						//父级
						for(var i=0;i<parentLength;i++){
							parent = this.options.ops[i];
							var childLength = parent.children.length;
							
							this.menuli1=this.createLi('zm_menu');
							
							this.menuSpan1 = this.createSpan('zm_icon zm_move_b');
							
							if(i==parentLength-1){
								this.menuSpan1 = this.createSpan('zm_icon zm_move_c');
							}
							if(i==parentLength-1&&childLength==0){
								this.menuSpan1 = this.createSpan('zm_icon zm_move_j')
							}
							if(childLength==0){
								this.menuSpan1 = this.createSpan('zm_icon zm_move_i')
							}
							if(childLength>0){
								this.menuSpan1.click(function(){
									if($(this).next('span').next('div').next('ul').is(':visible')){
									$(this).removeClass('zm_move_f');
									$(this).next('span').next('div').next('ul').slideUp(500);
									return;
									}
									$(this).addClass('zm_move_f');
									$(this).next('span').next('div').next('ul').slideDown(500); 
								});
							}
							this.menuSpan1.html('&nbsp;');
							this.menuli1.append(this.menuSpan1);
							this.menuSpan2 = this.createSpan(null);
							this.menuSpan2.html(parent.name);
							
							
							this.menuli1.append(this.menuSpan2);
							this.menuli1.append(this.initOpreate(parent.opreate,null,parent.id,parent.id,null));
							
							//子集模块
							if(childLength>0){
							   this.menuChildUl = this.createUl();
							   for(var j=0;j<childLength;j++){
							   
								child = parent.children[j];
								this.menuChildLi1 = this.createLi('zm_item zm_move_k');
								this.menuChildDiv1 =this.createDiv('zm_move_i zm_pic');
								if(j==childLength-1){
									this.menuChildDiv1 =this.createDiv('zm_move_j zm_pic');
							    }
								this.menuChildLi1.append(this.menuChildDiv1);
								this.menuChildDiv2 = this.createSpan(null);
								this.menuChildDiv2.html(child.name);
								this.menuChildLi1.append(this.menuChildDiv2);
								this.menuChildLi1.append(this.initOpreate(child.opreate,parent.id,child.id,null,parent.id));
								this.menuChildUl.append(this.menuChildLi1);
								this.menuli1.append(this.menuChildUl);
							}
							}
							this.menuul.append(this.menuli1);
						}
						this.accordion.append(this.menuul);
					},//初始化所有的控件
					_init : function() {
						var root = this.options.root;
						if(this.options.state==1){
							//alert(this.options.state);
						}else{
							this.initHead();
							this.initContent();
							$(root).empty();
							$(root).append(this.win);
							$(root).append(this.accordion);
						}
						
					}
				});
			})(jQuery);
			

		 function choose(check){
		 	var opreate = check.attr('opreateAll');
				var part = check.attr('part');
				var parentPart = check.attr('parentPart');
				var opreateChild = check.attr('opreate');
				var parent = check.attr('parent');
				
				if(check.attr('checked')=='checked'){
					check.attr('checked','checked');
					$("[opreate="+opreate+"][enable=enable]").attr('checked',"checked");
					$("[parent="+opreate+"][enable=enable]").attr('checked',"checked");
					if(part!=null){
						$("[parentPart="+part+"][enable=enable]").attr('checked',"checked");
					}
					
					if(opreateChild!=null){
						opreateSelect(opreateChild);
					}
					if(parentPart!=null){
						partSelect(parentPart);
					}
					if(parent!=null){
						parentSelect(parent);
					}
				}else{
					check.attr('checked',false);
					$("[opreate="+opreate+"][enable=enable]").attr('checked',false);
					$("[parent="+opreate+"][enable=enable]").attr('checked',false);
					if(part!=null){
						$("[parentPart="+part+"][enable=enable]").attr('checked',false);
					}
					
					if(opreateChild!=null){
						opreateSelect(opreateChild);
					}
					if(parentPart!=null){
						partSelect(parentPart);
					}
					if(parent!=null){
						parentSelect(parent,parentPart);
					}
				}
		 }
		 function parentSelect(parent,parentPart){
		 	
		 	var $child = $('[parent='+parent+']').length;
			var $childCheck = $('[parent='+parent+'][checked=checked]').length;
			if($child==$childCheck){
				$("[opreate="+parent+"][enable=enable]").attr('checked',"checked");
				$("[opreateAll="+parent+"][enable=enable]").attr('checked',"checked");
			}else{
				if(parentPart!=null){
					$("[part="+parentPart+"][enable=enable]").attr('checked',false);
				}else{
					$("[opreate="+parent+"][enable=enable]").attr('checked',false);
				}
				$("[opreateAll="+parent+"][enable=enable]").attr('checked',false);
			}
		 }
		 function opreateSelect(opreate){
		 	var $opreateChild = $('[opreate='+opreate+']');
			var $opreateChildCheck=$('input[opreate='+opreate+'][checked=checked]');
			if($opreateChild.length==$opreateChildCheck.length){
				$("[opreateAll="+opreate+"][enable=enable]").attr('checked',"checked");
			}else{
				$("[opreateAll="+opreate+"][enable=enable]").attr('checked',false);
			}
		 }
		 function partSelect(parentPart){
		 	 var $partChild = $('[parentPart='+parentPart+']');
			 var $partChildCheck=$('input[parentPart='+parentPart+'][checked=checked]');
				if($partChildCheck.length==$partChild.length){
					$("[part="+parentPart+"][enable=enable]").attr('checked',"checked");
				}else{
					$("[part="+parentPart+"][enable=enable]").attr('checked',false);
				}
		 }