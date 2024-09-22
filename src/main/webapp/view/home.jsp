<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .navbar {
            background-color: #343a40;
            padding: 10px 20px;
        }
        .navbar .navbar-brand {
            color: #ffffff;
            font-size: 24px;
            font-weight: bold;
        }
        .navbar .nav-link {
            color: #ffffff;
            margin-right: 15px;
            font-size: 16px;
        }
        .navbar .nav-link:hover {
            color: #f8f9fa;
        }
        .navbar .fa-search {
            color: #ffffff;
            font-size: 18px;
        }
        .navbar .fa-search:hover {
            color: #f8f9fa;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">MyWebsite</a>
    <div class="collapse navbar-collapse">
        <c:choose>
            <c:when test="${sessionScope.account == null}">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/view/login.jsp">Đăng nhập</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/view/register.jsp">Đăng ký</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#"><i class="fa fa-search search-button"></i></a>
                    </li>
                </ul>
            </c:when>
            <c:otherwise>
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/member/myaccount">
                            ${sessionScope.account.fullName}
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/logout">Đăng Xuất</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#"><i class="fa fa-search search-button"></i></a>
                    </li>
                </ul>
            </c:otherwise>
        </c:choose>
    </div>
</nav>

<!-- FontAwesome icons and Bootstrap JS -->
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
