<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>墨韵书院-首页</title>
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/author.css">
<link rel="stylesheet" href="css/basic.css">
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
	<div class="content">

		<div id="box">
			<dl>
				<dt>艺术家分类：</dt>
				<dd>全部</dd>
				<dd>国画</dd>
				<dd>油画</dd>
				<dd>书法</dd>
				<dd>版画</dd>
			</dl>
			<dl>
				<dt>地区：</dt>
				<dd>全部</dd>
				<dd>北京</dd>
				<dd>上海</dd>
				<dd>天津</dd>
				<dd>山东</dd>
				<dd>河北</dd>
				<dd>辽宁</dd>
				<dd>浙江</dd>
				<dd>江西</dd>
				<dd>山西</dd>
				<dd>吉林</dd>
				<dd>安徽</dd>
				<dd>河南</dd>
				<dd>广东</dd>
				<dd>黑龙江</dd>
				<dd>福建</dd>
				<dd>江苏</dd>
				<dd>内蒙古</dd>
				<dd>湖北</dd>
				<dd>四川</dd>
				<dd>云南</dd>
				<dd>甘肃</dd>
				<dd>青海</dd>
				<dd>宁夏</dd>
				<dd>新疆</dd>

			</dl>
			<dl>
				<dt>价格：</dt>
				<dd>1000元以下</dd>
				<dd>2000-3000元</dd>
				<dd>3000-5000元</dd>
				<dd>5000-8000元</dd>
				<dd>8000-10000元</dd>
				<dd>10000元以上</dd>
			</dl>
			<!-- <dl style="border: none">
			<dt>显卡：</dt>
			<dd>独立显卡</dd>
			<dd>集成显卡</dd>
			<dd>核芯显卡</dd>
		</dl> -->
			<dl class="select" style="border-bottom-width: 0px;">
				<dt>已选条件：</dt>
				<!-- <dd>16英寸<span>x</span></dd>
				<dd>核芯显卡<span>x</span></dd> -->
			</dl>
		</div>
	</div>
	<hr
		style="width: 80%; margin: 0px 0px 20px 10%; border: 2hpx solid red;">
	<div class="recommend">
	 
	</div>
	<!-- <div class="author-recommend">
		<h3>艺术家推荐</h3>
		<div class="author">
			<img src="">
		</div>

	</div>
	<div class="opus-display">
		<h3>作品展览</h3>
		<div class="opus"></div>
	</div> -->

</body>

<script>
	function display(x) {
		$(".cate-list").css("display", "inline");
	}
	function hid(x) {
		$(".cate-list").css("display", "none");
	}
</script>
<script>

	var dls = document.querySelectorAll('dl:not(.select)');
	var selected = document.querySelector('.select');

	for (var i = 0; i < dls.length; i++) {
		dls[i].mark = false; //给每一行的dl标签添加一条属性，用于对应下面的dd标签。我们约定如果这个属性的值为true表示对应的标签没有创建。如果值为false表示对应的标签已经创建了
		select(i);
	}

	function select(n) {
		var dds = dls[n].querySelectorAll('dd');
		var prev = null;
		var dd = null; //每一行都需要创建一个dd标签，放到这里是为了如果标签已经被创建了，通过这个变量能够找到这个标签

		for (var i = 0; i < dds.length; i++) {
			dds[i].onclick = function() {
				//给当前点击的添加一个高亮
				//prev && (prev.className = '');

				if (prev) {
					prev.className = ''
				}

				this.className = 'active';

				prev = this;

				//创建dd标签
				var parent = this.parentNode;
				if (!parent.mark) { //这个条件满足说明对应的dd标签还没有创建
					dd = document.createElement('dd');
					dd.innerHTML = this.innerHTML;
					selected.appendChild(dd);

					parent.mark = true;

				} else {
					//走这里的时候说明对应的标签已经创建了，只需要把内容修改了就可以了
					dd.innerHTML = this.innerHTML;
				}

				var span = document.createElement('span');
				var This = this;
				span.innerHTML = 'X';
				span.onclick = function() {
					
					selected.removeChild(dd);
					This.className = '';
					parent.mark = false;
					
					getOpus();
				};
				dd.appendChild(span);
				getOpus();
			};
		}
	}
	
	function getOpus(){
		var opusdata =[];
		$(".select dd").each(function(i,e){
			var text = $(e).text();
			var index = text.indexOf("X");
			text = text.slice(0,index);
			opusdata.push(text);
		});
		
		$.ajax({
			url:"getScreen.form",
			type:"post",
			data:{
				"tipic":opusdata[0],
				"address":opusdata[1],
				"price":opusdata[2]
			},
		    success:function(data){
		    	$(".recommend").html("");
		    	$.each(data,function(i,e){
		    		$(".recommend").append('<div class="opus-box">'+
		    		'<a href="opusDetail.form?id='+e.id+'"><div>'+
                    '<img src="'+e.opus_image+'"><p style="margin-top: 10px;">'+
                    '￥:在售'+e.opus_price+'元</p><p><span>'+
                    e.author_name+'</span>&nbsp;&nbsp;<span>('+e.opus_name+')</span>'+
					'</p></div></a></div>');
		    	});
		    }
		})
	}
</script>
</html>
