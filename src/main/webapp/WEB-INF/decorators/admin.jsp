<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/common/taglib.jsp"%>
<c:url value="/" var="URL"></c:url>




<!DOCTYPE html>
<html lang="en">
<!--<![endif]-->


    <body>
        
        
        <div>
            <sitemesh:write property="body"/>
        </div>
        
        
        
	        <!-- Tải thư viện jQuery -->
	    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


	    
	    <!-- Hàm JavaScript để hiển thị hình ảnh khi chọn file -->
	    <script>
		    function chooseFile(fileInput) {
		        if (fileInput.files && fileInput.files[0]) {
		            var reader = new FileReader();
		            reader.onload = function(e) {
		                $('#imagePreview').attr('src', e.target.result); // Đảm bảo rằng ID '#imagePreview' khớp với thẻ img trên trang HTML
		            };
		            reader.readAsDataURL(fileInput.files[0]);
		        }
		    }
		</script>

        
	    
    </body>
</html>
