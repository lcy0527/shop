<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head lang="en">
<meta charset="UTF-8">
<title>登录</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link rel="stylesheet" href="../AmazeUI-2.4.2/assets/css/amazeui.css" />
<link href="../css/dlstyle.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="../css/verify.css">
<style type="text/css">
form label {
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 100%;
	font: inherit;
	vertical-align: baseline;
	font-family: arial, "Lantinghei SC", "Microsoft Yahei";
	position: absolute;
	display: block;
	width: 40px;
	height: 40px;
	line-height: 40px;
	background: #fff;
	text-align: center;
	top: 1px;
	left: 1px
}
</style>
</head>

<body>

	<div class="login-boxtitle">
		<a href="home.html"><img alt="logo" src="../images/logobig.png" />
		</a>
	</div>

	<div class="login-banner">
		<div class="login-main">
			<div class="login-banner-bg">
				<span></span><img src="../images/big.jpg" />
			</div>
			<div class="login-box">

				<h3 class="title">登录商城</h3>

				<div class="clear"></div>
				<%
					String userName = "";
					String userPwd = "";
					Cookie[] cookies = request.getCookies();
					if (cookies != null) {
						for (int i = 0; i < cookies.length; i++) {
							if ("userName".equals(cookies[i].getName())) {
								userName = cookies[i].getValue();
							}
							if ("userPwd".equals(cookies[i].getName())) {
								userPwd = cookies[i].getValue();
							}
						}
					}
				%>
				<div class="login-form">
					<form method="post" action="../../login" id="loginForm">
						<div class="user-name">
							<label for="user"><i class="am-icon-user"></i> </label> <input
								type="text" name="userName" id="user" value="<%=userName%>" placeholder="邮箱/手机/用户名">
						</div>

						<div class="user-pass">
							<label for="password"><i class="am-icon-lock"></i> </label> <input
								type="password" name="passPwd" id="password" value="<%=userPwd%>" placeholder="请输入密码">
						</div>
				</div>

				<div class="login-links">
					<label for="remember-me"><input id="remember-me"
						type="checkbox" name="cheName">记住密码</label> <a href="#" class="am-fr">忘记密码</a> <a
						href="register.jsp" class="zcnext am-fr am-btn-default">注册</a> <br />
				</div>
				<div class="am-cf">
					<input type="button" name="" value="登 录"
						class="am-btn am-btn-primary am-btn-sm" onclick="clicks()">
				</div>
				</form>



				<div id="mpanel4" style="margin-top:50px;display: none;"></div>





				<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
				<script type="text/javascript" src="../js/verify.js"></script>
				<script type="text/javascript">
					function clicks() {
						$("#mpanel4").css("display", "block");
					}

					$('#mpanel4').slideVerify({
						type : 2, //类型
						vOffset : 5, //误差量，根据需求自行调整
						vSpace : 5, //间隔
						imgName : [ 'tupian1.jpg', 'tupian2.jpg' ],
						imgSize : {
							width : '400px',
							height : '200px',
						},
						blockSize : {
							width : '40px',
							height : '40px',
						},
						barSize : {
							width : '400px',
							height : '40px',
						},
						ready : function() {
						},
						success : function() {
							$("#loginForm").submit();
						},
						error : function() {
							//		        	alert('验证失败！');
						}

					});
				</script>



				<div class="partner">
					<h3>合作账号</h3>
					<div class="am-btn-group">
						<li><a href="#"><i class="am-icon-qq am-icon-sm"></i><span>QQ登录</span>
						</a></li>
						<li><a href="#"><i class="am-icon-weibo am-icon-sm"></i><span>微博登录</span>
						</a></li>
						<li><a href="#"><i class="am-icon-weixin am-icon-sm"></i><span>微信登录</span>
						</a></li>
					</div>
				</div>

			</div>
		</div>
	</div>


	<div class="footer ">
		<div class="footer-hd ">
			<p>
				<a href="# ">恒望科技</a> <b>|</b> <a href="# ">商城首页</a> <b>|</b> <a
					href="# ">支付宝</a> <b>|</b> <a href="# ">物流</a>
			</p>
		</div>
		<div class="footer-bd ">
			<p>
				<a href="# ">关于恒望</a> <a href="# ">合作伙伴</a> <a href="# ">联系我们</a> <a
					href="# ">网站地图</a> <em>© 2015-2025 Hengwang.com 版权所有. 更多模板 <a
					href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a>
					- Collect from <a href="http://www.cssmoban.com/" title="网页模板"
					target="_blank">网页模板</a> </em>
			</p>
		</div>
	</div>
</body>

</html>