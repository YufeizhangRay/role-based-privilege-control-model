//$(function() {
//	$("#dragger").draggable({
//		onDrag:function(event){
//			//通过event 拿到拖动的html元素节点对象
//			var div = event.data;
//			if(div.left<0){
//				div.left = 0;
//			}
//			if(div.top<0){
//				div.top = 0;
//			}
//			if((div.left + $(div.target).innerWidth()) > $(div.parent).innerWidth()){
//				div.left = $(div.parent).innerWidth() - $(div.target).innerWidth();
//			}
//			if((div.top + $(div.target).innerHeight()) > $(div.parent).innerHeight()){
//				div.top = $(div.parent).innerHeight() - $(div.target).innerHeight();
//			}
//		}
//	});
//});

//$(function(){
//	$(".drag").draggable({
//		proxy:"clone",
//		revert:true
//	});
//	
//	$("#content2").droppable({
//		accept:"#drag1,#drag3",
//		onDrop:function(e,source){
//			$(this).append(source);
//		},
//		onDragEnter:function(e,source){
//			$(source).draggable("options").cursor = "pointer";
//			$(source).draggable("proxy").css("border","1px red solid");
//		},
//		onDragLeave:function(e,source){
//			$(source).draggable("options").cursor = "no-drop";
//			$(source).draggable("proxy").css("border","1px blue solid");
//		},
//		onDragOver:function(e,source){
//			console.log("aaa"); 
//		}
//	});
//});

//$(function(){
//	var arrows = $("<div class='arrows'>&gt;&gt;&gt;</div>").appendTo("body");
//	$(".dragg").draggable({
//		revert:true
//	}).droppable({
//		onDragOver:function(e,source){
//			arrows.css({
//				display:"block",
//				left:$(this).offset().left - 20,
//				top:$(this).offset().top + $(this).outerHeight()-12
//			});
//		},
//		onDragLeave:function(e,source){
//			arrows.hide();
//		},
//		onDrop:function(e,source){
//			$(source).insertAfter(this);
//			arrows.hide();
//		}
//	});
//});

//$(function(){
//	$("#bar").progressbar({
//		value:10
//	});
//});
//function add(){
//	var value = $("#bar").progressbar("getValue");
//	$("#bar").progressbar("setValue",value+3);
//	value = $("#bar").progressbar("getValue");
//	console.log(value); 
//}

//$(function(){
//	$("#pagination").pagination({
//		total:222,
//		pagelist:[10,33,44,55],
//		layout:["list","first","links","last"],
//		buttons:[{
//			text:"xxxx",
//			iconCls:"icon-add",
//			handler:function(){
//				alert("haaha");
//			}
//		}],
//		onSelectPage:function(pageNumber,pageSize){
//			$(this).pagination("loading");
//			alert(pageNumber + ", "+pageSize);
//			$(this).pagination("loaded");
//		}
//	});
//});

//$(function(){
//	$("#p").panel({
//		width:300,
//		height:400,
//		title:"面板测试",
//		iconCls:"icon-save",
//		fit:false,
//		collapsible:true,
//		footer:"#footer"
//	})
//	
//	$("#open").click(function(){
//		$("#p").panel("open");
//	});
//	
//	$("#close").click(function(){
//		$("#p").panel("close");
//	});
//	
//	$("#refresh").click(function(){
//		$("#p").panel("refresh","/sshwebeasyui/panel");
//	});
//});

//$(function(){
//	$("#tt").tabs({
//		onSelect:function(title){
//			$("#tt").tabs("getTab","tab2").panel({
//				href:"/sshwebeasyui/panel"
//			});
//		},
//		tools:[
//			{
//				iconCls:"icon-add",
//				handler:function(){
//					//加一个tab
//					$("#tt").tabs("add",{
//						title:"NewTab",
//						content:"ddddccc",
//						cls:"tab",
//						closable:true,
//					    selected:true,
//						tools:[
//							{
//								iconCls:"icon-mini-refresh",
//								handler:function(){
//									var tab = $("#tt").tabs("getSelected");
//									tab.panel("refresh","/sshwebeasyui/panel");
//								}
//							}
//						]
//					});
//				}
//			},{
//				iconCls:"icon-remove",
//				handler:function(){
//					var tab = $("#tt").tabs("getSelected"); 
//					var index = $("#tt").tabs("getTabIndex",tab);
//					$("#tt").tabs("close",index);
//				}
//			}
//		]
//	});
//});

