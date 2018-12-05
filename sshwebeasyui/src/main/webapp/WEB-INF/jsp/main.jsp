<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>xxxx管理后台</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/easyui/themes/default/easyui.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/icon.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/main.js"></script>
</head>
<body class="easyui-layout">
	<div class="sshweb_header" data-options="region:'north',border:false,split:true,minHeight:60,maxHeight:60">
		<div class="sshweb_header_left">
			<h1>Easyui+Spring+SpringMVC+Hibernate+权限控制模型</h1>
		</div>
		<div class="sshweb_header_right">
			<p><strong>${loginUser.username }</strong>，欢迎您！</p>
			<p><a href="#">网站首页</a>|<a href="#">帮助中心</a>|<a href="${pageContext.request.contextPath}/logout">安全退出</a></p>
		</div>
	</div>
	<div class="sshweb_sidebar" data-options="region:'west',border:true,split:true,title:'导航菜单',minWidth:160,maxWidth:160">
		<div class="easyui-accordion" data-options="border:false,fit:true">
			<div title="系统设置" data-options="iconCls:'icon-wrench'" style="padding: 5px;">
				<ul class="easyui-tree sshweb_side_tree">
					<li iconCls="icon-users"><a href="#" data-icon="icon-users" data-link="${pageContext.request.contextPath}/userManager" iframe="0">用户管理</a></li>
					<li iconCls="icon-user-group"><a href="#" data-icon="icon-user-group" data-link="${pageContext.request.contextPath}/roleManager" iframe="1">角色管理</a></li>
					<li iconCls="icon-book"><a href="#" data-icon="icon-book" data-link="${pageContext.request.contextPath}/permissionManager" iframe="1">权限管理</a></li>
					<li><a>数据字典</a></li>
					<li><a>系统参数</a></li>
				</ul>
			</div>
			<div title="菜单设置" data-options="iconCls:'icon-wrench'" style="padding: 5px;">
				
			</div>
			<div title="订单管理" data-options="iconCls:'icon-wrench'" style="padding: 5px;">
				
			</div>
		</div>
	</div>
	<div class="sshweb_footer" data-options="region:'south',border:true,split:true,minHeight:30,maxHeight:30">
		&copy;2018 Sshweb All Rights Reserved.
	</div>
	<div class="sshweb_main" data-options="region:'center'">
		<div id="sshweb_tabs" class="easyui-tabs" data-options="border:false,fit:true">
			<div title="首页" data-options="href:'${pageContext.request.contextPath}/main/index',closable:false,iconCls:'icon-tip',cls:'pd3'"></div>
		</div>
	</div>
</body>
</html>