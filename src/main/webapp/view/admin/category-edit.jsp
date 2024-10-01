<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<form action="${pageContext.request.contextPath}/admin/category/update" method="post" enctype="multipart/form-data">
    <!-- Category ID (Hidden) -->
    <input type="hidden" id="categoryid" name="categoryid" value="${cate.categoryId}"><br>
    
    <!-- Category Name -->
    <label for="categoryname">Category Name:</label><br>
    <input type="text" id="categoryname" name="categoryname" value="${cate.categoryname}"><br>
    
    <!-- Image Upload and Preview -->
    <label for="images">Images:</label><br>
    <c:choose>
        <c:when test="${cate.images.substring(0,5) ne 'https'}">
            <c:url value="/image?fname=${cate.images}" var="imgUrl" />
        </c:when>
        <c:otherwise>
            <c:url value="${cate.images}" var="imgUrl" />
        </c:otherwise>
    </c:choose>
    
    <div>
        <img id="imagePreview" height="150" width="200" src="${imgUrl}" />
    </div>
    <input type="file" onchange="chooseFile(this)" id="images" name="images" accept="image/*"><br>
    
    <!-- Status -->
    <label for="status">Status (Enter a number):</label><br>
    <input type="number" id="status" name="status" value="${cate.status}"><br>
    
    <!-- Submit Button -->
    <br> 
    <input type="submit" value="Submit">
</form>

