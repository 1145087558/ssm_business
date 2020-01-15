<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>墨韵书院-首页</title>
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/basic.css">
<link rel="stylesheet" href="css/personCenter.css">
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
					style="opacity: 0.8; margin-right: 20px;">

			</div>
			<div class="col-md-6">
				<form action="search.form" method="post">
					<input type="text" placeholder="请输入标题、关键字、画家名字等"
						style="width: 100%;"> <input type="button" value="搜索"
						id="search-btn">
				</form>
			</div>
			<div class="col-md-3">
				<p style="float: right;">
					<c:if test="$sessionScope.user==null">
						<a href="login.jsp"><font size="6" color="red"><i
								class="fa fa-cart-plus"></i> </font><font style="margin-left: 6px;"
							size="4" color="black">购物车</font> </a>
					</c:if>

					<c:if test="${sessionScope.user!=null}">
						<a href="displayCart.form"><font size="6" color="red"><i
								class="fa fa-cart-plus"></i> </font><font style="margin-left: 6px;"
							size="4" color="black">购物车</font> </a>
					</c:if>
				</p>
			</div>
		</div>
	</div>
		<div class="menu">
		<ul>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"> 艺术品分类
			</a>
				<ul class="dropdown-menu">
					<li>类型
						<!-- <ul class="dropdown-menu-type">
							<li>国画</li>
							<li>版画</li>
						</ul> -->
					</li>
					<li>题材</li>
					<li>地域</li>
					<li>名家</li>
				</ul></li>

			<li><a href="findAll.form">艺淘宝</a></li>
			<li><a href="allAuthor.form">艺术家</a></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"> 书画知识
			</a>
				<ul class="dropdown-menu">
					<li><a href="calligraphy.form?type_status=1">书法知识</a></li>
					<li><a href="calligraphy.form?type_status=2">书画技法</a></li> 
					<li><a href="calligraphy.form?type_status=3">文房四宝</a>
					<li><a href="calligraphy.form?type_status=4">国画知识</a>
					<li><a href="calligraphy.form?type_status=5">书画保养知识</a></li>
					<li><a href="calligraphy.form?type_status=6">书画装裱知识</a></li>
					<li><a href="calligraphy.form?type_status=7">中国书画流派</a></li>
				</ul></li>
			<li>艺术动态</li>
			<li>名人榜</li>
			<li>关于我们</li>
		</ul>
	</div>

		<div class="perMSg">
			<div class="first">
				<p>更改会员信息</p>
				<form action="person.form" method="post">
					<label>您的名字</label><br> <input type="text" name="name"><br>
					<!-- <label>您的手机号</label><br>
					<input type="text" name="tel"><br> -->
					<label>您的收货地址</label><br> <input type="text" name="address"><br>
					<label>您的个人简介</label><br>
					<textarea name="synopsis"></textarea>
					<br> <input type="submit" value="提交" class="per-btn">
				</form>

			</div>
		</div>
			<!-- <div class="last">
				<p>更改密码</p>
				<form action="" method="post">
					<label>您的旧密码</label><br>
					<input type="password"><br>
					<label>输入新密码</label><br>
					<input type="password"><br>
					<label>确认新密码</label><br>
					<input type="password"><br>
					<input type="submit" value="提交" class="per-btn">
				</form>
			
			</div> -->
		<!-- </div> -->
		
</body>
<script>
	function display(x){
		$(".cate-list").css("display","inline");
	}
	function hid(x){
		$(".cate-list").css("display","none");
	}
</script>
</html>
