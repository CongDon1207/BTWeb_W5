package vn.iotstar.controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vn.iotstar.models.User;
import vn.iotstar.services.IUserService;
import vn.iotstar.services.impl.UserServiceImpl;
import vn.iotstar.utils.Constant;
@WebServlet(urlPatterns = {"/updateprofile"})

@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024, // 1MB
	    maxFileSize = 1024 * 1024 * 10, // 10MB
	    maxRequestSize = 1024 * 1024 * 50 // 50MB
	)
public class UpdateProfileController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7569691220550706686L;
	// Path where the uploaded files will be saved
    

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
		req.getRequestDispatcher("/view/updateprofile.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Lấy dữ liệu từ form
	    String fullname = req.getParameter("fullname");
	    String phone = req.getParameter("phone");
	    System.out.println(fullname);
	    System.out.println(phone);

	    // Thư mục upload
	    String uploadPath = Constant.DIR;
	    File uploadDir = new File(uploadPath);
	    if (!uploadDir.exists()) uploadDir.mkdir();

	    // Xử lý phần upload file
	    String fileName = "";
	    try {
	        for (Part part : req.getParts()) {
	            if (part.getName().equals("profileImage")) {
	                fileName = extractFileName(part);
	                part.write(uploadPath + File.separator + fileName);
	            }
	        }

	        HttpSession session = req.getSession();
	        User u = (User) session.getAttribute("account"); // Lấy User từ session
	        String username = u.getUserName(); // Lấy username từ User object 
	        IUserService service = new UserServiceImpl();
	        boolean isUpdated = service.updateProfile(username, fullname, phone, uploadPath + File.separator + fileName);

	        // Thiết lập nội dung trả về
	        resp.setContentType("text/html");
	        PrintWriter out = resp.getWriter();

	        // Tạo phản hồi HTML
	        out.println("<html><body>");
	        if (isUpdated) {
	            out.println("<h3>Profile updated successfully with the image " + fileName + "</h3>");
	        } else {
	            out.println("<h3>Failed to update profile. Please try again.</h3>");
	        }
	        out.println("<p>Full name: " + fullname + "</p>");
	        out.println("<p>Phone: " + phone + "</p>");
	        out.println("<img src='" + req.getContextPath() + "/image?fname=" + fileName + "' alt='Profile Image' width='150' height='150' />");

	        out.println("</body></html>");
	        System.out.println("File saved at: " + uploadPath + File.separator + fileName);


	    } catch (Exception e) {
	        // Thiết lập nội dung phản hồi lỗi
	        resp.setContentType("text/html");
	        PrintWriter out = resp.getWriter();
	        out.println("<html><body>");
	        out.println("<h3>There was an error: " + e.getMessage() + "</h3>");
	        out.println("</body></html>");
	    }
	}
}