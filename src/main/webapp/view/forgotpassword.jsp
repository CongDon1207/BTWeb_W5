<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quên mật khẩu</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .forgot-password-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }
        label {
            display: block;
            margin-bottom: 10px;
            font-size: 14px;
        }
        input[type="email"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #5cb85c;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }
        button:hover {
            background-color: #4cae4c;
        }
        .error-message {
            color: red;
            margin-bottom: 10px;
            text-align: center;
        }
        .success-message {
            color: green;
            margin-bottom: 10px;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="forgot-password-container">
        <h2>Quên mật khẩu</h2>

        <% if (request.getParameter("error") != null) { %>
            <div class="error-message">
                <%= request.getParameter("error") %>
            </div>
        <% } else if (request.getParameter("success") != null) { %>
            <div class="success-message">
                <%= request.getParameter("success") %>
            </div>
        <% } %>

        <form action="forgotpassword" method="post">
            <label for="email">Nhập email đã đăng ký:</label>
            <input type="email" id="email" name="email" placeholder="Nhập email của bạn" required>
            
            <button type="submit">Khôi phục mật khẩu</button>
        </form>
    </div>
</body>
</html>
