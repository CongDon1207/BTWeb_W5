<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reset Password</title>
    <style>
        .alert {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
        }
        .alert-success {
            color: #3c763d;
            background-color: #dff0d8;
            border-color: #d6e9c6;
        }
        .alert-error {
            color: #a94442;
            background-color: #f2dede;
            border-color: #ebccd1;
        }
    </style>
</head>
<body>
    <h2>Reset Password</h2>

    <!-- Hiển thị thông báo lỗi -->
    <c:if test="${not empty error}">
        <div class="alert alert-error">
            ${error}
        </div>
    </c:if>

    <!-- Hiển thị thông báo thành công -->
    <c:if test="${not empty success}">
        <div class="alert alert-success">
            ${success}
        </div>
    </c:if>

    <form action="resetpassword" method="post">
        <input type="hidden" name="email" value="${email}">
        <label for="password">Mật khẩu mới:</label>
        <input type="password" name="password" required><br>
        
        <label for="confirmPassword">Xác nhận mật khẩu:</label>
        <input type="password" name="confirmPassword" required><br>
        
        <button type="submit">Đặt lại mật khẩu</button>
    </form>
</body>
</html>
