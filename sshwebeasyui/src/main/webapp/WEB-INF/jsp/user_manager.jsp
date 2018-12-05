<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<table id="usersDatagrid"></table>
		<div id="usersToolbar">
			<div class="usersToolbar_button" style="height: 40px;">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="user_openAdd()" plain="true">添加</a>
	            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="user_openEdit()" plain="true">修改</a>
	            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="user_remove()" plain="true">删除</a>
	            <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="user_cancel()" plain="true">取消</a>
	            <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="user_reload()" plain="true">刷新</a>
			</div>
			<div style="height: 40px;">
				<label>用户名：</label><input class="easyui-textbox" id="user_searchbox" style="width:150px,height:24px;">
            	<a href="#" class="easyui-linkbutton" iconCls="icon-search" id="user_searchbtn">开始检索</a>
			</div>
		</div>
	</div>
	
	<div id="user_dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width: 400px; padding: 10px;">
		<form id="user_dialog_form" method="post">
			<table>
				<tr>
					<td width="100" align="right">用户名称：<input type="hidden" id="id" name="id" /> </td>
					<td><input type="text" id="username" name="username" class="easyui-textbox" data-options="required:true"/></td>
				</tr>
				<tr>
					<td width="100" align="right">密　　码：</td>
					<td><input type="text"  id="password" name="password" class="easyui-textbox"/></td>
				</tr>
				<tr>
					<td width="100" align="right">用户状态：</td>
					<td><input type="text"  id="state" name="state" class="easyui-combotree"
							data-options="url:'/sshwebeasyui/getAllStates',required:true,panelHeight:55"></td>
				</tr>
				<tr>
					<td width="100" align="right">注册日期：</td>
					<td><input type="text"  id="regDate" name="regDate" class="easyui-datebox"/></td>
				</tr>
				<tr>
					<td width="100" align="right">用户角色：</td>
					<td><input type="text"  id="rolesId" name="roles" class="easyui-combotree"
							data-options="url:'/sshwebeasyui/getAllRoles',multiple:true,required:true,panelHeight:133"/></td>
				</tr>
			</table>
		</form>
	</div>
	
	
	<script type="text/javascript">
		$(function(){
			$("#user_searchbtn").bind("click",function(){
				var queryParams = $("#usersDatagrid").datagrid("options").queryParams;
				queryParams.username = $("#user_searchbox").textbox("getValue");
				$("#usersDatagrid").datagrid("load");
			});
			$("#usersDatagrid").datagrid({
				url:"/sshwebeasyui/getAllPagerUsers",
				pagination:true,
				pageSize:10,
				toolbar:"#usersToolbar",
				columns:[[
					{field:"ck",checkbox:true},
					{field:"id",title:"用户编码",width:200},
					{field:"username",title:"用户名",width:200},
					{field:"state",title:"用户状态",width:200,formatter:function(value,row,index){
						if(value == 1){
							return "正常状态";
						}else{
							return "禁用状态";
						}
					}},
					{field:"regDate",title:"注册日期",width:200},
					{field:"roles",title:"用户角色",formatter:function(value,row,index){
						var rolesStr = "";
						if(value!=null && value.length>0){
							for(var i=0;i<value.length;i++){
								rolesStr += "【"+ value[i].roleName +"】"
							}
						}
						return rolesStr;
					}},
				]],
			});
		});
		
		function user_openAdd(){
			$("#user_dialog_form").form("clear");
			$("#user_dialog").dialog({
				title:"添加用户",
				closed:false,/* 显示弹框 */
				modal:true,/* 其他不可选 */
				onOpen:function(){
					$("#username").textbox({disabled:false});
				},
				buttons:[
					{
						text:"确定",iconCls:"icon-ok",
						handler:user_add
					},
					{
						text:"取消",iconCls:"icon-cancel",
						handler:function(){
							$("#user_dialog").dialog("close");
						}
					}
				],
			});
		}
		
		function user_openEdit() {
			$("#user_dialog_form").form("clear");
			var rows = $("#usersDatagrid").datagrid("getSelections");
			if(rows.length > 1){
				$.messager.alert("信息提示","一次只能修改一条数据！","info");
			}else if(rows.length == 0){
				$.messager.alert("信息提示","请勾选您要修改的数据！","info");
			}else{
				//只有一条数据，所以数组为0
				var user = rows[0];
				user.password = "";
				$("#user_dialog_form").form("load",user); // 选中的行的数据绑定到form表单上
				$("#user_dialog").dialog({
					title:"修改用户",
					closed:false,
					modal:true,
					onOpen:function(){
						$("#username").textbox({disabled:true});
					},
					buttons:[
						{
							text:"确定",iconCls:"icon-ok",
							handler: user_edit
						},
						{
							text:"取消",iconCls:"icon-cancel",
							handler:function(){
								$("#user_dialog").dialog("close");
							}
						}
					],
				});
			}
		}
		
		
		function user_remove() {
			var rows = $("#usersDatagrid").datagrid("getSelections");
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
							url:"/sshwebeasyui/deleteUser",
							method:"post",
							data:{
								ids:ids
							},
							success:function(data){ //"ok","error"
								if(data == "ok"){
									$("#usersDatagrid").datagrid("reload");
								}else{
									$.messager.alert("信息提示","删除失败！","info");
								}
							}
						});
					}
				});
			}
			
		}
		
		
		
		function user_add() {
			doAjax("/sshwebeasyui/addUser");
		}
		
		function user_edit(){
			doAjax("/sshwebeasyui/updateUser");
		}
		
		function doAjax(param){
			//var stateArr = $("#state").val().split(",");
			//先拿到rolesId：1,2,3  --->  [{id:1},{id:2}]
			var rolesArr = $("#rolesId").val().split(",");
			var roleObjStr = "[";
			for(var i=0;i<rolesArr.length;i++){
				roleObjStr += "{id:"+rolesArr[i]+"},";
			}
			//[{id:1},{id:2},{id:3}]
			roleObjStr = roleObjStr.substring(0,roleObjStr.length-1) + "]";
			/* 变成JS对象 */
			var roleObj = eval("(" + roleObjStr + ")");
			$.ajax({
				url:param,
				method:"post",
				contentType:"application/json;charset=utf-8",
				data:JSON.stringify({
				    id:$("#id").val(), //增加用户的时候id为数据库自增，此句无意义
					username:$("#username").val(),
					password:$("#password").val(),
					state:$("#state").val(),
					regDate:$("#regDate").val(),
					roles: roleObj
				}),
				success:function(data){ //"ok","error"
					if(data == "ok"){
						$("#user_dialog").dialog("close");
						$("#usersDatagrid").datagrid("reload");
					}else{
						$.messager.alert("信息提示","提交失败！","info");
					}
				}
			});
		}
		
		function user_cancel() {
			$("#usersDatagrid").datagrid("rejectChanges");
		}
		
		function user_reload() {
			$("#usersDatagrid").datagrid("reload");
		}
	</script>
</body>
</html>