
			
			//初始化创建下拉列表
			function createSelect()
			{
				var regContent = document.getElementById("regContent");
				var createTr = document.createElement("tr");
				regContent.appendChild(createTr);
				var createTd = document.createElement("td");
				createTr.appendChild(createTd);
				var createDiv = document.createElement("div");
				createTd.appendChild(createDiv);
				createDiv.setAttribute("id","selDiv_"+index);
				//createDiv.innerHTML='<select name="reg_'+index+'" id="reg_'+index+'" onchange="chooseCascade(this)"><option>请选择</option><option value="9001">游戏道具</option><option value="9002">游戏物品</option><option value="9003">电信积分</option><option value="9004">游戏点数</option></select>';
				createDiv.innerHTML='<select name="reg_'+index+'" id="reg_'+index+'" onchange="chooseCascade(this)"><option>请选择</option><option value="9001">游戏道具</option><option value="9002">游戏物品</option></select>';
				index++;
				return index;
				
			}
			function chooseCascade(obj)
			{
				var selValue = obj.value;
				switch (selValue)
				{
					case "9001":createGameTool(obj);break;
					case "9002":createVirtualGood(obj);break;
					//case "9003":createTelcomBound(obj);break;
					//case "9004":createGamePoint(obj);break;
					default:clearReg(obj);
				}
			}
			function clearReg(obj)
			{
				var selName = obj.name;
				var subIndex = selName.split("_")[1];	
				var casDiv=document.getElementById("casDiv_"+subIndex);
				if("0"==subIndex)
				{
					var buttonDiv = document.getElementById("add_"+subIndex);
					if(null!=casDiv)
					{
						var casTd = casDiv.parentNode;
						var addTd = buttonDiv.parentNode;
						casTd.parentNode.removeChild(casTd);
						addTd.parentNode.removeChild(addTd);
					}
				}
				else
				{
					var buttonDiv = document.getElementById("delete_"+subIndex);
					if(null!=casDiv)
					{
						var casTd = casDiv.parentNode;
						var addTd = buttonDiv.parentNode;
						casTd.parentNode.removeChild(casTd);
						addTd.parentNode.removeChild(addTd);
					}
				}
			}

			function createGameTool(obj)
			{
				var selName = obj.name;
				var subIndex = selName.split("_")[1];
				if(0==subIndex)
				{
					//先判断div里边是否有内容
					var existCasDiv = document.getElementById("casDiv_"+subIndex);
					if(null!=existCasDiv)
					{
						existCasDiv.innerHTML="";
						var selectElement = document.createElement("select");
						existCasDiv.appendChild(selectElement);
						selectElement.setAttribute("id","cas_"+subIndex);
						selectElement.setAttribute("name","cas_"+subIndex);
						selectElement.options.length = 0;
						for(var i=0;i<allGameTools.length;i++)
						{
							selectElement.options.add(
									new Option(allGameTools[i].name,allGameTools[i].id ));	
						}
						//增加文本框记录个数
						var numTextBox = document.createElement("input");
						numTextBox.setAttribute("type","text");
						numTextBox.setAttribute("id","casNum_"+subIndex);
						numTextBox.setAttribute("name","casNum_"+subIndex);
						numTextBox.setAttribute("size",9);
						existCasDiv.appendChild(numTextBox);
						
					}
					else
					{
					
						var parentTr = obj.parentNode.parentNode.parentNode;
						//级联的内容
						var createTd = document.createElement("td");
						parentTr.appendChild(createTd);
						var createDiv = document.createElement("div");
						createDiv.setAttribute("id","casDiv_"+subIndex);
						createTd.appendChild(createDiv);
						var selectElement = document.createElement("select");
						createDiv.appendChild(selectElement);
						selectElement.setAttribute("id","cas_"+subIndex);
						selectElement.setAttribute("name","cas_"+subIndex);
						selectElement.options.length = 0;
						for(var i=0;i<allGameTools.length;i++)
						{
							selectElement.options.add(
									new Option(allGameTools[i].name,allGameTools[i].id ));	
						}
						//增加文本框记录个数
						var numTextBox = document.createElement("input");
						numTextBox.setAttribute("type","text");
						numTextBox.setAttribute("id","casNum_"+subIndex);
						numTextBox.setAttribute("name","casNum_"+subIndex);
						numTextBox.setAttribute("size",9);
						createDiv.appendChild(numTextBox);
						//button按钮 上边为+
						var buttonTd = document.createElement("td");
						parentTr.appendChild(buttonTd);
						var buttonDiv = document.createElement("div");
						buttonDiv.setAttribute("id","add_"+subIndex);
						buttonTd.appendChild(buttonDiv);
						var addButton = document.createElement("input");
						addButton.setAttribute("type","button");
						addButton.setAttribute("id","button_add");
						addButton.setAttribute("name","button_add");
						addButton.setAttribute("value","+");
						addButton.onclick = createSelect;
						buttonDiv.appendChild(addButton);
					}
				}
				else
				{
					//先判断div里边是否有内容
					var existCasDiv = document.getElementById("casDiv_"+subIndex);
					if(null!=existCasDiv)
					{
						existCasDiv.innerHTML="";
						var selectElement = document.createElement("select");
						existCasDiv.appendChild(selectElement);
						selectElement.setAttribute("id","cas_"+subIndex);
						selectElement.setAttribute("name","cas_"+subIndex);
						selectElement.options.length = 0;
						for(var i=0;i<allGameTools.length;i++)
						{
							selectElement.options.add(
									new Option(allGameTools[i].name,allGameTools[i].id ));	
						}
						//增加文本框记录个数
						var numTextBox = document.createElement("input");
						numTextBox.setAttribute("type","text");
						numTextBox.setAttribute("id","casNum_"+subIndex);
						numTextBox.setAttribute("name","casNum_"+subIndex);
						numTextBox.setAttribute("size",9);
						existCasDiv.appendChild(numTextBox);
						
					}
					else
					{
						var parentTr = obj.parentNode.parentNode.parentNode;
						//级联的内容
						var createTd = document.createElement("td");
						parentTr.appendChild(createTd);
						var createDiv = document.createElement("div");
						createDiv.setAttribute("id","casDiv_"+subIndex);
						createTd.appendChild(createDiv);
						var selectElement = document.createElement("select");
						createDiv.appendChild(selectElement);
						selectElement.setAttribute("id","cas_"+subIndex);
						selectElement.setAttribute("name","cas_"+subIndex);
						selectElement.options.length = 0;
						for(var i=0;i<allGameTools.length;i++)
						{
							selectElement.options.add(
									new Option(allGameTools[i].name,allGameTools[i].id ));	
						}
						//增加文本框记录个数
						var numTextBox = document.createElement("input");
						numTextBox.setAttribute("type","text");
						numTextBox.setAttribute("id","casNum_"+subIndex);
						numTextBox.setAttribute("name","casNum_"+subIndex);
						numTextBox.setAttribute("size",9);
						createDiv.appendChild(numTextBox);
						//button按钮 上边为-
						var buttonTd = document.createElement("td");
						parentTr.appendChild(buttonTd);
						var buttonDiv = document.createElement("div");
						buttonDiv.setAttribute("id","delete_"+subIndex);
						buttonTd.appendChild(buttonDiv);
						var deleteButton = document.createElement("input");
						deleteButton.setAttribute("type","button");
						deleteButton.setAttribute("id","button_delete_"+subIndex);
						deleteButton.setAttribute("name","button_delete_"+subIndex);
						deleteButton.setAttribute("value","-");
						deleteButton.onclick =function(){
								var buttonIndex = this.name.split("_")[2];
								document.getElementById("selDiv_"+buttonIndex).parentNode.parentNode.innerHTML="";
						
							};
						buttonDiv.appendChild(deleteButton);	
					}
				}
			}

			function createVirtualGood(obj)
			{
				var selName = obj.name;
				var subIndex = selName.split("_")[1];
				if(0==subIndex)
				{
					//先判断div里边是否有内容
					var existCasDiv = document.getElementById("casDiv_"+subIndex);
					if(null!=existCasDiv)
					{
						existCasDiv.innerHTML="";
						var selectElement = document.createElement("select");
						existCasDiv.appendChild(selectElement);
						selectElement.setAttribute("id","cas_"+subIndex);
						selectElement.setAttribute("name","cas_"+subIndex);
						selectElement.options.length = 0;
						for(var i=0;i<allVirtualGoods.length;i++)
						{
							selectElement.options.add(
									new Option(allVirtualGoods[i].name,allVirtualGoods[i].id ));	
						}
						//增加文本框记录个数
						var numTextBox = document.createElement("input");
						numTextBox.setAttribute("type","text");
						numTextBox.setAttribute("id","casNum_"+subIndex);
						numTextBox.setAttribute("name","casNum_"+subIndex);
						numTextBox.setAttribute("size",9);
						existCasDiv.appendChild(numTextBox);
						
					}
					else
					{
						var parentTr = obj.parentNode.parentNode.parentNode;
						//级联的内容
						var createTd = document.createElement("td");
						parentTr.appendChild(createTd);
						var createDiv = document.createElement("div");
						createDiv.setAttribute("id","casDiv_"+subIndex);
						createTd.appendChild(createDiv);
						var selectElement = document.createElement("select");
						createDiv.appendChild(selectElement);
						selectElement.setAttribute("id","cas_"+subIndex);
						selectElement.setAttribute("name","cas_"+subIndex);
						selectElement.options.length = 0;
						for(var i=0;i<allVirtualGoods.length;i++)
						{
							selectElement.options.add(
									new Option(allVirtualGoods[i].name,allVirtualGoods[i].id ));	
						}
						//增加文本框记录个数
						var numTextBox = document.createElement("input");
						numTextBox.setAttribute("type","text");
						numTextBox.setAttribute("id","casNum_"+subIndex);
						numTextBox.setAttribute("name","casNum_"+subIndex);
						numTextBox.setAttribute("size",9);
						createDiv.appendChild(numTextBox);
						//button按钮 上边为+
						var buttonTd = document.createElement("td");
						parentTr.appendChild(buttonTd);
						var buttonDiv = document.createElement("div");
						buttonDiv.setAttribute("id","add_"+subIndex);
						buttonTd.appendChild(buttonDiv);
						var addButton = document.createElement("input");
						addButton.setAttribute("type","button");
						addButton.setAttribute("id","button_add");
						addButton.setAttribute("name","button_add");
						addButton.setAttribute("value","+");
						addButton.onclick = createSelect;
						buttonDiv.appendChild(addButton);
						
					}
				}
				else
				{
					//先判断div里边是否有内容
					var existCasDiv = document.getElementById("casDiv_"+subIndex);
					if(null!=existCasDiv)
					{
						existCasDiv.innerHTML="";
						var selectElement = document.createElement("select");
						existCasDiv.appendChild(selectElement);
						selectElement.setAttribute("id","cas_"+subIndex);
						selectElement.setAttribute("name","cas_"+subIndex);
						selectElement.options.length = 0;
						for(var i=0;i<allVirtualGoods.length;i++)
						{
							selectElement.options.add(
									new Option(allVirtualGoods[i].name,allVirtualGoods[i].id ));	
						}
						//增加文本框记录个数
						var numTextBox = document.createElement("input");
						numTextBox.setAttribute("type","text");
						numTextBox.setAttribute("id","casNum_"+subIndex);
						numTextBox.setAttribute("name","casNum_"+subIndex);
						numTextBox.setAttribute("size",9);
						existCasDiv.appendChild(numTextBox);
						
					}
					else
					{
						
					
						var parentTr = obj.parentNode.parentNode.parentNode;
						//级联的内容
						var createTd = document.createElement("td");
						parentTr.appendChild(createTd);
						var createDiv = document.createElement("div");
						createDiv.setAttribute("id","casDiv_"+subIndex);
						createTd.appendChild(createDiv);
						var selectElement = document.createElement("select");
						createDiv.appendChild(selectElement);
						selectElement.setAttribute("id","cas_"+subIndex);
						selectElement.setAttribute("name","cas_"+subIndex);
						selectElement.options.length = 0;
						for(var i=0;i<allVirtualGoods.length;i++)
						{
							selectElement.options.add(
									new Option(allVirtualGoods[i].name,allVirtualGoods[i].id ));	
						}
						//增加文本框记录个数
						var numTextBox = document.createElement("input");
						numTextBox.setAttribute("type","text");
						numTextBox.setAttribute("id","casNum_"+subIndex);
						numTextBox.setAttribute("name","casNum_"+subIndex);
						numTextBox.setAttribute("size",9);
						createDiv.appendChild(numTextBox);
						//button按钮 上边为-
						var buttonTd = document.createElement("td");
						parentTr.appendChild(buttonTd);
						var buttonDiv = document.createElement("div");
						buttonDiv.setAttribute("id","delete_"+subIndex);
						buttonTd.appendChild(buttonDiv);
						var deleteButton = document.createElement("input");
						deleteButton.setAttribute("type","button");
						deleteButton.setAttribute("id","button_delete_"+subIndex);
						deleteButton.setAttribute("name","button_delete_"+subIndex);
						deleteButton.setAttribute("value","-");
						deleteButton.onclick =function(){
								var buttonIndex = this.name.split("_")[2];
								document.getElementById("selDiv_"+buttonIndex).parentNode.parentNode.innerHTML="";
						
							};
						buttonDiv.appendChild(deleteButton);	
					}
				}
			}
			
			function createTelcomBound(obj)
			{
				var selName = obj.name;
				var subIndex = selName.split("_")[1];
				if(0==subIndex)
				{
					//先判断div里边是否有内容
					var existCasDiv = document.getElementById("casDiv_"+subIndex);
					if(null!=existCasDiv)
					{
						existCasDiv.innerHTML="";
						var createText = document.createElement("input");
						createText.setAttribute("type","text");
						createText.setAttribute("id","casNum_"+subIndex);
						createText.setAttribute("name","casNum_"+subIndex);
						createText.setAttribute("size",9);
						existCasDiv.appendChild(createText);
					}
					else
					{
						//创建文本框和+按钮
						var parentTr = obj.parentNode.parentNode.parentNode;
						var createTd = document.createElement("td");
						parentTr.appendChild(createTd);
						var createDiv = document.createElement("div");
						createDiv.setAttribute("id","casDiv_"+subIndex);
						createTd.appendChild(createDiv);
						var createText = document.createElement("input");
						createText.setAttribute("type","text");
						createText.setAttribute("id","casNum_"+subIndex);
						createText.setAttribute("name","casNum_"+subIndex);
						createText.setAttribute("size",9);
						createDiv.appendChild(createText);
						//button按钮 上边为+
						var buttonTd = document.createElement("td");
						parentTr.appendChild(buttonTd);
						var buttonDiv = document.createElement("div");
						buttonDiv.setAttribute("id","add_"+subIndex);
						buttonTd.appendChild(buttonDiv);
						var addButton = document.createElement("input");
						addButton.setAttribute("type","button");
						addButton.setAttribute("id","button_add");
						addButton.setAttribute("name","button_add");
						addButton.setAttribute("value","+");
						addButton.onclick = createSelect;
						buttonDiv.appendChild(addButton);
					}
				}
				else
				{
					//先判断div里边是否有内容
					var existCasDiv = document.getElementById("casDiv_"+subIndex);
					if(null!=existCasDiv)
					{
						existCasDiv.innerHTML="";
						var createText = document.createElement("input");
						createText.setAttribute("type","text");
						createText.setAttribute("id","casNum_"+subIndex);
						createText.setAttribute("name","casNum_"+subIndex);
						createText.setAttribute("size",9);
						existCasDiv.appendChild(createText);
					}
					else
					{
						//创建文本框和-按钮
						var parentTr = obj.parentNode.parentNode.parentNode;
						var createTd = document.createElement("td");
						parentTr.appendChild(createTd);
						var createDiv = document.createElement("div");
						createDiv.setAttribute("id","casDiv_"+subIndex);
						createTd.appendChild(createDiv);
						var createText = document.createElement("input");
						createText.setAttribute("type","text");
						createText.setAttribute("id","casNum_"+subIndex);
						createText.setAttribute("name","casNum_"+subIndex);
						createText.setAttribute("size",9);
						createDiv.appendChild(createText);
						//button按钮 上边为-
						var buttonTd = document.createElement("td");
						parentTr.appendChild(buttonTd);
						var buttonDiv = document.createElement("div");
						buttonDiv.setAttribute("id","delete_"+subIndex);
						buttonTd.appendChild(buttonDiv);
						var deleteButton = document.createElement("input");
						deleteButton.setAttribute("type","button");
						deleteButton.setAttribute("id","button_delete_"+subIndex);
						deleteButton.setAttribute("name","button_delete_"+subIndex);
						deleteButton.setAttribute("value","-");
						deleteButton.onclick =function(){
								var buttonIndex = this.name.split("_")[2];
								document.getElementById("selDiv_"+buttonIndex).parentNode.parentNode.innerHTML="";
						
							};
						buttonDiv.appendChild(deleteButton);	
					}	
				}
			}

			function createGamePoint(obj)
			{
				var selName = obj.name;
				var subIndex = selName.split("_")[1];
				if(0==subIndex)
				{
					//先判断div里边是否有内容
					var existCasDiv = document.getElementById("casDiv_"+subIndex);
					if(null!=existCasDiv)
					{
						existCasDiv.innerHTML="";
						var createText = document.createElement("input");
						createText.setAttribute("type","text");
						createText.setAttribute("id","casNum_"+subIndex);
						createText.setAttribute("name","casNum_"+subIndex);
						createText.setAttribute("size",9);
						existCasDiv.appendChild(createText);
					}
					else
					{
						//创建文本框和+按钮
						var parentTr = obj.parentNode.parentNode.parentNode;
						var createTd = document.createElement("td");
						parentTr.appendChild(createTd);
						var createDiv = document.createElement("div");
						createDiv.setAttribute("id","casDiv_"+subIndex);
						createTd.appendChild(createDiv);
						var createText = document.createElement("input");
						createText.setAttribute("type","text");
						createText.setAttribute("id","casNum_"+subIndex);
						createText.setAttribute("name","casNum_"+subIndex);
						createText.setAttribute("size",9);
						createDiv.appendChild(createText);
						//button按钮 上边为+
						var buttonTd = document.createElement("td");
						parentTr.appendChild(buttonTd);
						var buttonDiv = document.createElement("div");
						buttonDiv.setAttribute("id","add_"+subIndex);
						buttonTd.appendChild(buttonDiv);
						var addButton = document.createElement("input");
						addButton.setAttribute("type","button");
						addButton.setAttribute("id","button_add");
						addButton.setAttribute("name","button_add");
						addButton.setAttribute("value","+");
						addButton.onclick = createSelect;
						buttonDiv.appendChild(addButton);
					}
				}
				else
				{
					//先判断div里边是否有内容
					var existCasDiv = document.getElementById("casDiv_"+subIndex);
					if(null!=existCasDiv)
					{
						existCasDiv.innerHTML="";
						var createText = document.createElement("input");
						createText.setAttribute("type","text");
						createText.setAttribute("id","casNum_"+subIndex);
						createText.setAttribute("name","casNum_"+subIndex);
						createText.setAttribute("size",9);
						existCasDiv.appendChild(createText);
					}
					else
					{
						//创建文本框和+按钮
						var parentTr = obj.parentNode.parentNode.parentNode;
						var createTd = document.createElement("td");
						parentTr.appendChild(createTd);
						var createDiv = document.createElement("div");
						createDiv.setAttribute("id","casDiv_"+subIndex);
						createTd.appendChild(createDiv);
						var createText = document.createElement("input");
						createText.setAttribute("type","text");
						createText.setAttribute("id","casNum_"+subIndex);
						createText.setAttribute("name","casNum_"+subIndex);
						createText.setAttribute("size",9);
						createDiv.appendChild(createText);
						//button按钮 上边为-
						var buttonTd = document.createElement("td");
						parentTr.appendChild(buttonTd);
						var buttonDiv = document.createElement("div");
						buttonDiv.setAttribute("id","delete_"+subIndex);
						buttonTd.appendChild(buttonDiv);
						var deleteButton = document.createElement("input");
						deleteButton.setAttribute("type","button");
						deleteButton.setAttribute("id","button_delete_"+subIndex);
						deleteButton.setAttribute("name","button_delete_"+subIndex);
						deleteButton.setAttribute("value","-");
						deleteButton.onclick =function(){
								var buttonIndex = this.name.split("_")[2];
								document.getElementById("selDiv_"+buttonIndex).parentNode.parentNode.innerHTML="";
						
							};
						buttonDiv.appendChild(deleteButton);
					}
				}
			}
			function produceReg()
			{
				var produceReg = document.getElementById("resultSel");
				var resultCas = document.getElementById("resultCas");
				if(null!=resultSel)
				{
					resultSel.innerHTML="";
				}
				if(null!=resultCas)
				{
					resultCas.innerHTML="";
				}
				produceReg.innerHTML='<select name="resultReg1" id="resultReg1" onchange="resultCascade(this)"><option>请选择</option><option value="9001">游戏道具</option><option value="9002">游戏物品</option><option value="9003">电信积分</option><option value="9004">游戏点数</option></select>';;
			}
			function resultCascade(obj)
			{
				var resultCas = document.getElementById("resultCas");
				if(null!=resultCas)
				{
					resultCas.innerHTML="";
				}
				if("9001"==obj.value)
				{
					
					var selectElement = document.createElement("select");
					resultCas.appendChild(selectElement);
					selectElement.setAttribute("id","resultReg2");
					selectElement.setAttribute("name","resultReg2");
					for(var i=0;i<allGameTools.length;i++)
					{
						selectElement.options.add(
								new Option(allGameTools[i].name,allGameTools[i].id ));	
					}
					//增加文本框记录个数
					/*
					var numTextBox = document.createElement("input");
					numTextBox.setAttribute("type","text");
					numTextBox.setAttribute("id","resultReg3");
					numTextBox.setAttribute("name","resultReg3");
					numTextBox.setAttribute("size",9);
					resultCas.appendChild(numTextBox);
					*/
				}
				else if("9002"==obj.value)
				{
					var selectElement = document.createElement("select");
					resultCas.appendChild(selectElement);
					selectElement.setAttribute("id","resultReg2");
					selectElement.setAttribute("name","resultReg2");
					for(var i=0;i<allVirtualGoods.length;i++)
					{
						selectElement.options.add(
								new Option(allVirtualGoods[i].name,allVirtualGoods[i].id ));	
					}
					//增加文本框记录个数
					/*
					var numTextBox = document.createElement("input");
					numTextBox.setAttribute("type","text");
					numTextBox.setAttribute("id","resultReg3");
					numTextBox.setAttribute("name","resultReg3");
					numTextBox.setAttribute("size",9);
					resultCas.appendChild(numTextBox);
					*/
				}
				else if("9003"==obj.value||"9004"==obj.value)
				{
					var numTextBox = document.createElement("input");
					numTextBox.setAttribute("type","text");
					numTextBox.setAttribute("id","resultReg3");
					numTextBox.setAttribute("name","resultReg3");
					numTextBox.setAttribute("size",9);
					resultCas.appendChild(numTextBox);
				}
				
			}

		
			
