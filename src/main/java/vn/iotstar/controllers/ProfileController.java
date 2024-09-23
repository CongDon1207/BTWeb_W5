package vn.iotstar.controllers;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
@WebServlet(urlPatterns = "/profile")
public class ProfileController extends HttpServlet{
	 private static final long serialVersionUID = 1L;

	    // Path where the uploaded files will be saved
	    private static final String UPLOAD_DIRECTORY = "E:\\upload"; 

	    // This method extracts file name from the Part object
	    private String extractFileName(Part part) {
	        for (String content : part.getHeader("content-disposition").split(";")) {
	            if (content.trim().startsWith("filename")) {
	                return content.substring(content.indexOf("=") + 2, content.length() - 1);
	            }
	        }
	        return "default.file";
	    }
	    
	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    	req.getRequestDispatcher("/view/profile.jsp").forward(req, resp);
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Lấy dữ liệu từ form
	        String fullname = request.getParameter("fullname");
	        String phone = request.getParameter("phone");

	        // Thư mục upload
	        File uploadDir = new File(UPLOAD_DIRECTORY);
	        if (!uploadDir.exists()) uploadDir.mkdir();

	        // Xử lý phần upload file
	        try {
	            String fileName = "";
	            for (Part part : request.getParts()) {
	                if (part.getName().equals("profileImage")) {
	                    fileName = extractFileName(part);
	                    part.write(UPLOAD_DIRECTORY + File.separator + fileName);
	                }
	            }

	            // Ví dụ: lưu thông tin vào database hoặc lưu vào session
	            // (Bạn cần tích hợp JDBC hoặc Hibernate để lưu thông tin vào database)

	            // Sau khi upload xong, thiết lập thuộc tính và chuyển tới trang kết quả
	            request.setAttribute("message", "Profile updated successfully with the image " + fileName);
	            request.setAttribute("fullname", fullname);
	            request.setAttribute("phone", phone);
	            request.setAttribute("imagePath", UPLOAD_DIRECTORY + File.separator + fileName);

	        } catch (Exception e) {
	            request.setAttribute("message", "There was an error: " + e.getMessage());
	        }

	        // Forward tới trang kết quả
	        getServletContext().getRequestDispatcher("/view/profile.jsp").forward(request, response);
	    }
}
