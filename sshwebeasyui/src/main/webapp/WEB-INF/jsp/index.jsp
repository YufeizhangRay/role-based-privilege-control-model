<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EasyUI+Spring+SpringMVC+Hibernate+控制模型+通用后台</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/easyui/themes/default/easyui.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/easyui/themes/icon.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/index.css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/index.js"></script>
</head>
<body class="easyui-layout" >

		<!-- <div data-options="region:'north',title:'North Title',split:true" style="height: 100px;"></div>
		<div data-options="region:'south',title:'South Title',split:true" style="height: 100px;"></div>
		<div data-options="region:'east',title:'East',split:true" style="width: 100px;"></div>
		<div data-options="region:'west',title:'West',split:true" style="width: 100px;"></div>
		<div data-options="region:'center',title:'Center Title'" style="padding:5px;background:#eee">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'west'" style="width: 180px"></div>
				<div data-options="region:'center'"></div>
			</div>
		</div> -->
		
		<!-- <form id="ff" method="post">
			<div>
				<label for="name">名字</label>
				<input name="name" class="easyui-textbox" data-options="required:true,prompt:'请填写名字',multiline:true">
			</div>
			<br>
			<div>
				<label for="email">邮箱</label>
				<input id="eemm" name="email" type="text">
			</div>
			<br>
			<input type="submit">
		</form> -->
		
		<!-- <form id ="ff" method="post">
			<input id="pwd" name="pwd" type="password" class="easyui-validatebox" data-options="required:true,validType:'length[6,20]'">
			<input id="rpwd" name="rpwd" type="password" class="easyui-validatebox" required="required" validType="equals['#pwd']">
			<input type="submit">
		</form> -->
		
		<!-- <ul id="tt"></ul>
		<div id="mm" class="easyui-menu">
			<div onclick="addSonNode()" data-options="iconCls:'icon-add'">添加子节点</div>
			<div onclick="addBrotherNode()">添加兄弟节点</div>
			<div onclick="removeNode()">删除节点</div>
		</div>
		<button onclick="allchk()">全选</button>
		<button onclick="halfchk()">半选</button>
		<button onclick="nochk()">没选</button> -->
		
		<table id="dg"></table>
		<div id="tb" style="height: 45px;line-height: 45px;">
			&nbsp;<a id="save_user" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true">保存</a>
			&nbsp;<a id="undo_user" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true">取消</a>
			&nbsp;用户id：<div id="search_id" class="easyui-numberbox"></div>
			&nbsp;用户名：<div id="search_username" class="easyui-textbox"></div>
			&nbsp;<a href="#" id="search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
		</div>
</body>
</html>