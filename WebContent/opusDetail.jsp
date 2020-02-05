<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>墨韵书院-作品详情页</title>
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/basic.css">
<link rel="stylesheet" href="css/opusDetail.css">
<link
	href="https://netdna.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"
	rel="stylesheet">
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<style type="text/css">
.magnifier{
width: 200px;
height: 200px;
position: absolute;
left:0;
top: 0;
background: url('images/scope.png') repeat;
display: none;
cursor: move;
}
.big-img{
width: 400px;
height: 400px;
/* ${opus.opus_image} */
background: url('images/1.png') no-repeat ;
background-size:800px 700px;
position: absolute;
left:410px;
top:0;
display: none;
}
</style>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">	
	$(function(){
	//当鼠标移上时显示放大镜和大图
	$('detail-left').mouseenter(function(){
	$('.magnifier,.big-img').show();
	$(this).mousemove(function(e){
		
	var m_left = e.pageX - $(this).offset().left - $('.magnifier').width()/2;
	var m_top = e.pageY - $(this).offset().top - $('.magnifier').height()/2;
	//边界值判断

	//判断左右边界
	if(m_left < 0){
	m_left = 0;
	}else if(m_left > $('.wrap_con').width() - $('.magnifier').width()){
	m_left = $('.wrap_con').width() - $('.magnifier').width();
	}
	//判断上下边界
	if(m_top < 0){
	m_top = 0;
	}else if(m_top > $('.wrap_con').height() - $('.magnifier').height()){
	m_top = $('.wrap_con').height() - $('.magnifier').height();
	}

	$('.magnifier').css({left:m_left,top:m_top});

	//处理大图，也就是放大镜放大后的图
	/*
	整体的思路为：当放大镜移动的时候让大图作为背景图移动，所以此处有一个等比缩放，而此处正好大图的宽度为700，小图的宽度为350.所以放大镜的宽度为:放大后的宽度 * 小图的宽度 / 大图的宽度即400 * 350 / 700 = 200
	此处的宽和高时有讲究的，需要满足条件：
	小图的宽度:大图的宽度 = 放大镜的left:放大后的left
	*/
	var d_w = 700 * m_left / 350;
	var d_h = 700 * m_top / 350;
	$('.big-img').css('background-position','-'+d_w+'px -'+d_h+'px');
	});
	}).mouseleave(function(){
	$('.magnifier,.big-img').hide();
	});
	})
	</script>
	
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
					<a href="">类型</a> <a href="">题材</a> <a href="">地域</a>
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



	<div class="row">
		<div class="detail">
			<div class="detail-left">
				<img src="${opus.opus_image}">
				<div class="magnifier"></div>
				<div class="big-img"></div>
				 <a
					href="like_times.form?id=${opus.id}&like_times=${opus.opus_like_times+1}"
					onclick="alert('操作成功,当前点赞数为：${opus.opus_like_times+1}')"><button>点赞</button>
				</a> <a href="#" href="javascript:;" onclick="modalShow()"><button>分享</button>
				</a>
			</div>
			<div class="detail-right">
				<p>作品名称：${opus.opus_name}</p>
				<p>作品作者：${opus.author_name}</p>
				<p>作品类型：${opus.opus_tipic}</p>
				<p>创建时间：${opus.opus_create_time }</p>
				<p>作品简介：${opus.opus_synopsis }</p>
				<br>
				<p>
					<span>点赞数：${opus.opus_like_times }</span><span
						style="margin-left: 30px;">热度：${opus.opus_heat }</span>
				</p>
				<br>
				<p>
					<font color="red" size="5">价格：￥${opus.opus_price}元</font>
				</p>
				<p>
					<%-- <c:if test="${sessionScope.user==null}">
						<a href="login.jsp"><button style="background-color: red;">立即购买</button></a>
						</c:if>
						<c:if test="${sessionScope.user!=null&&sessionScope.user.balance<cart.opus_price}">
							
							<a href="#" onclick="alert('余额不足')"><button style="background-color: red;">立即购买</button></a>
						</c:if>	
						<c:if test="${sessionScope.user!=null&&sessionScope.user.balance>cart.opus_price}">
						<a href="order.form?id=${opus.id}&name=${opus.opus_name}&price=${opus.opus_price}" onclick="alert('下单成功，可前往订单页面查看')">购买</a>
						</c:if> --%>
					<a href=""><button>立即购买</button></a> <a
						href="cart.form?id=${opus.id}" onclick="alert('操作成功，可前往购物车查看')"><button>加入购物车</button>
					</a>
				</p>
			</div>

		</div>

	</div>


	<!-- 作品介绍 -->
	<div class="row">
		<div class="introduction">
			<div class="box">
				<div class="box-title">作品介绍</div>
			</div>
			<div class="intro-author">
				<h4>作者简介</h4>
				<p>${opus.opus_synopsis }</p>

			</div>
			<!-- <div class="intro-opus">作品介绍</div> -->
			<div class="fingerpost">
				<p>艺术品保养指南</p>
				<p>1、保持展示环境安全清洁、温度湿度均衡，避免阳光直射；</p>
				<p>2、预防外力碰撞损坏，切勿叠压码放；</p>
				<p>3、建议使用专用清洁品及时除尘，避免腐蚀性化学液体接触艺术品。</p>
			</div>

		</div>

	</div>
	<div class="row">

		<div class="discuss">
			<p>
				<font size="3">作品评论</font>
			</p>
			<form action="discuss.form" method="post">
				<textarea name="discussMsg"></textarea>
				<input type="hidden" name="id" value="${opus.id}"> <input
					type="hidden" name="name" value="${opus.opus_name}"> <br>
				<input type="submit" value="评论">
			</form>

			<p style="margin: 100px 0px 0px 10%;">评论区：</p>
			<div class="under">

				<c:forEach items="${disList}" var="discu">
					<p>${discu.discuss_date}</p>
					<p>用户${discu.user_name}: ${discu.discuss_msg}</p>
				</c:forEach>


			</div>

		</div>
	</div>
	</div>

	<!-- 分享功能模态框 -->
	<div id="tenant-model-box" class="tenant-model">
		<div class="tenant-model-content">
			<header class="tenant-model-header">
			<h4
				style="margin-left: 20px; padding: 5px; font-weight: bold; height: 30px; line-height: 30px;">分享到:</h4>
			<span id="closeModel">×</span> </header>
			<div class="tenant-model-body">
				<div class="fl" onclick="shareTo('qzone')">
					<img src="http://zixuephp.net/static/images/qqzoneshare.png"
						width="30">
				</div>
				<div class="fl" onclick="shareTo('qq')">
					<img src="http://zixuephp.net/static/images/qqshare.png" width="32">
				</div>
				<div class="fl" onclick="shareTo('sina')">
					<img src="http://zixuephp.net/static/images/sinaweiboshare.png"
						width="36">
				</div>
				<div class="fl" onclick="shareTo('wechat')">
					<img src="http://zixuephp.net/static/images/wechatshare.png"
						width="32">
				</div>
			</div>
		</div>
	</div>


