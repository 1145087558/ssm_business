<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>墨韵书院-首页</title>
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/basic.css">
<link rel="stylesheet" href="css/person_basic.css">
<link rel="stylesheet" href="css/order.css">
<link
	href="//netdna.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"
	rel="stylesheet">
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body>

	<c:if test="${sessionScope.user==null}">
		<div class="top">
			<a href="login.jsp"><button type="button" class="btn btn-primary">登录</button></a>
			<a href="register.jsp"><button type="button"
					class="btn btn-primary">注册</button></a>
		</div>
	</c:if>
	<c:if test="${sessionScope.user!=null}">
		<div class="top">
			<div class="dropdown" id="dp">
				<span style="font-size: 16px;">${sessionScope.user.tel}</span>
				<div class="dropdown-content" id="dpct">
					<a href="" id="order" data-toggle="modal" data-target="#editModal"
						ng-click="entity={}">余额充值</a> <a href="seekOrder.form">我的订单</a> <a
						href="personCenter.jsp">个人中心</a> <a href="upload.jsp">上传作品</a> <a
						href="logout.form">退出</a>
				</div>
			</div>
		</div>
	</c:if>

	<div class="modal fade" id="editModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="pay.form" method="post">
					<label>请输入充值金额：</label><br> <input type="text" name="balance"
						style="width: 500px; border: 1px solid red; border-radius: 4px;"><br>
					<input type="submit" value="确定"
						style="margin: 10px 20px 0px 20px; width: 55px; height: 35px; border-radius: 4px; border: 1px solid #e2e2e2; background: white;">
					<button class="btn btn-default" data-dismiss="modal"
						aria-hidden="true">关闭</button>
				</form>

			</div>
		</div>
	</div>



	<!-- 搜索和logo行 -->
	<div class="container">
		<div class="row">

			<div class="col-md-3">
				<img src="image/logo1.jpg" width="80%" height="100%"
					style="opacity: 0.8;">
			</div>
			<div class="col-md-6">
				<form action="search.form" method="post">
					<input type="text" placeholder="请输入标题、关键字、画家名字等"
						style="width: 100%;" name="search"> <input type="submit"
						value="搜索" id="search-btn">
				</form>
			</div>
			<div class="col-md-3">
				<p style="float: right;">
					<a href="displayCart.form"><font size="6" color="red"><i
							class="fa fa-cart-plus"></i> </font><font style="margin-left: 6px;"
						size="4" color="black">购物车</font> </a>
				</p>

			</div>
		</div>
	</div>


	<!-- 导航栏 -->

	<div class="menu">
		<ul>
			<li class="dropdown" id="type"><span style="font-size: 20px;">艺术品分类</span>
				<div class="dropdown-content" id="art-type">
					<a href="search.form?search=国画">国画</a> <a
						href="search.form?search=油画">油画</a> <a
						href="search.form?search=书法">书法</a> <a
						href="search.form?search=版画">版画</a>
				</div></li>

			<li><a href="findAll.form">艺淘宝</a></li>
			<li><a href="allAuthor.form">艺术家</a></li>
			<li class="dropdown" id="type"><span style="font-size: 20px;">书画知识</span>
				<div class="dropdown-content" id="art-type">
					<a href="calligraphy.form?type_status=1">书法知识</a> <a
						href="calligraphy.form?type_status=2">书画技法</a> <a
						href="calligraphy.form?type_status=3">文房四宝</a> <a
						href="calligraphy.form?type_status=4">国画知识</a> <a
						href="calligraphy.form?type_status=5">书画保养知识</a> <a
						href="calligraphy.form?type_status=6">书画装裱知识</a> <a
						href="calligraphy.form?type_status=7">中国书画流派</a>
				</div></li>
			<li><a href="collect.jsp">我的收藏</a></li>

			<li class="dropdown" id="type"><span style="font-size: 20px;">
					中国名画艺术欣赏</span>

				<div class="dropdown-content" id="art-type">
					<a href="#">中国油画欣赏</a> <a href="#">中国山水画欣赏</a> <a href="#">中国花鸟画欣赏</a>
					<a href="#">中国版画欣赏</a>
				</div></li>
			<li><a href="hof.jsp">名人榜</a></li>
			<li class="dropdown" id="type"><span style="font-size: 20px;">关于我们</span>

				<div class="dropdown-content" id="art-type">
					<a href="#">网站简介</a> <a href="#">联系我们</a> <a href="#">友情链接</a>
				</div></li>
		</ul>
	</div>
	<!-- <hr style="border:1px solid red;margin-top:-1px;width:100%;"> -->
	<%-- <div class="cart">
		<h2 style="text-align: center; margin-bottom: 20px;">订单管理</h2>
		<table border="1" style="width: 100%; text-align: center;">
			<tr>
				<td>订单编号</td>
				<td>作品名称</td>
				<td>作品价格</td>
				<td>订单生成时间</td>
			</tr>
			<c:forEach items="${orderList}" var="order">
				<tr>
					<td>${order.id}</td>
					<td>${order.opus_name}</td>
					<td>${order.opus_price}</td>
					<td>${order.order_date}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
 --%>
 
 
 
 		<div class="content">
			<div class="content-left">
				<p>个人中心</p>
				<hr>
					<p><a href="personCenter.jsp">个人资料</a></p>
					<p><a href="safe_setup.jsp">安全设置</a></p>
					<p><a href="address_manage.jsp">收货地址</a></p>
					<p><a href="seekOrder.form">订单管理</a></p>
					<p><a href="seekEvaluate.form">评价</a></p>
			</div>
			<div class="content-right">
				<h3>订单管理/Order Manage</h3>
				<hr>
				
				<table class="table table-hover">
					<thead><th>商品</th><th>价格</th><th>商品操作</th><th>交易状态</th><th>交易操作</th></thead>
					<tbody>
					
					<c:forEach items="${orderList}" var="order" varStatus="i">
						<tr><td style="width:200px;">订单编号:${order.order_number}</td><td colspan="3">交易时间:${order.order_date}</td><td></td><td></td><td></td></tr>
					<tr>
						<td>
							<div class="opusMsg">
								<div class="opusMsg-left">
									<img src="${opusList[i.index].opus_image}" style="width:100%;height:100%;">
								</div>
								<div class="opusMsg-right">
									<p>作品名称：${order.opus_name}</p>
									<p>作者：${opusList[i.index].author_name}</p>
								</div>
							</div>
						</td>
						<td style="line-height:100px;">${order.opus_price}￥</td>
						<c:choose>
						<c:when test="${order.status =='已支付'}">
						<td style="line-height:100px;"><a href="javascript:;" onclick="prompt('${order.order_number}',this)">催促发货</a>
						</td>
						</c:when>
						<c:when test="${order.status =='已发货'}">
						<td style="line-height:100px;"><a href="javascript:;" onclick="receive('${order.order_number}',this)">确认收货</a>
						</td>
						</c:when>
						<c:otherwise>
						<td style="line-height:100px;"></td>
						</c:otherwise>
						</c:choose>
						<td style="line-height:100px;">${order.status}</td>
						<td style="line-height:100px;"><a href="javascript:;" onclick="deleteOrder(${order.id},this)">删除订单</a>
						<a href="javascript:;" onclick="refundRequest('${order.order_number}',this)">退款</a></td>
					</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
</body>

<script type="text/javascript">

function deleteOrder(id,obj){
	$.ajax({
		url:"deleteOrder.form",
		type:"post",
		data:{
			"id":id
		},
		success:function(){
			
		}
	})
	var parent = $(obj).parent().parent();
	parent.prev().remove();
	parent.remove();
}

function refundRequest(orderNumber,obj){
	$.ajax({
		url:"refundSH.form",
		type:"post",
		data:{
			"out_trade_no":orderNumber
		},
		success:function(){
			
		}
	})
	$(obj).parent().next().text("退款中");
	$(obj).parent().next().prev().text("");
}

function prompt(orderNumber,obj){
	$.ajax({
		url:"prompt.form",
		type:"post",
		data:{
			"out_trade_no":orderNumber
		},
		success:function(){
			
		}
	})
	$(obj).parent().next().text("已催促");
	$(obj).parent().next().prev().text("");
}

function receive(orderNumber,obj){
	$.ajax({
		url:"receive.form",
		type:"post",
		data:{
			"out_trade_no":orderNumber
		},
		success:function(){
			
		}
	})
	$(obj).parent().next().text("已收货");
	$(obj).parent().next().prev().text("");
}
</script>
</html>