//$(function(){
//	//绑定document
//	$(document).bind("contextmenu",function(e){
//		//禁用原本的右键菜单
//		e.preventDefault();
//		//显示自己自定义的右键菜单
//		$("#mm").menu("show",{
//			left:e.pageX,
//			top:e.pageY
//		});
//	});
//	
//	$("#mm").menu({
//		onClick:function(item){
//			if(item.name=="new"){
//				alert("New菜单具体的功能");
//			}
//		}
//	});
//});

//$(function(){
//	$("#btn").linkbutton({
//		iconCls:"icon-search"
//	});
//	
//	$("#btn").bind("click",function(){
//		alert("aaaaa");
//	});
//});

//$(function(){
//	$("#btn").menubutton({
//		iconCls:"icon-search",
//		menu:"#mm"
//	});
//	
//	$("#mm").menu({
//		onClick:function(item){
//			if(item.name=="new"){
//				alert("New菜单具体的功能");
//			}
//		}
//    });
//});

//$(function(){
//	$.extend($.fn.validatebox.defaults.rules,{
//		equals:{
//			validator:function(value,param){
//				return value == $(param[0]).val();
//			},
//			message:"两次密码不匹配"
//		},
////		minLength:{
////			validator:function(value,param){
////				return value.length >= param[0];
////			},
////			message:"长度不能小于6"
////		}
//	});
//	
//	$("#ff").form({
//		url:"/sshwebeasyui/panel",
//		onSubmit:function(){//表单提交触发前
//			//数据前端的有效性验证
//			//额外增加参数提交到后台
//			//param.mm = "vv";
//			//返回true false
//			return $("#ff").form("validate");
//		},
//		success:function(data){
//			//json {“名”：“值”}
//			var objdata = eval('('+data+')');//将String转js的object
////			alert(data);
////			alert(objdata);
////			alert(objdata.name);
//		}
//	});
//	
//	$("#eemm").validatebox({
//		required:true,
//		validType:"email"
//	}
//});

//$(function(){
//	$("#cc").combo({
//		panelHeight:100,
//		
//	});
//	$("#sp").appendTo($("#cc").combo("panel"));
//	$("#sp input").click(function(){
//		var value = $(this).val();
//		var text = $(this).next("span").text();
//		$("#cc").combo("setValue",value).combo("setText",text).combo("hidePanel");
//	});
//});

//$(function(){
//	$("#nn").numberbox({
//		min:2,
//		precision:2,
//		prefix:"$"
//	});
//	
//	$("#ccipt").combobox({
//		url:"/sshwebeasyui/comboboxval",//获取回来的json格式数据[{"id":1,"text":"xxx"},{},{}]
//		valueField:"id",
//		textField:"text",
//		onSelect:function(rs){
//			var url="/sshwebeasyui/comboboxval1?id="+rs.id;
//			$("#cc3").combobox("reload",url);
//		}
//	});
//});

//$(function(){
//	$("#dd").datebox({
//		required:true,
//		formatter:function(date){
//			return date.getFullYear()+"年"+(date.getMonth()+1)+"月"+date.getDate()+"日";
//		},
//		parser:function(str){
//			if(str==""||str==null){
//				return new Date();
//			}else{
//				//不是空 xxxx年xx月xx日
//				var y = parseInt(str.substring(0,str.indexOf("年")),10);
//				var m = parseInt(str.substring(str.indexOf("年")+1,str.indexOf("月")),10);
//				var d = parseInt(str.substring(str.indexOf("月")+1,str.indexOf("日")),10);
//				if(!isNaN(y)&&!isNaN(m)&&!isNaN(d)){
//					return new Date(y,m-1,d);
//				}else{
//					return new Date();
//				}
//			}
//		}
//	});
//	
//	$("#dd").datebox().datebox("calendar").calendar({
//		validator:function(date){
//			var now = new Date();
//			return date < now;//符合选择的条件
//		}
//	});
//});

