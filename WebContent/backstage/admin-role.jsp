<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>角色管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理 <span class="c-gray en">&gt;</span> 角色管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="cl pd-5 bg-1 bk-gray"> <span class="l"> <a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" href="javascript:;" onclick="admin_role_add('添加用户','member-add.jsp','','510')"><i class="Hui-iconfont">&#xe600;</i> 添加用户</a> </span> <span class="r">共有数据：<strong>${fn:length(users)}</strong> 条</span> </div>
	<table class="table table-border table-bordered table-hover table-bg">
		<thead>
			<tr>
				<th scope="col" colspan="6">角色管理</th>
			</tr>
			<tr class="text-c">
				<th width="25"><input type="checkbox" value="" name=""></th>
				<th width="40">ID</th>
				<th width="200">角色名</th>
				<th>用户列表</th>
				<th width="300">描述</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${users}" var="user">
		<tr class="text-c">
				<td><input type="checkbox" value="${user.id}" name="id"></td>
				<td>${user.id}</td>
				<c:if test="${user.status==1}">
				<td>超级管理员</td>
				<td><a href="#">${user.name}</a></td>
				<td>拥有至高无上的权利</td>
				<td class="f-14"><a style="text-decoration:none" onClick="member_stop(this,'${user.id}')" href="javascript:;" title="管理员"><i class="Hui-iconfont">&#xe631;</i></a></td>
				</c:if>
				<c:if test="${user.status==0}">
				<td>普通用户</td>
				<td><a href="#">${user.name}</a></td>
				<td>只能对自己上传作品进行修改</td>
				<td class="f-14"><a style="text-decoration:none" onClick="member_start(this,'${user.id}')" href="javascript:;" title="超级管理员"><i class="Hui-iconfont">&#xe631;</i></a></td>
				</c:if>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript">
/*管理员-角色-添加*/
function admin_role_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*管理员-角色-编辑*/
function admin_role_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*管理员-角色-删除*/
function admin_role_del(obj,id){
	layer.confirm('角色删除须谨慎，确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '',
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").remove();
				layer.msg('已删除!',{icon:1,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}

/*用户-普通用户*/
function member_stop(obj,id){
	layer.confirm('确认要降级吗？',function(index){
		$.ajax({
			type: 'POST',
			url: 'modifyUserStatus.form?status=0&id='+id,
			dataType: 'json',
			success: function(data){
				
				layer.msg('已降级!',{icon: 5,time:1000});
				window.location.href="getUserAllList.form";
			},
			error:function(data) {
				console.log(data.msg);
				layer.msg('已降级!',{icon: 5,time:1000});
				window.location.href="getUserAllList.form";
			}
		});	
	});
}

/*用户-超级管理员*/
function member_start(obj,id){
	
	layer.confirm('确认要升级吗？',function(index){
		$.ajax({
			type: 'POST',
			url: 'modifyUserStatus.form?status=1&id='+id,
			dataType: 'json',
			success: function(data){
				layer.msg('已升级!',{icon: 6,time:1000});
				window.location.href="getUserAllList.form";
			},
			error:function(data) {
				console.log(data.msg);
				layer.msg('已升级!',{icon: 6,time:1000});
				window.location.href="getUserAllList.form";
			}
		});
		
	});
}
</script>
</body>
</html>