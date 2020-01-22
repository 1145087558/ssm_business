<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>墨韵书院-首页</title>
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!-- <link rel="stylesheet" href="css/register.css"> -->
<link
	href="https://netdna.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/amazeui.css">
<link rel="stylesheet" href="css/amazeui.min.css">
<link rel="stylesheet" href="css/dlstyle.css">

<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="js/amazeui.min.js"></script>
<script type="text/javascript" src="js/1.14.0/jquery.validate.js"></script>
</head>

<body>
	<div class="login-boxtitle">
		<!--<a href="home/demo.html"><img alt="" src="img/logo1.jpg" /></a>-->
	</div>

	<div class="res-banner">
		<div class="res-main">
			<div class="login-banner-bg">
				<span></span><img src="image/login-background.jpg" />
			</div>
			<div class="login-box">

				<div class="am-tabs" id="doc-my-tabs">
					<ul class="am-tabs-nav am-nav am-nav-tabs am-nav-justify">
						<li class="am-active"><a href="">邮箱注册</a></li>
						<li><a href="">手机号注册</a></li>
					</ul>

					<div class="am-tabs-bd">
						<div class="am-tab-panel am-active">
							<form method="post" action="emailRegister.form" id="emailForm">

								<div class="user-email">
									<label for="email"><i class="am-icon-envelope-o"></i></label> <input
										type="email" name="email" id="email" placeholder="请输入邮箱账号">
								</div>
								<div class="user-pass">
									<label for="password"><i class="am-icon-lock"></i></label> <input
										type="password" name="pwd" id="password" placeholder="设置密码">
								</div>
								<div class="user-pass">
									<label for="passwordRepeat"><i class="am-icon-lock"></i></label>
									<input type="password" name="" id="passwordRepeat"
										placeholder="确认密码">
								</div>

								<div class="am-cf">
									<input type="submit" name="" value="注册"
										class="am-btn am-btn-primary am-btn-sm am-fl">
								</div>

							</form>

							<div class="login-links">
								<label id="emailError"></label>
								<!-- <label for="reader-me"> <input id="reader-me"
									type="checkbox"> 点击表示您同意商城《服务协议》
								</label> -->
							</div>
						</div>

						<div class="am-tab-panel">
							<form method="post" action="telRegister.form" id="telForm">
								<div class="user-phone">
									<label for="phone"><i
										class="am-icon-mobile-phone am-icon-md"></i></label> <input type="tel"
										name="tel" id="tel" placeholder="请输入手机号">
								</div>
								<div class="verification">
									<label for="code"><i class="am-icon-code-fork"></i></label> <input
										type="tel" name="code" id="code" placeholder="请输入验证码">
									<a class="btn" href="javascript:void(0);"
										onclick="sendMobileCode();" id="sendMobileCode"> <span
										id="dyMobileButton">获取</span></a>
								</div>
								<div class="user-pass">
									<label for="password"><i class="am-icon-lock"></i></label> <input
										type="password" name="pwd" id="pwd" placeholder="设置密码">
								</div>
								<div class="user-pass">
									<label for="passwordRepeat"><i class="am-icon-lock"></i></label>
									<input type="password" name="" id="pwdRepeat"
										placeholder="确认密码">
								</div>


								<div class="am-cf">
									<input type="submit" name="" value="注册"
										class="am-btn am-btn-primary am-btn-sm am-fl">
								</div>
							</form>

							<div class="login-links">
								<label id="telError"></label>
								<!-- <label for="reader-me"> <input id="reader-me"
									type="checkbox"> 点击表示您同意商城《服务协议》
								</label> -->

							</div>
						</div>

						<script>
							$(function() {
								$('#doc-my-tabs').tabs();

							})
						</script>

					</div>
				</div>

			</div>
		</div>
		<script type="text/javascript">
			function sendMobileCode() {
				$.ajax({
					url : "sendPhoneCode.form",
					type : "post",
					data : {
						"tel" : $("#tel").val()
					},
					success : function() {

					}
				});
			}

			$(function() {
				$("#emailForm").validate({
					rules : {
						password : {
							required : true,
							minlength : 4
						},
						passwordRepeat : {
							required : true,
							equalTo : "#password"
						},
						email : {
							required : true,
							email : true,
							remote : {
								url : "checkEmail.form",
								type : "post",
								dataType : "json",
								data : {
									email : function() {
										return $("#email").val();
									}
								}
							}
						}
					},
					messages : {
						password : {
							required : "请输入密码",
							minlength : "密码至少4位"
						},
						passwordRepeat : {
							required : "确认密码是必填的",
							equalTo : "确认密码与密码不同"
						},
						email : {
							email : "请输入一个正确的邮箱",
							required : "请输入邮箱",
							remote : "邮箱已被注册"
						}
					},
					errorPlacement : function(error, element) {
						$("#emailError").html($(error).text());
					},
					submitHandler : function(form) {
						form.submit();
					}
				});

				$("#telForm").validate({
					rules : {
						pwd : {
							required : true,
							minlength : 4
						},
						pwdRepeat : {
							required : true,
							equalTo : "#password"
						},
						tel : {
							required : true,
							isMobile : true,
							remote : {
								url : "checkPhone.form",
								type : "post",
								dataType : "json",
								data : {
									tel : function() {
										return $("#tel").val();
									}
								}
							}
						},
						code : {
							required : true,
							rangelength : [ 6, 6 ]
						}
					},
					messages : {
						pwd : {
							required : "请输入密码",
							minlength : "密码至少4位"
						},
						pwdRepeat : {
							required : "确认密码是必填的",
							equalTo : "确认密码与密码不同"
						},
						tel : {
							required : "请输入手机号",
							remote : "手机号已被注册"
						},
						code : {
							required : "请输入验证码",
							rangelength : "验证码格式不对"
						}
					},
					errorPlacement : function(error, element) {
						$("#telError").html($(error).text());
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
		</script>

		<c:if test="${error!=null}">
			<script type="text/javascript">
				$("#telError").html('${error}');
			</script>
		</c:if>
</body>

</html>
