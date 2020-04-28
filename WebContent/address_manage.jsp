<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>墨韵书院-首页</title>
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/address_manage.css">
<link rel="stylesheet" href="css/basic.css">
<link rel="stylesheet" href="css/person_basic.css">
<link href="https://netdna.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
					<p><a href="personCenter.jsp">个人资料</a></p>
					<p><a href="safe_setup.jsp">安全设置</a></p>
					<p><a href="address_manage.jsp">收货地址</a></p>
					<p><a href="seekOrder.form">订单管理</a></p>
					<p><a href="">收藏</a></p>
					<p><a href="">评价</a></p>
			</div>
			<div class="content-right">
				<h3>地址管理/Address Manage</h3>
				<hr>
			<div class="Caddress">
				<div class="open_new">
					<button class="open_btn" onclick="modalShow()">添加新地址</button>
				</div>
				
			</div>
			</div>
		</div>
		
		<!--添加收获地址-->
		<div id="tenant-model-box" class="tenant-model">
		<div class="tenant-model-content">
			<header class="tenant-model-header">
			<h4
				style="margin-left: 20px; padding: 5px; font-weight: bold; height: 30px; line-height: 30px;">收获地址:</h4>
			<span id="closeModel">×</span> </header>
			<div class="tenant-model-body">
				<form action="" method="post" id="addressForm">
					<label>收货人</label><p><input type="text" name="name"></p>
					<label>手机号码</label><p><input type="tel" name="phone"></p>
					<label>所在地</label>
						<p>
							<select name="province" id="province">
								<option value="请选择">请选择</option>
							</select>
							<select name="city" id="city">
								<option value="请选择">请选择</option>
							</select>
							<select name="town" id="town">
								<option value="请选择">请选择</option>
							</select>
						</p>	
					<label>详细地址</label><p><textarea  name="area"></textarea></p>
					<input type="submit" value="添加"/>
				</form>
			</div>
		</div>
	</div>
	
</body>

<script>
	 
	 $("#order").click(function(){
	 	$("form").css("display","inline");
	 });
	 
	 function modalShow() {
		 $("#addressForm").attr("action","addUserAddress.form");
		 $(".tenant-model-body input[type='submit']").val("添加");
			$("#tenant-model-box").show();
			$("#closeModel").click(function() {
				$("#tenant-model-box").hide();
				document.getElementById("addressForm").reset();
				$("#tenant-model-box select option:first-child").text("请选择");
				$("#tenant-model-box select option:first-child").val("");
				$("#tenant-model-box textarea").text("");
			});
		}
	 $(function(){
		 
		$.ajax({
			url:"getAddress.form",
			type:"post",
			success:function(data){   
				$.each(data,function(i,e){
				$(".content-right .Caddress").append('<div class="add_mi">'+
						'<p style="border-bottom: 1px dashed #ccc; line-height: 28px;">'+
						'<span>'+e.province+'</span><span>'+e.city+'</span>(<span>'+e.name+'</span>收)</p>'+
						'<p><span>'+e.town+'</span>'+' '+'<span>'+e.area+'</span>'+' '+'<span>'+e.phone+'</span></p>'+
						'<a href="javascript:;" class="delete">删除</a>'+
						'<a href="javascript:;" class="modify">编辑</a>'+
						'<input type="hidden" name="id" value="'+e.id+'"></div>');
				});
			}
		})
		
		$(".content-right").on("click",".modify",function(){
			$(".tenant-model-body form").append('<input type="hidden" name="id">');
			var obj1=$(this).prev().prev();
			var obj2=$(this).prev().prev().prev();
			$(".tenant-model-body textarea").text(obj1.find("span").eq(1).text());
			var id = $(this).next().val();
			$(".tenant-model-body input[name='id']").val(id);
			var province = obj2.find("span").eq(0).text();
			var city = obj2.find("span").eq(1).text();
			var town = obj1.find("span").eq(0).text();
			$("#province option:first-child").text(province);
			$("#province option:first-child").val(province);
			$("#city option:first-child").text(city);
			$("#city option:first-child").val(city);
			$("#town option:first-child").text(town);
			$("#town option:first-child").val(town);
			var phone = obj1.find("span").eq(2).text();
			$(".tenant-model-body input[name='phone']").val(phone);
			var name = obj2.find("span").eq(2).text();
			$(".tenant-model-body input[name='name']").val(name);
			
			$("#addressForm").attr("action","modifyAddress.form");
			$(".tenant-model-body input[type='submit']").val("修改");
			$("#tenant-model-box").show();
			$("#closeModel").click(function() {
				$("#tenant-model-box").hide();
				document.getElementById("addressForm").reset();
				$("#tenant-model-box select option:first-child").text("请选择");
				$("#tenant-model-box select option:first-child").val("");
				$("#tenant-model-box textarea").text("");
			});
		});
		$(".content-right").on("click",".delete",function(){
			var obj = $(this).parent();
			$.ajax({
				url:"deleteAddress.form",
				type:"post",
				data:{
					"id":obj.find("input[name='id']").val()
				},
				success:function(){
					obj.remove();
				},
				error:function(){
					obj.remove();
				}
			});
			
		});
	 });
</script>
<script type="text/javascript" src="js/area.js" ></script>
	<script type="text/javascript" src="js/select.js" ></script>
</html>
