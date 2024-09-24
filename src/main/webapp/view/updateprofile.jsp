<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Update Profile</title>
</head>
<body>
    <h2>Update Profile</h2>
    <form method="post" action="<%=request.getContextPath()%>/updateprofile" enctype="multipart/form-data">
        Fullname: <input type="text" name="fullname" required/><br/><br/>
        Phone: <input type="text" name="phone" required/><br/><br/>
        Choose an image: <input type="file" name="profileImage" accept="image/*" required/><br/><br/>
        <input type="submit" value="Update Profile"/>
    </form>
</body>
</html>
