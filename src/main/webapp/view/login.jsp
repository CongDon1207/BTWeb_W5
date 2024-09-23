<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "jakarta.tags.core" %>
<%@ taglib prefix ="fmt" uri = "jakarta.tags.fmt"%>
<%@ taglib prefix = "fn" uri = "jakarta.tags.functions" %>

<!-- BEGIN BODY -->
<body class="login">
	
	<link href="templates/assets/admin/pages/css/login.css" rel="stylesheet" type="text/css"/>

	<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
	<div class="menu-toggler sidebar-toggler">
	</div>
	<!-- END SIDEBAR TOGGLER BUTTON -->
	<!-- BEGIN LOGO -->
	<div class="logo">
		<a href="index.html">
		<img src="templates/assets/admin/layout2/img/logo-big.png" alt=""/>
		</a>
	</div>
	<!-- END LOGO -->
	<!-- BEGIN LOGIN -->
	<div class="content">
		<!-- BEGIN LOGIN FORM -->
		<form class="login-form" action="/bt_web_t3/login" method="post">
			<h3 class="form-title">Sign In</h3>
			<div class="alert alert-danger display-hide">
				<button class="close" data-close="alert"></button>
				<span>
				Enter any username and password. </span>
			</div>
			<div class="form-group">
				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
				<label class="control-label visible-ie8 visible-ie9">Username</label>
				<input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" placeholder="Username" name="username"/>
			</div>
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9">Password</label>
				<input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off" placeholder="Password" name="password"/>
			</div>
			<div class="form-actions">
				<button type="submit" class="btn btn-success uppercase">Login</button>
				<label class="rememberme check">
				<input type="checkbox" name="remember" value="1"/>Remember </label>
				<a href="/bt_web_t3/forgotpassword" id="forget-password" class="forget-password">Forgot Password?</a>
			</div>
			<div class="login-options">
				<h4>Or login with</h4>
				<ul class="social-icons">
					<li>
						<a class="social-icon-color facebook" data-original-title="facebook" href="#"></a>
					</li>
					<li>
						<a class="social-icon-color twitter" data-original-title="Twitter" href="#"></a>
					</li>
					<li>
						<a class="social-icon-color googleplus" data-original-title="Goole Plus" href="#"></a>
					</li>
					<li>
						<a class="social-icon-color linkedin" data-original-title="Linkedin" href="#"></a>
					</li>
				</ul>
			</div>
			<div class="create-account">
				<p>
					<a href="/bt_web_t3/register" id="register-btn" class="uppercase">Create an account</a>
				</p>
			</div>
		</form>
		<!-- END LOGIN FORM -->
		
	</div>
	<div class="copyright">
		 2014 © Metronic. Admin Dashboard Template.
	</div>
</body>
<!-- END LOGIN -->










































