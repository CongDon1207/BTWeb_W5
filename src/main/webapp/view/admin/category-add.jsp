<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form action="${pageContext.request.contextPath}/admin/category/insert" method="post" enctype="multipart/form-data">
    <label for="categoryname">Category Name:</label><br>
    <input type="text" id="categoryname" name="categoryname" required><br>
    
    <label for="images">Images:</label><br>
    <input type="file" onchange="chooseFile(this)" id="images" name="images" accept="image/*" required><br>
    
    <label for="status">Status (Enter a number):</label><br>
    <input type="number" id="status" name="status" required><br>
    
    <br> 
    <input type="submit" value="Submit">
</form>
