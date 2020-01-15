<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>墨韵书院-首页</title>
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/homepage.css">
<link rel="stylesheet" href="css/basic.css">
<link
	href="//netdna.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"
	rel="stylesheet">
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>

	<button type="button" class="btn btn-default" title="新建"
		data-toggle="modal" data-target="#editModal" ng-click="entity={}">新建</button>

	<!-- 编辑窗口 -->
	<div class="modal fade" id="editModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form>
					<label></label> <input type="text">
				</form>

				<button class="btn btn-success" data-dismiss="modal"
					aria-hidden="true" ng-click="save()">保存</button>
				<button class="btn btn-default" data-dismiss="modal"
					aria-hidden="true">关闭</button>

			</div>
		</div>
	</div>



</body>


</html>
