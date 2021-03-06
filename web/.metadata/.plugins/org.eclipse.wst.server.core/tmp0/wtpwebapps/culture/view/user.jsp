<%@page import="com.chengtao.entity.WebTip"%>
<%@page import="com.chengtao.entity.UserType"%>
<%@page import="com.chengtao.entity.CompanyUser"%>
<%@page import="com.chengtao.entity.PersonUser"%>
<%@page import="com.chengtao.utils.ParamUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Object user = request.getAttribute(ParamUtils.ENTITY_USER);
	PersonUser personUser = null;
	CompanyUser companyUser = null;
	UserType type = UserType.Visitor;
	if(user != null){
		if(user instanceof PersonUser){
			personUser = (PersonUser)user;
			if(personUser != null){
				type = UserType.PersonUser;
			}
		}else{
			companyUser = (CompanyUser)user;
			if(companyUser != null){
				type = UserType.CompanyUser;
			}
		}
	}
	Object tipObject = request.getAttribute(ParamUtils.ENTITY_WEB_TIP);
	WebTip tip = null;
	if(tipObject != null){
		tip = (WebTip)tipObject;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/font-awesome.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/main.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/javascript/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/javascript/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/javascript/jquery.validate.min.js"></script>
<title>文化创意</title>
</head>
<%
if(tip != null){
	out.println(tip.toString());
}
%>
<body>
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<%=request.getContextPath()%>">文化创意</a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
		        <li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown">管理<span class="caret"></span></a>
		          <ul class="dropdown-menu" role="menu">
		          	<%if(type != UserType.Visitor){%>
		          		<li><a href="<%=request.getContextPath()%><%=ParamUtils.SERVLET_USER_INFO%>">个人信息</a></li>
		          		<li><a href="<%=request.getContextPath()%><%=ParamUtils.SERVLET_USER_LOGOUT%>">注销</a></li>
		          	<%}%>
		          	<%if(type == UserType.Visitor){ %>
		            	<li><a href="<%=request.getContextPath()%><%=ParamUtils.VIEW_LOGIN%>">登录</a></li>
					<%}%>
		          </ul>
		        </li>
		      </ul>
		</div>
	</div>
	</nav>
	<div class="container user-main">
		<h3>个人信息</h3>
		<hr/>
		<div class="row">
			<div class="col-md-8">
			  	<%
			  		int id = 0;
			  		if(type == UserType.PersonUser){
			  			id = personUser.getId();
			  		}else if(type == UserType.CompanyUser){
			  			id = companyUser.getId();
			  		}
			  	%>
			  	<%
			  		int mTpye = -1;
			  		if(type == UserType.PersonUser){
			  			mTpye = 0;
			  		}else if(type == UserType.CompanyUser){
			  			mTpye = 1;
			  		}
			  	%>
			  	<%
					String userModifyPath = request.getContextPath() + ParamUtils.SERVLET_USER_MODIFY; 
			  		userModifyPath += "?" + ParamUtils.FOREM_USER_ID + "=" + id + "&";
			  		userModifyPath += ParamUtils.FOREM_USER_TYPE + "=" + mTpye;
				%>
				<form role="form" id="user-form" method="POST" action="<%=userModifyPath%>">
				  <%
				  	String name = "";
				  	if(type == UserType.PersonUser){
			  			name = personUser.getName();
			  		}else if(type == UserType.CompanyUser){
			  			name = companyUser.getName();
			  		}
				  	if(name == null){
		  				name = "";
		  			}
				  %>
				  <div class="form-group">
				    <label>用户名</label>
				    <input type="text" class="form-control" value="<%=name%>"  id="<%=ParamUtils.FOREM_USER_NAME %>" name="<%=ParamUtils.FOREM_USER_NAME %>" placeholder="请输入用户名">
				  </div>
				  <div class="form-group" id="form-p">
				    <label>密码</label>
				    <input type="password" class="form-control"id="<%=ParamUtils.FOREM_USER_PASSWORD %>" name="<%=ParamUtils.FOREM_USER_PASSWORD %>" placeholder="请输入密码">
				  </div>
				  <div class="form-group" id="form-p-again">
				    <label>确认密码</label>
				    <input type="password" class="form-control"id="<%=ParamUtils.FOREM_USER_PASSWORD_AGAIN %>" name="<%=ParamUtils.FOREM_USER_PASSWORD_AGAIN %>" placeholder="请确认密码">
				  </div>
				  <%
				  	String nickName = "";
				  	if(type == UserType.PersonUser){
				  		nickName = personUser.getNickName();
				  		if(nickName == null){
				  			nickName = "";
				  		}
				  %>
				  <div class="form-group">
				    <label>昵名</label>
				    <input type="text" class="form-control" value="<%=nickName%>" id="<%=ParamUtils.FOREM_USER_NICK_NAME %>" name="<%=ParamUtils.FOREM_USER_NICK_NAME %>" placeholder="请输入昵名">
				  </div>
			  	  <%}%>
				  <%
				  String companyName = "";
				  if(type == UserType.CompanyUser){
					  companyName = companyUser.getCompanyName();
				  	if(companyName == null){
				  		companyName = "";
		  			}
				  %>
				  <div class="form-group">
				    <label>公司名</label>
				    <input type="text" class="form-control" value="<%=companyName%>"  id="<%=ParamUtils.FOREM_USER_COMPANY_NAME %>" name="<%=ParamUtils.FOREM_USER_COMPANY_NAME %>" placeholder="请输入公司名">
				  </div>
				  <%}%>
				  <%
				  String realName = "";
				  if(type == UserType.PersonUser){
					  realName = personUser.getRealName();
				  }else if(type == UserType.CompanyUser){
					  realName = companyUser.getRealName();
				  }
				  if(realName == null){
					  realName = "";
					  
				  }
				  %>
				  <div class="form-group">
				    <label>真实名</label>
				    <input type="text" class="form-control" value="<%=realName%>"  id="<%=ParamUtils.FOREM_USER_REAL_NAME %>" name="<%=ParamUtils.FOREM_USER_REAL_NAME %>" placeholder="请输入真实名">
				  </div>
				  <%
				  String email = "";
				  if(type == UserType.PersonUser){
					  email = personUser.getEmail();
				  }else if(type == UserType.CompanyUser){
					  email = companyUser.getEmail();
				  }
				  if(email == null){
					  email = "";
					  
				  }
				  %>
				  <div class="form-group">
				    <label>邮箱</label>
				    <input type="text" class="form-control" value="<%=email%>"  id="<%=ParamUtils.FOREM_USER_EMAIL %>" name="<%=ParamUtils.FOREM_USER_EMAIL %>" placeholder="请输入邮箱">
				  </div>
				  <%
				  String qq = "";
				  if(type == UserType.PersonUser){
					  qq = personUser.getQQ();
				  }else if(type == UserType.CompanyUser){
					  qq = companyUser.getQQ();
				  }
				  if(qq == null){
					  qq = "";
				  }
				  %>
				  <div class="form-group">
				    <label>QQ</label>
				    <input type="text" class="form-control" value="<%=qq%>"  id="<%=ParamUtils.FOREM_USER_QQ %>" name="<%=ParamUtils.FOREM_USER_QQ %>" placeholder="请输入QQ">
				  </div>
				   <%
				  String tel = "";
				  if(type == UserType.PersonUser){
					  tel = personUser.getTel();
				  }else if(type == UserType.CompanyUser){
					  tel = companyUser.getTel();
				  }
				  if(tel == null){
					  tel = "";
				  }
				  %>
				  <div class="form-group">
				    <label>电话</label>
				    <input type="text" class="form-control" value="<%=tel%>"  id="<%=ParamUtils.FOREM_USER_TEL %>" name="<%=ParamUtils.FOREM_USER_TEL %>" placeholder="请输入电话">
				  </div>
				  <%
				  String address = "";
				  if(type == UserType.PersonUser){
					  address = personUser.getAddress();
				  }else if(type == UserType.CompanyUser){
					  address = companyUser.getAddress();
				  }
				  if(address == null){
					  address = "";
				  }
				  %>
				  <div class="form-group">
				    <label>地址</label>
				    <input type="text" class="form-control" value="<%=address%>"  id="<%=ParamUtils.FOREM_USER_ADDRESS %>" name="<%=ParamUtils.FOREM_USER_ADDRESS %>" placeholder="请输入地址">
				  </div>
				   <%
				  String intro = "";
				  if(type == UserType.PersonUser){
					  intro = personUser.getIntroduce();
				  }else if(type == UserType.CompanyUser){
					  intro = companyUser.getIntroduce();
				  }
				  if(intro == null){
					  intro = "";
				  }
				  %>
				  <div class="form-group">
				    <label>简介</label>
				    <textarea class="form-control" rows="3" id="<%=ParamUtils.FOREM_USER_INTRO %>" name="<%=ParamUtils.FOREM_USER_INTRO %>" placeholder="请输入简介" ><%=intro%></textarea>
				  </div>
				  <button id="btn-form-modify" type="submit" class="btn btn-block btn-success btn-lg">修改</button>
				</form>
			</div>
			<div class="col-md-2 col-md-offset-1">
				<%
				switch(type){
				case Visitor:
					out.println("<img width=\"200px\" src=\""+request.getContextPath()+"/images/ct.jpg\" class=\"img-responsive img-rounded\"/>");
					break;
				case PersonUser:
					if(personUser != null){
						if(personUser.getAvatar() != null){
							out.println("<img width=\"200px\" src=\""+personUser.getAvatar()+ "\""+" class=\"img-responsive img-rounded\"/>");
						}else{
							out.println("<img width=\"200px\" src=\""+request.getContextPath()+ "\""+"/images/ct.jpg\" class=\"img-responsive img-rounded\"/>");
						}
					}
					break;
				case CompanyUser:
					if(companyUser != null){
						if(companyUser.getAvatar() != null){
							out.println("<img width=\"200px\" src=\""+companyUser.getAvatar()+"\""+" class=\"img-responsive img-rounded\"/>");
						}else{
							out.println("<img width=\"200px\" src=\""+request.getContextPath()+"/images/ct.jpg\" class=\"img-responsive img-rounded\"/>");
						}
					}
					break;
				}
				%>
				<br>
				<%
					String avatorUploadPath = request.getContextPath() + ParamUtils.SERVLET_USER_AVATAR; 
					avatorUploadPath += "?" + ParamUtils.FOREM_USER_ID + "=" + id + "&";
					avatorUploadPath += ParamUtils.FOREM_USER_TYPE + "=" + mTpye;
				%>
				<form role="form" method="POST" enctype="multipart/form-data" action="<%=avatorUploadPath%>">
				  <div class="form-group">
				    <input type="file" id="<%=ParamUtils.FOREM_USER_AVATAR %>" name="<%=ParamUtils.FOREM_USER_AVATAR %>">
				  </div>
				  <button type="submit" class="btn btn-info btn-block">上传</button>
				</form>
			</div>
		</div>
	</div>
	<br>
	<br>
	<br>
	<script type="text/javascript"
	src="<%=request.getContextPath()%>/javascript/index.js"></script>
</body>
</html>