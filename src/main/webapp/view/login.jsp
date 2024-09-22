<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "jakarta.tags.core" %>
<%@ taglib prefix ="fmt" uri = "jakarta.tags.fmt"%>
<%@ taglib prefix = "fn" uri = "jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Form</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Arial', sans-serif;
        }
        .login-container {
            max-width: 400px;
            margin: 80px auto;
            padding: 30px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
        }
        .login-container h2 {
            margin-bottom: 20px;
            font-size: 24px;
            font-weight: bold;
            text-align: center;
        }
        .login-container .form-group {
            margin-bottom: 20px;
        }
        .login-container input[type="submit"] {
            background-color: #007bff;
            border: none;
            color: white;
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 5px;
            width: 100%;
            cursor: pointer;
        }
        .login-container input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .login-container .forgot-password {
            text-align: center;
            margin-top: 10px;
        }
        .login-container .forgot-password a {
            color: #007bff;
        }
        .login-container .forgot-password a:hover {
            color: #0056b3;
        }
    </style>
</head>
<body>

<div class="login-container">
    <h2>Login</h2>
    <form action="<%=request.getContextPath()%>/login" method="post">
    	<c:if test = "${alert != null }">
    		<h3 class = "alert alert danger">${alert}</h3>
    	</c:if>	
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" id="username" name="username" placeholder="Enter username" required>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Enter password" required>
        </div>
        <div class ="form-check">
	        <input type="checkbox" class="form-check-input" id="remember" name = "remember">
			<label class="form-check-label" for="remember">RememberMe</label>
        </div>
        <div>
        	<br><input type="submit" value="Login">
        </div>
        
        <div class="forgot-password">
            <a href="/bt_web_t3/forgotpassword">Forgot Password?</a>
        </div>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>










































