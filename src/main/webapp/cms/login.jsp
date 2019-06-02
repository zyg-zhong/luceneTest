<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>持名法州后台管理中心</title>
			
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
	<link rel="icon" href="img/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="${pageContext.request.contextPath }/cms/css/common.css" type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/cms/css/login.css" type="text/css"></link>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/cms/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/cms/themes/IconExtension.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/cms/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/cms/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/cms/js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
	
		$(function(){
			//验证码：
		    //点击图片刷新
			$("#captchaImage").click(function(){
				$(this).attr("src","${pageContext.request.contextPath}/admin/getcode?d="+new Date().getTime());
			});
			//初始化用户名文本框
			$("#tusernameinput").textbox({
				required:true,
			});
			//初始化密码文本框
			$("#tthpasswordinput").textbox({
				required:true,
				type:"password",
			});
			//初始化验证码文本框
			$("#tthenCode").textbox({
				required:true,
			});
			//初始化登录按钮
			$("#loginButton").linkbutton({
				onClick:function(){
					$("#loginForm").form("submit",{
						url:"${pageContext.request.contextPath}/admin/adminLogin",
						onSubmit:function(){
		    				
	    				},
						success:function(data){
							var obj=JSON.parse(data);
							alert(obj.message);
							if(obj.message=="登录成功！"){
								location.href="${pageContext.request.contextPath }/cms/main/main.jsp"
							}
							
						}
			});
				}
			});
			
		});
	</script>
</head>
<body>
	
		<div class="login">
			<form id="loginForm" method="post" >
				
				<table>
					<tbody>
						<tr>
							<td width="190" rowspan="2" align="center" valign="bottom">
								<img src="img/header_logo.gif" />
							</td>
							<th>
								用户名:
							</th>
							<td>
								<input id="tusernameinput"  name="username" class="text"  maxlength="20"/>
							</td>
					  </tr>
					  <tr>
							<th>
								密&nbsp;&nbsp;&nbsp;码:
							</th>
							<td>
								<input id="tthpasswordinput"  name="password" class="text" maxlength="20" />
							</td>
					  </tr>
					
						<tr>
							<td>&nbsp;</td>
							<th>验证码:</th>
							<td>
								<input id="tthenCode"  name="code" class="text captcha" maxlength="4" autocomplete="off"/>
								<img id="captchaImage" class="captchaImage" src="${pageContext.request.contextPath}/admin/getcode" title="点击更换验证码"/>
							</td>
						</tr>					
					<tr>
						<td>
							&nbsp;
						</td>
						<th>
							&nbsp;
						</th>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<th>&nbsp;</th>
						<td>
							<input type="button" class="homeButton" value="" onclick="location.href='${pageContext.request.contextPath }/main/main.jsp'"><a id="loginButton" >登录</a>
						</td>
					</tr>
				</tbody></table>
				<div class="powered">COPYRIGHT © 2008-2017.</div>
				<div class="link">
					<a href="http://www.chimingfowang.com/">持名佛网首页</a> |
					<a href="http://www.chimingbbs.com/">交流论坛</a> |
					<a href="">关于我们</a> |
					<a href="">联系我们</a> |
					<a href="">授权查询</a>
				</div>
			</form>
		</div>
</body>
</html>