//$(function(){
//	$("#dd").dialog({
//		width:500,
//		height:400,
//		modal:true,
//		toolbar:[{
//			text:"Edit",
//			iconCls:"icon-edit",
//			handler:function(){
//				alert("工具栏的编辑按钮");
//			}
//		}],
//		buttons:[{
//			text:"OK",
//			iconCls:"icon-ok",
//			handler:function(){
//				alert("按钮栏的ok按钮");
//			}
//		}]
//	
//	});
//});

//$(function(){
//	$("#tt").tree({
//		url:"/sshwebeasyui/getAllNodes",
//		dnd:true,
//		checkbox:true,
//		lines:true,
//		animate:true,
//	//	onlyLeafCheck:true,
////		onClick:function(node){
////			$("#tt").tree("beginEdit",node.target);
////		},
//		onAfterEdit:function(node){
//			//获取到修改后的节点的值，通过ajax发出修改后的数据请求
//			//把添加的节点信息，写入数据库，在这里实现
//			var nodeText = node.text;
//			var parentNode = $("#tt").tree("getParent",node.target);
//			if(parentNode!=null){
//				$.post("/sshwebeasyui/addNode",{
//					text:nodeText,
//					parentId:parentNode.id
//				},function(rs){});
//			}else {
//				$.post("/sshwebeasyui/addNode",{
//					text:nodeText
//				},function(rs){});
//			}
//		},
//		onContextMenu:function(e,node){
//			//右键点击的时候触发，发出ajax请求，获取数据库最新节点的id值
//			$.post("/sshwebeasyui/getNewestId",{},function(newestid){id = newestid;});
//			//禁用原本浏览器的右键菜单
//			e.preventDefault();
//			//让右键点击的节点对象被选中
//			$(this).tree("select",node.target);
//			//显示出自定义的右键菜单
//			$("#mm").menu("show",{
//				left:e.pageX,
//				top:e.pageY
//			});
//		}
//	});
//});
//var id;//从数据库中拿到的最新的id值
//function addSonNode() {
//	//获取选中的节点对象
//	var selectedNode = $("#tt").tree("getSelected");
//	//创建出要添加的子节点
//	var node = {
//			parent:selectedNode.target,
//			data:[{
//				id:++id,//在项目里，节点都对应数据库一条记录，id，新创建的节点，id，数据库里的最新id+1
//				text:""
//			}]
//	}
//	//把新建的节点插入到树里面
//	$("#tt").tree("append",node);
//	//添加了新的节点，知道新节点的id，通过新节点的id获取到新节点对象
//	var actNode = $("#tt").tree("find",id);//第一次添加节点，id=8
//	$("#tt").tree("beginEdit",actNode.target);
//}
//
//function addBrotherNode() {
//	//获取选中的节点对象
//	var selectedNode = $("#tt").tree("getSelected");
//	//创建出要添加的子节点
//	var node = {
//			after:selectedNode.target,
//			data:{
//				id:++id,//在项目里，节点都对应数据库一条记录，id，新创建的节点，id，数据库里的最新id+1
//				text:""
//			}
//	}
//	//把新建的节点插入到树里面
//	$("#tt").tree("insert",node);
//	//添加了新的节点，知道新节点的id，通过新节点的id获取到新节点对象
//	var actNode = $("#tt").tree("find",id);//第一次添加节点，id=8
//	$("#tt").tree("beginEdit",actNode.target);
//}
//
//function removeNode() {
//	//获取选中的节点对象
//	var selectedNode = $("#tt").tree("getSelected");
//	$("#tt").tree("remove",selectedNode.target);
//	$.post("/sshwebeasyui/removeNode",{id:selectedNode.id},function(rs){});
//}
//
//function allchk() {
//	var nodes = $("#tt").tree("getChecked","checked");
//	var tarr = new Array();
//	for(var i=0;i<nodes.length;i++){
//		tarr[i] = nodes[i].text;
//	}
//	alert(tarr.join(","));
//}
//
//function halfchk() {
//	var nodes = $("#tt").tree("getChecked","indeterminate");
//	var tarr = new Array();
//	for(var i=0;i<nodes.length;i++){
//		tarr[i] = nodes[i].text;
//	}
//	alert(tarr.join(","));
//}
//
//function nochk() {
//	var nodes = $("#tt").tree("getChecked","unchecked");
//	var tarr = new Array();
//	for(var i=0;i<nodes.length;i++){
//		tarr[i] = nodes[i].text;
//	}
//	alert(tarr.join(","));
//}

