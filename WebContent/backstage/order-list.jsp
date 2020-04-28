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
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css"
	href="static/h-ui/css/modify_style.css"/>
<link rel="stylesheet" type="text/css"
	href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>订单管理</title>
</head>
<body>
	<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
	产品管理 <span class="c-gray en">&gt;</span> 品牌管理 <a
		class="btn btn-success radius r"
		style="line-height: 1.6em; margin-top: 3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="page-container">
		<div class="text-c">
			<form class="Huiform" method="post" action="searchCartAll.form" target="_self">
				<!-- <input type="text" placeholder="分类名称" value="" class="input-text"
					style="width: 120px"> <span class="btn-upload form-group">
					<input class="input-text upload-url" type="text"
					name="uploadfile-2" id="uploadfile-2" readonly style="width: 200px">
					<a href="javascript:void();" class="btn btn-primary upload-btn"><i
						class="Hui-iconfont">&#xe642;</i> 上传logo</a> <input type="file"
					multiple name="file-2" class="input-file">
				</span> <span class="select-box" style="width: 150px"> <select
					class="select" name="brandclass" size="1">
						<option value="1" selected>国内品牌</option>
						<option value="0">国外品牌</option>
				</select>
				</span>
				<button type="button" class="btn btn-success" id="" name=""
					onClick="picture_colume_add(this);">
					<i class="Hui-iconfont">&#xe600;</i> 添加
				</button> -->
				
				<label>订单编号：</label><input type="text" name="order_number">
				<label>用户名：</label><input type="text" name="user_name">
				<label>支付方式：</label>
				<select name="order_type">
					<option value= "全部">全部</option>
					<option value="余额">余额</option>
					<option value="支付宝">支付宝</option>
				</select>
				<br>
				<label>下单时间：</label><input type="text">
				<input type="submit" value="重置" style="margin-right:140px;">
				<input type="submit" value="搜索" style="margin-right:10px;">
				
			</form>
		</div>
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"><a href="javascript:;" onclick="datadel()"
				class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
					批量删除</a><a class="btn btn-primary radius"
				 href="downloadOder.form"><iclass="Hui-iconfont">&#xe600;</i> 导出信息</a></span> 
				 <span class="r">共有数据：<strong>${fn:length(orderList)}</strong>
				条
			</span>
		</div>
		<div class="mt-20">
			<table class="table table-border table-bordered table-bg table-sort">
				<thead>
					<tr class="text-c">
						<th width="25"><input type="checkbox" name="" value="" style="width:15px;"></th>
						<th width="100">订单号</th>
						<th width="120">作品名称</th>
						<th width="80">用户名称</th>
						<th width="80">价格</th>
						<th width="100">下单时间</th>
						<th width="80">支付方式</th>
						<th>收获地址</th>
						<th width="60">发布状态</th>
						<th width="70">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${orderList}" var="order">
						<tr class="text-c">
							<td><input name="id" type="checkbox" value="${order.id}" style="width:15px;"></td>
							<td class="text-l">${order.order_number}</td>
							<!-- <td><input type="text" class="input-text text-c" value="1"></td> -->
							<td>${order.opus_name}</td>
							<td>${order.user_name}</td>
							<td>${order.opus_price}</td>
							<td>${order.order_date}</td>
							<td>${order.order_type}</td>
							<td class="text-l">${order.user_address}</td>
							<c:choose>
							<c:when test="${order.status == '已支付'}">
							<td class="td-status"><span
								class="label label-success radius">已支付</span></td>
							<td class="td-manage"><a title="发货" href="javascript:;"
								onclick="picture_prompt(this,'${order.order_number}')" class="ml-5"
								style="text-decoration: none"><i class="Hui-iconfont">&#xe6de;</i></a>
								<a title="删除" href="javascript:;"
								onclick="picture_del(this,'${order.id}')" class="ml-5"
								style="text-decoration: none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
							</c:when>
							<c:otherwise>
							<td class="td-status"><span
								class="label label-danger radius">${order.status}</span></td>
							<td class="td-manage"><a title="删除" href="javascript:;"
								onclick="picture_del(this,'${order.id}')" class="ml-5"
								style="text-decoration: none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
							</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer 作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"
		src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript">
$('.table-sort').dataTable({
	"aaSorting": [[ 1, "desc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	"aoColumnDefs": [
	  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
	  {"orderable":false,"aTargets":[0,9]}// 制定列不参与排序
	]
});

/*用户-还原*/
function member_huanyuan(obj,id){
	layer.confirm('确认要通过吗？',function(index){
		$.ajax({
			type: 'POST',
			url: 'modifyOpusByStatus.form?status=0&id='+id,
			dataType: 'json',
			success: function(data){
				
			},
			error:function(data) {
			    console.log(data.msg);
				
			}
		});
		$(obj).parents("tr").remove();
		layer.msg('已通过!',{icon: 6,time:1000});
	});
}

/*作品-删除*/
function picture_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: 'deletOrder.form?id='+id,
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").remove();
				layer.msg('已删除!',{icon:1,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
				$(obj).parents("tr").remove();
				layer.msg('已删除!',{icon:1,time:1000});
			},
		});		
	});
}

/*作品-发货*/
function picture_prompt(obj,id){
	layer.confirm('确认要发货吗？',function(index){
		$.ajax({
			type: 'POST',
			url: 'doDelivery.form?order_number='+id,
			success: function(data){
				$(obj).parents("tr").remove();
				layer.msg('已发货!',{icon:1,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
				$(obj).parents("tr").remove();
				layer.msg('已发货!',{icon:1,time:1000});
			},
		});		
	});
}
</script>
</body>
</html>