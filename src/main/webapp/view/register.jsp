<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tạo tài khoản mới</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }
        .register-container {
            max-width: 500px;
            margin: 50px auto;
            padding: 30px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        .register-container h2 {
            text-align: center;
            margin-bottom: 20px;
            font-size: 24px;
            font-weight: bold;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-control {
            padding: 10px;
            font-size: 14px;
        }
        .input-group-addon {
            background-color: #e9ecef;
        }
        .submit-btn {
            background-color: #007bff;
            color: white;
            font-size: 16px;
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
        }
        .submit-btn:hover {
            background-color: #0056b3;
        }
        .login-link {
            text-align: center;
            margin-top: 10px;
        }
        .login-link a {
            color: #007bff;
        }
    </style>
</head>
<body>

<div class="register-container">
    <h2>Tạo tài khoản mới</h2>
    
    <c:if test="${alert != null}">
        <div class="alert alert-danger">${alert}</div>
    </c:if>
    
    <form action="register" method="post">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon"><i class="fa fa-user"></i></div>
                <input type="text" class="form-control" placeholder="Tài khoản" name="username" required>
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon"><i class="fa fa-user"></i></div>
                <input type="text" class="form-control" placeholder="Họ tên" name="fullname" required>
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon"><i class="fa fa-envelope"></i></div>
                <input type="email" class="form-control" placeholder="Nhập Email" name="email" required>
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon"><i class="fa fa-phone"></i></div>
                <input type="text" class="form-control" placeholder="Số điện thoại" name="phone" required>
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon"><i class="fa fa-lock"></i></div>
                <input type="password" class="form-control" placeholder="Mật khẩu" name="password" required>
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon"><i class="fa fa-lock"></i></div>
                <input type="password" class="form-control" placeholder="Nhập lại mật khẩu" name="confirm_password" required>
            </div>
        </div>

        <button type="submit" class="submit-btn">Tạo tài khoản</button>

        <div class="login-link">
            <p>Nếu bạn đã có tài khoản? <a href="${pageContext.request.contextPath}/login">Đăng nhập</a></p>
        </div>
    </form>
</div>

<script src="https://kit.fontawesome.com/a076d05399.js"></script>
</body>
</html>
