<%@page import="com.chengtao.entity.WebTip"%>
<%@page import="com.chengtao.utils.ParamUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/javascript/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/javascript/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/javascript/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/javascript/main.js"></script>
<title>文化创意</title>
</head>
<%
	WebTip webTip = (WebTip)request.getAttribute(ParamUtils.ENTITY_WEB_TIP);
	if(webTip != null){
		out.print(webTip.toString());
		if(webTip.isState()){
			response.sendRedirect(request.getContextPath()+ParamUtils.SERVLET_ACTIVITY);
		}
	}
%>
<body>
	<div class="container-fluid container-bg">
		<div class="row row-bg">
			<div class="col-md-6 big-image">
				<img class="img-responsive"
					src="<%=request.getContextPath()%>/images/bg.jpg" alt="">
			</div>
			<div class="col-md-6 scholar-form">
				<h3>登录</h3>
				<hr>
				<form role="form" method="POST"
					action="<%=request.getContextPath()%><%=ParamUtils.SERVLET_USER_LOGIN%>"
					id="login">
					<div class="form-group">
						<label>用户名</label> <input type="text" class="form-control"
							placeholder="请输入用户名" name="<%=ParamUtils.ATTR_USER_NAME%>">
					</div>
					<div class="form-group">
						<label>密码</label> <input type="password" class="form-control"
							placeholder="请输入密码" name="<%=ParamUtils.ATTR_USER_PASSWORD%>">
					</div>
					<div class="form-grop">
						<a href="<%=request.getContextPath()%><%=ParamUtils.VIEW_SIGN_UP%>">点击注册</a>
					</div>
					<button type="submit" class="btn btn-success btn-block">登录</button>
				</form>
			</div>
		</div>
	</div>
	<div id="loginModal" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel" aria-hidden="true"
		class="modal fade bs-example-modal-sm">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" data-dismiss="modal" class="close">
						<span aria-hidden="true">×</span><span class="sr-only">Close</span>
					</button>
					<h4 id="mySmallModalLabel" class="modal-title"></h4>
				</div>
				<div class="modal-body">
					<a data-dismiss="modal" role="button" class="btn btn-success left">重新登录</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>