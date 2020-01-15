<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link href="static/h-ui/css/H-ui.min.css" rel="stylesheet"
	type="text/css" />
<link href="static/h-ui.admin/css/H-ui.login.css" rel="stylesheet"
	type="text/css" />
<link href="static/h-ui.admin/css/style.css" rel="stylesheet"
	type="text/css" />
<link href="lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet"
	type="text/css" />
<title>后台登录 - 墨韵书院</title>
</head>
<body>
	<input type="hidden" id="TenantId" name="TenantId" value="" />
	<div class="header"></div>
	<div class="loginWraper">
		<div id="" class="loginBox">
			<form class="form form-horizontal" action="backstageLogin.form"
				method="post" id="loginForm">
				<div class="row cl">
					<label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
					<div class="formControls col-xs-8">
						<input id="tel" name="tel" type="text" placeholder="手机号"
							class="input-text size-L">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
					<div class="formControls col-xs-8">
						<input id="pwd" name="pwd" type="password" placeholder="密码"
							class="input-text size-L">
					</div>
				</div>
				<div class="row cl">
					<div class="formControls col-xs-8 col-xs-offset-3">
						<input class="input-text size-L" id="code" name="code" type="text"
							placeholder="验证码" value="" style="width: 150px;"> <img
							src="drawImage.form" id="find"> <a id="kanbuq"
							href="javascript:;" onclick="changeImg()">看不清，换一张</a>
					</div>
				</div>
				<div class="row cl">
					<div class="formControls col-xs-8 col-xs-offset-3">
						<label id="online"></label>
					</div>
				</div>
				<div class="row cl">
					<div class="formControls col-xs-8 col-xs-offset-3">
						<input name="" type="submit" class="btn btn-success radius size-L"
							value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;"> <input
							name="" type="reset" class="btn btn-default radius size-L"
							value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="footer">Copyright 墨韵书院</div>
	<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript" src="../js/1.14.0/jquery.validate.js"></script>
	<script type="text/javascript">
		$("#find").click(function() {
			this.src = "drawImage.form?nocache=" + new Date().getTime();
		});
		function changeImg() {
			$("#find").attr("src",
					"drawImage.form?nocache=" + new Date().getTime());
		}
		$(function() {
			$("#loginForm").validate({
				rules : {
					tel : {
						required : true
					},
					code : {
						required : true,
						remote : {
							url : "checkCode.form",
							type : "post",
							dataType : "json",
							data : {
								signcode : function() {
									return $("#code").val();
								}
							}
						}
					},
					pwd : {
						required : true
					}
				},
				messages : {
					tel : {
						required : "请输入账号"
					},
					pwd : {
						required : "请输入密码"
					},
					code : {
						required : "请输入验证码",
						remote : "验证码错误"
					}
				},
				errorPlacement : function(error, element) {
					$("#online").html($(error).text());
				},
				submitHandler : function(loginForm) {
					loginForm.submit();
				}
			});

		});
	</script>
	<c:if test="${error!=null}">
		<script type="text/javascript">
		$("#online").html('${error}');
		</script>
	</c:if>
</body>
</html>