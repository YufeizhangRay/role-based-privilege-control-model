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
		<table id="permissionDatagrid"></table>
		<div id="permissionToolbar">
			<div class="permissionToolbar_button" style="height: 40px;">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="permission_openAdd()" plain="true">添加</a>
	            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="permission_openEdit()" plain="true">修改</a>
	            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="permission_remove()" plain="true">删除</a>
	            <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="permission_cancel()" plain="true">取消</a>
	            <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="permission_reload()" plain="true">刷新</a>
			</div>
		</div>
	</div>
	<div id="permission_dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width: 400px; padding: 10px;">
		<form id="permission_dialog_form" method="post">
			<table>
				<tr>
					<td width="100" align="right">权限名称：<input type="hidden" id="id" name="id" /> </td>
					<td><input type="text" id="resources" name="resources" class="easyui-textbox"/></td>
				</tr>
				<tr>
					<td width="100" align="right">权限状态：</td>
					<td><input type="text"  id="state" name="state" class="easyui-combotree"
					data-options="url:'/sshwebeasyui/getAllStates',required:true,panelHeight:55"/></td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		$(function(){
			$("#permissionDatagrid").datagrid({
				url:"/sshwebeasyui/getAllPagerpermissions",
				pagination:true,
				pageSize:10,
				toolbar:"#permissionToolbar",
				columns:[[
					{field:"ck",checkbox:true},
					{field:"id",title:"权限编码",width:200},
					{field:"resources",title:"权限名称",width:200},
					{field:"state",title:"权限状态",width:200,formatter:function(value,row,index){
						if(value == 1){
							return "正常状态";
						}else{
							return "禁用状态";
						}
					}},
				]]
			});
		});
		
		function permission_openAdd(){
			$("#permission_dialog_form").form("clear");
			$("#permission_dialog").dialog({
				title:"添加角色",
				closed:false,
				modal:true,
				buttons:[
					{
						text:"确定",iconCls:"icon-ok",
						handler:permission_add
					},
					{
						text:"取消",iconCls:"icon-cancel",
						handler:function(){
							$("#permission_dialog").dialog("close");
						}
					}
				],
			});
		}
		
		function permission_openEdit() {
			$("#permission_dialog_form").form("clear");
			var rows = $("#permissionDatagrid").datagrid("getSelections");
			if(rows.length > 1){
				$.messager.alert("信息提示","一次只能修改一条数据！","info");
			}else if(rows.length == 0){
				$.messager.alert("信息提示","请勾选您要修改的数据！","info");
			}else{
				var permission = rows[0];
				$("#permission_dialog_form").form("load",permission);
				$("#permission_dialog").dialog({
					title:"修改权限",
					closed:false,
					modal:true,
					buttons:[
						{
							text:"确定",iconCls:"icon-ok",
							handler: permission_edit
						},
						{
							text:"取消",iconCls:"icon-cancel",
							handler:function(){
								$("#permission_dialog").dialog("close");
							}
						}
					],
				});
			}
		}
		
		
		function permission_remove() {
			var rows = $("#permissionDatagrid").datagrid("getSelections");
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
							url:"/sshwebeasyui/deletePermission",
							method:"post",
							data:{
								ids:ids
							},
							success:function(data){ //"ok","error"
								if(data == "ok"){
									$("#permissionDatagrid").datagrid("reload");
								}else{
									$.messager.alert("信息提示","删除失败！","info");
								}
							}
						});
					}
				});
			}
			
		}
		
		
		
		function permission_add() {
			doAjax("/sshwebeasyui/addPermission");
		}
		
		function permission_edit(){
			doAjax("/sshwebeasyui/updatePermission");
		}
		
		function doAjax(param){
			$.ajax({
				url:param,
				method:"post",
				contentType:"application/json;charset=utf-8",
				data:JSON.stringify({
					id:$("#id").val(),
					resources:$("#resources").val(),
					state:$("#state").val(),
				}),
				success:function(data){ //"ok","error"
					if(data == "ok"){
						$("#permission_dialog").dialog("close");
						$("#permissionDatagrid").datagrid("reload");
					}else{
						$.messager.alert("信息提示","提交失败！","info");
					}
				}
			});
		}
		
		function permission_cancel() {
			$("#permissionDatagrid").datagrid("rejectChanges");
		}
		
		function permission_reload() {
			$("#permissionDatagrid").datagrid("reload");
		}
	</script>
</body>
</html>