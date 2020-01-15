<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>墨韵书院-首页</title>
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/register.css">
<link
	href="//netdna.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"
	rel="stylesheet">
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
	<div class="background">
		<img src="image/background.jpg" width="100%" height="100%" />
	</div>
	<form action="register.form" method="post">
		<div class="top">用户注册</div>
		<p>
			<input type="text" placeholder="手机号" name="tel" id="tel">
		</p>
		<span id="s1"></span>
		<p>
			<input type="password" placeholder="密码" name="pwd" id="pwd">
		</p>
		<span id="s2"></span>
		<p>
			<input type="password" placeholder="确认密码" name="confirePwd"
				id="confirePwd">
		</p>
		<span id="s3"></span>
		<p>
			<input type="submit" value="立即注册" id="btn" />
		</p>
		<p class="tip">
			已有账号？去<a href="login.jsp">登录</a>
		</p>
		<form>

			</div>
</body>
<!-- <script>
/* 判断手机号 */
	 var tel = document.getElementById('tel').value;
	 	$("#tel").blur(function(){
	 		$("s1").add();
		if($("#tel").val().length==0){
			$("#s1").html("手机号不能为空").css("color","red");
		}
		else if(!(/^1(3|4|5|6|7|8|9)\d{9}$/.test(tel))){ 
        	$("#s1").html("手机号格式不正确，请重试").css("color","red");
    	} 	
    	
	})
		
</script>

<script>
/* 判断密码 */
	var pwd=document.getElementById('pwd').value;
	var cpwd=document.getElementById('confirePwd').value;
	$("#pwd").blur(function(){
		if(pwd.length==0){
			$("#s2").html("密码不能为空").css("color","red");
		}
	})
	$("#confirePwd").blur(function(){
		if(pwd!=confirePwd){
			$("#s3").html("两次密码不一致,请重试").css("color","red");
		}
	})

</script> -->
</html>
