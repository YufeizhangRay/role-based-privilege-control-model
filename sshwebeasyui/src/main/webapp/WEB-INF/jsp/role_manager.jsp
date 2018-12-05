<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/easyui/themes/default/easyui.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/icon.css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<table id="roleDatagrid"></table>
		<div id="roleToolbar">
			<div class="roleToolbar_button" style="height: 40px;">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="role_openAdd()" plain="true">添加</a>
	            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="role_openEdit()" plain="true">修改</a>
	            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="role_remove()" plain="true">删除</a>
	            <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="role_cancel()" plain="true">取消</a>
	            <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="role_reload()" plain="true">刷新</a>
			</div>
		</div>
	</div>
	<div id="role_dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width: 400px; padding: 10px;">
		<form id="role_dialog_form" method="post">
			<table>
				<tr>
					<td width="100" align="right">角色名称：<input type="hidden" id="id" name="id" /> </td>
					<td><input type="text" id="roleName" name="roleName" class="easyui-textbox"/></td>
				</tr>
				<tr>
					<td width="100" align="right">角色状态：</td>
					<td><input type="text"  id="state" name="state" class="easyui-combotree"
					data-options="url:'/sshwebeasyui/getAllStates',required:true,panelHeight:55"/></td>
				</tr>
				<tr>
					<td width="100" align="right">角色权限：</td>
					<td><input type="text"  id="permissionsId" name="permissions" class="easyui-combotree"
							data-options="url:'/sshwebeasyui/getAllPermissions',multiple:true,panelHeight:133"/></td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		$(function(){
			$("#roleDatagrid").datagrid({
				url:"/sshwebeasyui/getAllPagerRoles",
				pagination:true,
				pageSize:10,
				toolbar:"#roleToolbar",
				columns:[[
					{field:"ck",checkbox:true},
					{field:"id",title:"角色编码",width:200},
					{field:"roleName",title:"角色名",width:200},
					{field:"state",title:"角色状态",width:200,formatter:function(value,row,index){
						if(value == 1){
							return "正常状态";
						}else{
							return "禁用状态";
						}
					}},
					{field:"permissions",title:"角色权限",formatter:function(value,row,index){
						var permissionsStr = "";
						if(value!=null && value.length>0){
							for(var i=0;i<value.length;i++){
								permissionsStr += "【"+ value[i].resources +"】"
							}
						}
						return permissionsStr;
					}},
				]],
				onLoadSuccess:function(){
					var dgbw = $(".datagrid-view div.datagrid-view2 div.datagrid-body").css("width");
					var dgbh = $(".datagrid-view div.datagrid-view2 div.datagrid-body").css("height");
					//dgbw为 210px 需要转化为数字形式
					$(".datagrid-view div.datagrid-view2 div.datagrid-body").css("width",parseInt(dgbw)+2);
					$(".datagrid-view div.datagrid-view2 div.datagrid-body").css("height",parseInt(dgbh)+4);
				}
			});
		});
		
		function role_openAdd(){
			$("#role_dialog_form").form("clear");
			$("#role_dialog").dialog({
				title:"添加角色",
				closed:false,
				modal:true,
				buttons:[
					{
						text:"确定",iconCls:"icon-ok",
						handler:role_add
					},
					{
						text:"取消",iconCls:"icon-cancel",
						handler:function(){
							$("#role_dialog").dialog("close");
						}
					}
				],
			});
		}
		
		function role_openEdit() {
			$("#role_dialog_form").form("clear");
			var rows = $("#roleDatagrid").datagrid("getSelections");
			if(rows.length > 1){
				$.messager.alert("信息提示","一次只能修改一条数据！","info");
			}else if(rows.length == 0){
				$.messager.alert("信息提示","请勾选您要修改的数据！","info");
			}else{
				var role = rows[0];
				$("#role_dialog_form").form("load",role);
				$("#role_dialog").dialog({
					title:"修改角色",
					closed:false,
					modal:true,
					buttons:[
						{
							text:"确定",iconCls:"icon-ok",
							handler: role_edit
						},
						{
							text:"取消",iconCls:"icon-cancel",
							handler:function(){
								$("#role_dialog").dialog("close");
							}
						}
					],
				});
			}
		}
		
		
		function role_remove() {
			var rows = $("#roleDatagrid").datagrid("getSelections");
			if(rows.length < 1){
				$.messager.alert("信息提示","请勾选您要删除的数据！","info");
			}else{
				$.messager.confirm("信息提示","确定要删除选中的记录吗？",function(result){
					if(result){
						var ids = [];
						$(rows).each(function(){
							ids.push(this.id);
						});
						$.ajax({
							url:"/sshwebeasyui/deleteRole",
							method:"post",
							data:{
								ids:ids
							},
							success:function(data){ //"ok","error"
								if(data == "ok"){
									$("#roleDatagrid").datagrid("reload");
								}else{
									$.messager.alert("信息提示","删除失败！","info");
								}
							}
						});
					}
				});
			}
			
		}
		
		
		
		function role_add() {
			doAjax("/sshwebeasyui/addRole");
		}
		
		function role_edit(){
			doAjax("/sshwebeasyui/updateRole");
		}
		
		function doAjax(param){
			//先拿到rolesId：1,2,3  --->  [{id:1},{id:2}]
			var permissionsArr = $("#permissionsId").val().split(",");
			var permissionsObjStr = "[";
			for(var i=0;i<permissionsArr.length;i++){
				permissionsObjStr += "{id:'"+permissionsArr[i]+"'},";
			}
			//[{id:1},{id:2},{id:3}]
			permissionsObjStr = permissionsObjStr.substring(0,permissionsObjStr.length-1) + "]";
			console.log(permissionsObjStr);
			var permissionsObj = eval("(" + permissionsObjStr + ")");
			$.ajax({
				url:param,
				method:"post",
				contentType:"application/json;charset=utf-8",
				data:JSON.stringify({
					id:$("#id").val(),
					roleName:$("#roleName").val(),
					state:$("#state").val(),
					permissions: permissionsObj
				}),
				success:function(data){ //"ok","error"
					if(data == "ok"){
						$("#role_dialog").dialog("close");
						$("#roleDatagrid").datagrid("reload");
					}else{
						$.messager.alert("信息提示","提交失败！","info");
					}
				}
			});
		}
		
		function role_cancel() {
			$("#roleDatagrid").datagrid("rejectChanges");
		}
		
		function role_reload() {
			$("#roleDatagrid").datagrid("reload");
		}
	</script>
</body>
</html>