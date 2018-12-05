$(function(){
	
	$(".sshweb_side_tree li a").bind("click",function(){
		var title = $(this).text();
		var iconCls = $(this).attr("data-icon");
		var url = $(this).attr("data-link");
		var iframe = $(this).attr("iframe")==1?true:false;
		addTab(title,url,iconCls,iframe);
	});
	
});

function addTab(title,url,iconCls,iframe){
	var tabPanel = $("#sshweb_tabs");
	if(!tabPanel.tabs("exists",title)){
		var content = "<iframe scrolling='auto' frameborder='0' src='"+url+"' style='width:100%;height:100%;'></iframe>";
		if(iframe){
			tabPanel.tabs("add",{
				title:title,
				content:content, //<iframe>放到tabs，灵活，里边的页面做任何操作，不会受，影响外面的页面，缺点：里边的用js，css，重复加载js文件，css
				iconCls:iconCls,
				fit:true,
				cls:"pd3",
				closable:true
			});
		}else{
			tabPanel.tabs("add",{
				title:title,
				href:url,  //本质是url指向的页面<body></body>,好处，不用重复的加载js文件，里边的这张页面，js，css，必须写在<body></body>，注意没url地址的datagrid的id不能重名
				iconCls:iconCls,
				fit:true,
				cls:"pd3",
				closable:true
			});
		}
	}else{
		tabPanel.tabs("select",title)
	}
}