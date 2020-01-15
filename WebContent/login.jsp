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
	<form action="login.form" method="post">
		<div class="top">用户登录</div>
		<p>
			<input type="text" placeholder="手机号" name="tel" />
		</p>
		<p>
			<input type="password" placeholder="密码" name="pwd" />
		</p>
		<p>
			<input type="submit" value="登录" id="btn" />
		</p>
		<p class="tip">
			还未注册？去<a href="register.jsp">注册</a>
		</p>
	</form>

	</div>
	<c:if test="${error!=null}">
		<script type="text/javascript">
alert("账号或者密码错误!");
</script>
	</c:if>
</body>
</html>
