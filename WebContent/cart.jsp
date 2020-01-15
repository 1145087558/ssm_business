<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>墨韵书院-首页</title>
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/basic.css">
<link rel="stylesheet" href="css/cart.css">
<link
	href="//netdna.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"
	rel="stylesheet">
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="js/layer/2.4/layer.js"></script>

<script type="text/javascript" src="js/cart.js"></script>
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
					<a href=""><font size="6" color="red"><i
							class="fa fa-cart-plus"></i> </font><font style="margin-left: 6px;"
						size="4" color="black">购物车</font> </a>
				</p>

			</div>
		</div>
	</div>


	<!-- 导航栏 -->

	<div class="menu">
		<ul>
			<li class="dropdown" id="type">
				<span style="font-size: 20px;">艺术品分类</span>
				<div class="dropdown-content" id="art-type">
					<a href="">类型</a>
					<a href="">题材</a>
					<a href="">地域</a>
				</div>
			</li>

			<li><a href="findAll.form">艺淘宝</a></li>
			<li><a href="allAuthor.form">艺术家</a></li>
			<li class="dropdown" id="type">
				<span style="font-size: 20px;">书画知识</span>
				<div class="dropdown-content" id="art-type">
					<a href="calligraphy.form?type_status=1">书法知识</a>
					<a href="calligraphy.form?type_status=2">书画技法</a>
					<a href="calligraphy.form?type_status=3">文房四宝</a>
					<a href="calligraphy.form?type_status=4">国画知识</a>
					<a href="calligraphy.form?type_status=5">书画保养知识</a>
					<a href="calligraphy.form?type_status=6">书画装裱知识</a>
					<a href="calligraphy.form?type_status=7">中国书画流派</a>
				</div>
				</li>
			<li class="dropdown" id="type">
			  <span style="font-size: 20px;">艺术动态</span>
				<div class="dropdown-content" id="art-type">
					<a href="#">书画大赛动态信息</a>
					<a href="#">艺术展览信息</a>
				</div>
			</li>
			
			<li class="dropdown" id="type">
			 <span style="font-size: 20px;"> 中国名画艺术欣赏</span>
		
				<div class="dropdown-content" id="art-type">
					<a href="#">中国油画欣赏</a>
					<a href="#">中国山水画欣赏</a>
					<a href="#">中国花鸟画欣赏</a>
					<a href="#">中国版画欣赏</a>
				</div>
			</li>
			<li><a href="">名人榜</a></li>
			<li class="dropdown" id="type">
				 <span style="font-size: 20px;">关于我们</span>
			
				<div class="dropdown-content" id="art-type">
					<a href="#">网站简介</a>
					<a href="#">联系我们</a>
					<a href="#">友情链接</a>
				</div>
			</li>
		</ul>
	</div>
	<!-- <hr style="border:1px solid red;margin-top:-1px;width:100%;"> -->
	<div class="cart">
		<h2 style="text-align: center; margin-bottom: 20px;">购物车</h2>
		<div class="sub_top">
			<input type="checkbox" id="check" value="quanxuan" class="quanxuan"
				onclick="checkAll()" /> <span>全选</span>
		</div>
		<c:forEach items="${cartList}" var="cart">
			<div class="parent_box">
				<div class="sub_content">
					<input type="checkbox" name="checkbox" value="quanxuan"
						class="quanxuan" onclick="checkObj(this)" />
				</div>
				<div class="left_box">
					<img src="${cart.opus_image}" width="100%" height="100%">
				</div>
				<div class="right_box">
					<p style="margin-top: 60px; font-size: 20px;">
						<span>${cart.author_name}</span><span>《${cart.opus_name}》</span>
					</p>
					<p>
						价格：￥在售<span name="xiaoji">${cart.opus_price}</span>元
					</p>
					<p>
						<%-- <c:if test="${sessionScope.user.balance<cart.opus_price}">
							<a href="#" onclick="alert('余额不足')">购买</a>
						</c:if>
						<c:if test="${sessionScope.user.balance>cart.opus_price}">
						<a href="order.form?id=${cart.opus_id}&name=${cart.opus_name}&price=${cart.opus_price}" onclick="alert('下单成功，可前往订单页面查看')">购买</a>
						</c:if> --%>
						<a
							href="order.form?id=${cart.opus_id}&name=${cart.opus_name}&price=${cart.opus_price}">购买</a>
						<a href="delete.form?id=${cart.id}">删除</a>
					</p>
				</div>
			</div>

		</c:forEach>
		<div class="count">
			共<span id="shuliang"></span>件商品，已选择<span id="jian">0</span>件 <span
				class="totalprice">总价格：0元</span>
		</div>

		<c:if test="${not empty cartList}">
			<div class="submit">
				<a href="javascript:;" onclick="orderPay()"><button>提交购物车</button>
				</a> <a href="clearCart.form"><button>清空购物车</button> </a>
			</div>
		</c:if>
	</div>
