<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>墨韵书院-首页</title>
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/homepage.css">
<link rel="stylesheet" href="css/basic.css">
<link
	href="https://netdna.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"
	rel="stylesheet">
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" href="js/echarts.min.js"></script>
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
						ng-click="entity={}">余额充值</a> <!-- <a href="seekOrder.form">我的订单</a> --> <a
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
	
	<div class="menu">
		<ul>
			<li class="dropdown" id="type"><span style="font-size: 20px;">艺术品分类</span>
				<div class="dropdown-content" id="art-type">
					<a href="search.form?search=国画">国画</a> 
					<a href="search.form?search=油画">油画</a> 
					<a href="search.form?search=书法">书法</a>
					<a href="search.form?search=版画">版画</a>
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
					<a href="#">网站简介</a> <a href="#">联系我们</a> <a href="#">友情链接</a>
				</div></li>
		</ul>
	</div>
	
	<div class="page-container">
		<div class="f-14 c-error"></div>
		<div id="container" style="min-width: 700px; height: 400px"></div>
	</div>
	
	<script type="text/javascript"
		src="backstage/lib/hcharts/Highcharts/5.0.6/js/highcharts.js"></script>
	
	<script type="text/javascript">
		$(function() {
			var like = new Array();
			var name = new Array();
			$.ajax({
				type : 'get',
				url : 'getAuthorLike.form',
				dataType : 'json',
				success : function(data) {
					$.each(data, function(i, e) {
						name.push(e.author_name);
						like.push(e.status);
					});					
					$('#container').highcharts(
							{
								chart : {
									type : 'column'
								},
								title : {
									text : '名人排行榜'
								},
								subtitle : {
									text : 'Source: 墨韵书院'
								},
								xAxis : {
									categories : name
								},
								yAxis : {
									min : 0,
									max : Math.max.apply(null, like),
									splitNumber : 5,
									title : {
										text : '热度'
									}
								},
								tooltip : {
									headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
									pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>'
											+ '<td style="padding:0"><b>{point.y} </b></td></tr>',
									footerFormat : '</table>',
									shared : true,
									useHTML : true
								},
								plotOptions : {
									column : {
										pointPadding : 0.2,
										borderWidth : 0
									}
								},
								series : [
										{
											name : 'number',
											data : like

										} ]
							});
				
				},
				error : function(data) {
				}
			});

			

		});
	</script>
</body>
</html>