$(function() {
	$("#dg").datagrid({
		width:760,
		height:470,
		title:"用户信息表",
		pagination:true,
		pageSize:10,
		url:"/sshwebeasyui/getAllPagerUsers",
//		onLoadSuccess:function(){
//			$("#dg").datagrid("freezeRow",0);
//		},
		//singleSelect:true,
		//method:"get",
//		frozenColumns:[[
//			{field:'ck',checkbox:"true"},
//			{field:"id",sortable:true,title:"id",width:100},
//			{field:"username",title:"username",width:100}
//		]],
		columns:[[
			{field:'ck',checkbox:"true"},
			{field:"id",sortable:true,title:"id",width:100},
			{field:"username",title:"username",width:100,editor:{type:"textbox"}},
			{field:"password",title:"password",width:100,editor:{type:"textbox"}},
			{field:"state",title:"state",width:100,formatter:function(value,row,index){
				if(value==1){
					return "正常状态";
				}else {
					return "禁用状态";
				}
			},editor:{type:"textbox"}},
			{field:"regDate",title:"regDate",width:130,editor:{type:"datebox"}},
			{field:"height",title:"height",width:100,editor:{
				type:"numberbox",
				options:{
					precision:2
				}
			}},
			{field:"roles",title:"roles",width:100,formatter:function(value,row,index){
				//简化一下，只显示一个角色
				var roleNameStr = "";
				if(value!=null&&value.length>0){
					roleNameStr = value[0].roleName;
				}
				return roleNameStr;
			},editor:{
				type:"combobox",
				options:{
					url:"/sshwebeasyui/getRoles",
					method:"post",
					valueField:"id",
					textField:"roleName",
					required:true,
					panelHeight:165
				}
			}},
		]],
		striped:true,
		toolbar:"#tb",
		onClickRow:function(index){
			if(editIndex!=index){
				$("#dg").datagrid("rejectChanges");
			}
			$("#dg").datagrid("beginEdit",index);
			$("#dg").datagrid("selectRow",index);
			editIndex = index;
		}
	});
	var editIndex;
	
	$("#search_btn").click(function() {
		var userid = $("#search_id").numberbox("getValue");
		var username = $("#search_username").textbox("getValue");
		var queryParam = $("#dg").datagrid("options").queryParams;
		queryParam.id = userid;
		queryParam.username = username;
		$("#dg").datagrid("load");
	});
	
	$("#save_user").click(function() {
		//添加到数据库之前，检验数据有效性
		//根据 editIndex 拿到行的row对象
		var row = $("#dg").datagrid("getRows")[editIndex];
		//根据 editIndex 拿到行的编辑器,简单类型的字段，直接获取值，roles是Role对象列表，单独处理
		var editor = $("#dg").datagrid("getEditor",{index:editIndex,field:"roles"});
		var roleId = $(editor.target).combobox("getValue");
		var roleText = $(editor.target).combobox("getText");
		//让dategrid接受修改
		$("#dg").datagrid("acceptChanges");
		//通过ajax发出修改用户的请求
		$.ajax({
			url:"/sshwebeasyui/updateUser",
			method:"post",
			//如果user对象是一个简单对象，即都是基本类型属性。而set集合role对象，必须声明请求的数据类型为json数据格式
			contentType:"application/json;charset=utf-8",
			//传过去的数据格式，从js转换为json格式，stringify()
			data:JSON.stringify({
				id:row.id,
				username:row.username,
				password:row.password,
				state:row.state,
				regDate:row.regDate,
				height:row.height,
				roles:[{id:roleId,roleName:roleText}]
			}),
			success:function(rs){
				if(rs=="success"){
					editIndex = undefined;
					$("#dg").datagrid("reload");
				}else{
					alert("修改失败!");
				}
			}
		});
	});
	
	$("#undo_user").click(function() {
		$("#dg").datagrid("rejectChanges");
		editIndex = undefined;
	});
});