</body>

<script type="text/javascript">
	function display(x) {
		$(".cate-list").css("display", "inline");
	}
	function hid(x) {
		$(".cate-list").css("display", "none");
	}
	$(function() {
		$("#shuliang").html($(".parent_box").length);
	});
	var totalprice= 0;
	function checkAll() {
		if ($("#check").prop("checked")) {
			$("input[name='checkbox']").prop("checked", true);
			totalprice = 0;
			$("span[name='xiaoji']").each(function(i, item) {
				totalprice += Number($(item).html());
			});
		} else {
			$("input[name='checkbox']").prop("checked", false);
			totalprice = 0;
		}
		$("#jian").html($("input[name='checkbox']:checked").length);

		$(".totalprice").html("总价格：" + totalprice + "元");
	}

	function checkObj(obj) {
		if ($(obj).prop("checked") == false) {
			if ($("input[name='checkbox']:checked").length != $(".parent_box").length) {
				$("#check").prop("checked", false);
			}
			var price = $(obj).parent().next().next().find(
					"p span[name='xiaoji']").text()
			totalprice -= Number(price);
			$(".totalprice").html("总价格：" + totalprice + "元");
		}
		if ($(obj).prop("checked") == true) {
			if ($("input[name='checkbox']:checked").length == $(".parent_box").length) {
				$("#check").prop("checked", true);
			}
			var price = $(obj).parent().next().next().find(
					"p span[name='xiaoji']").text()
			totalprice += Number(price);
			$(".totalprice").html("总价格：" + totalprice + "元");
		}
		$("#jian").html($("input[name='checkbox']:checked").length);
	}
	
	function orderPay(){
		layer.confirm('余额支付还是支付宝支付？', {
			  btn: ['余额', '支付宝']
		}, function(){
			wallerPay();	  
		},function(){
			pay();
		});
	}


	
	function wallerPay(){
		
		var orderDatas=[];
		var cartIds=[];
		$("input[name='checkbox']:checked").each(function(i, item) {
			if($(item).attr("id")=="check"){
			}else{
				orderDatas[i] = $(item).parent().next().next().find("p a:FIRST-CHILD").attr("href");
				cartIds[i] = $(item).parent().next().next().find("p a:LAST-CHILD").attr("href");
			}
		});
		
		$.ajax({
			type : "POST",
			dataType : "text",
			url : "orderWaller.form",
			data:{
				"orderDatas":orderDatas,
				"cartIds":cartIds
			},
			traditional : true,
			success : function(data) {
				alert(data);
				window.location.href = 'seekOrder.form';
			}
		});
	}
	

	function pay() {
		var orderDatas=[];
		var cartIds=[];
		$("input[name='checkbox']:checked").each(function(i, item) {
			if($(item).attr("id")=="check"){
			}else{
				orderDatas[i] = $(item).parent().next().next().find("p a:FIRST-CHILD").attr("href");
				cartIds[i] = $(item).parent().next().next().find("p a:LAST-CHILD").attr("href");
			}
		});
		
		var html = "";
		$.ajax({
			type : "POST",
			dataType : "text",
			url : "orderPay.form",
			data:{
				"orderDatas":orderDatas,
				"cartIds":cartIds
			},
			traditional : true,
			success : function(data) {
				var html = "<html><head></heda><body>";
				html += data;
				html += "</body></html>";
				document.write(html);
			},
			error : function(data) {
				if (data.status == 403)
					window.location.href = "login.jsp";
			}
		});
	}
</script>
</html>