</body>

<script>
	function display(x) {
		$(".cate-list").css("display", "inline");
	}
	function hid(x) {
		$(".cate-list").css("display", "none");
	}
	function modalShow() {
		$("#tenant-model-box").show();
		$("#closeModel").click(function() {
			$("#searchTenant").val("");
			$("#divSelectLi").empty();
			$("#tenant-model-box").hide();
		});
	}

	function shareTo(stype) {
		var ftit = '';
		var flink = '';
		var lk = '';
		var content = '';
		//获取文章标题
		ftit = $('.detail-right p:FIRST-CHILD').text();
		//获取书画信息
		content = $('.detail-right p:NTH-CHILD(5)').text();
		//获取网页中内容的第一张图片
		flink = window.location.host + "/ssm_business/"
				+ $('.detail-left img').eq(0).attr('src');
		if (typeof flink == 'undefined') {
			flink = '';
		}
		//当内容中没有图片时，设置分享图片为网站logo
		if (flink == '') {
			lk = 'http://' + window.location.host + '/static/images/logo.png';
		}
		//如果是上传的图片则进行绝对路径拼接
		if (flink.indexOf('/uploads/') != -1) {
			lk = 'http://' + window.location.host + flink;
		}
		//百度编辑器自带图片获取
		if (flink.indexOf('ueditor') != -1) {
			lk = flink;
		}
		console.log(lk + "ssssssssss");
		//qq空间接口的传参
		if (stype == 'qzone') {
			window
					.open('https://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url='
							+ document.location.href
							+ '?sharesource=qzone&title='
							+ ftit
							+ '&pics='
							+ flink + '&summary=' + content);
		}
		//新浪微博接口的传参
		if (stype == 'sina') {
			window.open('http://service.weibo.com/share/share.php?url='
					+ document.location.href + '?sharesource=weibo&title='
					+ ftit + '&pic=' + flink + '&appkey=2706825840');
		}
		//qq好友接口的传参
		if (stype == 'qq') {
			window.open('http://connect.qq.com/widget/shareqq/index.html?url='
					+ document.location.href + '?sharesource=qzone&title='
					+ ftit + '&pics=' + flink + '&summary=' + content);
		}
		//生成二维码给微信扫描分享
		if (stype == 'wechat') {
			window
					.open('http://zixuephp.net/inc/qrcode_img.php?url='+document.location.href);
					
						
		}
	}
</script>


</html>
