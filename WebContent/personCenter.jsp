<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>墨韵书院-首页</title>
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/person_center.css">
<link rel="stylesheet" href="css/basic.css">
<link rel="stylesheet" href="css/person_basic.css">
<link rel="stylesheet" href="css/dcalendar.picker.css" />
<link
	href="https://netdna.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"
	rel="stylesheet">
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/dcalendar.picker.js"></script>
<script type="text/javascript" src="js/1.14.0/jquery.validate.js"></script>
</head>

<body>

	<c:if test="${sessionScope.user==null}">
		<div class="top">
			<a href="login.jsp"><button type="button" class="btn btn-primary">登录</button>
			</a> <a href="register.jsp"><button type="button"
					class="btn btn-primary">注册</button> </a>
		</div>
	</c:if>
	<c:if test="${sessionScope.user!=null}">
		<div class="top">
			<div class="dropdown" id="dp">
				<span style="font-size: 16px;">${sessionScope.user.tel}</span>
				<div class="dropdown-content" id="dpct">
					<a href="" id="order" data-toggle="modal" data-target="#editModal"
						ng-click="entity={}">余额充值</a>
					<!--  <a href="seekOrder.form">我的订单</a> -->
					<a href="personCenter.jsp">个人中心</a> <a href="upload.jsp">上传作品</a> <a
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
				<img src="image/logo1.jpg" style="opacity: 0.8; width: 80%;">

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
					<c:if test="${sessionScope.user==null}">
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

	<!-- 导航栏 -->
	<div class="menu">
		<ul>
			<li class="dropdown" id="type"><span style="font-size: 20px;">艺术品分类</span>
				<div class="dropdown-content" id="art-type">
					<a href="#">类型</a> <a href="#">题材</a> <a href="$">地域</a>
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
			<li class="dropdown" id="type"><span style="font-size: 20px;">艺术动态</span>
				<div class="dropdown-content" id="art-type">
					<a href="#">书画大赛动态信息</a> <a href="#">艺术展览信息</a>
				</div></li>

			<li class="dropdown" id="type"><span style="font-size: 20px;">
					中国名画艺术欣赏</span>

				<div class="dropdown-content" id="art-type">
					<a href="#">中国油画欣赏</a> <a href="#">中国山水画欣赏</a> <a href="#">中国花鸟画欣赏</a>
					<a href="#">中国版画欣赏</a>
				</div></li>
			<li><a href="">名人榜</a></li>
			<li class="dropdown" id="type"><span style="font-size: 20px;">关于我们</span>

				<div class="dropdown-content" id="art-type">
					<a href="site_synopsis.jsp">网站简介</a> <a href="#">联系我们</a> <a
						href="link.jsp">友情链接</a>
				</div></li>
		</ul>
	</div>

	<div class="content">
		<div class="content-left">
			<p>个人中心</p>
			<hr>
			<p>
				<a href="personCenter.jsp">个人资料</a>
			</p>
			<p>
				<a href="safe_setup.jsp">安全设置</a>
			</p>
			<p>
				<a href="address_manage.jsp">收货地址</a>
			</p>
			<p>
				<a href="seekOrder.form">订单管理</a>
			</p>
			<p>
				<a href="seekEvaluate.form">评价</a>
			</p>
		</div>
		<div class="content-right">
			<h3>个人资料/Personal information</h3>
			<hr>
			<form action="modifyUser.form" method="post" id="modifyUserform">
				<div id="Error"></div>
				<label>昵称</label>
				<p>
					<input id="name" type="text" name="name"
						value="${sessionScope.user.name}">
				</p>
				<label>邮箱</label>
				<p>
					<input id="email" type="text" name="email"
						value="${sessionScope.user.email}">
				</p>
				<label>电话</label>
				<p>
					<input id="tel" type="tel" name="tel"
						value="${sessionScope.user.tel}">
				</p>
				<label>介绍</label>
				<p>
					<input id="synopsis" type="text" name="synopsis"
						value="${sessionScope.user.synopsis}">
				</p>
				<p>
					<label>性别</label>
					<c:choose>
						<c:when test="${sessionScope.user.sex =='男'}">
							<input type="radio" name="sex" class="sex" value="男"
								checked="checked">男
					<input type="radio" name="sex" class="sex" value="女">女
					<input type="radio" name="sex" class="sex" value="保密">保密
				</p>
				</c:when>
				<c:when test="${sessionScope.user.sex =='女'}">
					<input type="radio" name="sex" class="sex" value="男">男
					<input type="radio" name="sex" class="sex" value="女"
						checked="checked">女
					<input type="radio" name="sex" class="sex" value="保密">保密</p>
				</c:when>
				<c:when test="${sessionScope.user.sex =='保密'}">
					<input type="radio" name="sex" class="sex" value="男">男
					<input type="radio" name="sex" class="sex" value="女">女
					<input type="radio" name="sex" class="sex" value="保密"
						checked="checked">保密</p>
				</c:when>
				<c:otherwise>
					<input type="radio" name="sex" class="sex" value="男">男
					<input type="radio" name="sex" class="sex" value="女">女
					<input type="radio" name="sex" class="sex" value="保密">保密</p>
				</c:otherwise>
				</c:choose>
				<input type="hidden" name="id" value="${sessionScope.user.id }">
				<input type="submit" value="修改">
			</form>
		</div>
</body>

<script>
	$(function() {
		$("#modifyUserform").validate({
			rules : {
				name : {
					required : true,
					minlength : 2
				},
				email : {
					required : false,
					email : true
				},
				tel : {
					
					isMobile : true

				}
				
			},
			messages : {
				name : {
					required : "请输入姓名",
					minlength : " 姓名至少2位"
				},
				email : {
					email:"请输入正确的邮箱"
					
				},
				tel : {
					required : "请输入手机号码"
					
				}
			
			},
			errorPlacement : function(error, element) {
				$("#Error").html($(error).text());
			},
			submitHandler : function(form) {
				form.submit();
			}
		});
	});
	jQuery.validator
			.addMethod(
					"isMobile",
					function(value, element) {
						var length = value.length;
						var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
						return this.optional(element)
								|| (length == 11 && mobile.test(value));
					}, "请正确填写手机号码");
	$("#order").click(function() {
		$("form").css("display", "inline");
	});
</script>
</html>
