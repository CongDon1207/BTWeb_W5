<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<form action="${pageContext.request.contextPath}/admin/category/insert" method="post" enctype="multipart/form-data">
    <label for="categoryname">Category Name:</label><br>
    <input type="text" id="categoryname" name="categoryname" value = "${cate.categoryname}"><br>
    
    <label for="images">Images:</label><br>
          <c:if test="${cate.images.substring(0,5) ne 'https'}">
              <c:url value="/image?fname=${cate.images}" var="imgUrl" />
          </c:if>
          <c:if test="${cate.images.substring(0,5) eq 'https'}">
              <c:url value="${cate.images}" var="imgUrl" />
          </c:if>
          <img height="150" width="200" src="${imgUrl}" />
    <input type="file" id="images" name="images" accept="image/*"  value = "${cate.images}"><br>
    
    <label for="status">Status (Enter a number):</label><br>
    <input type="number" id="status" name="status" value = "${cate.status}"><br>
    
    <br> 
    <input type="submit" value="Submit">
</form>
