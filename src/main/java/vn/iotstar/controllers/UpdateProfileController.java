package vn.iotstar.controllers;

import java.io.File;
import java.io.IOException;

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
import vn.iotstar.services.UserServiceImpl;
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
    private static final String UPLOAD_DIRECTORY = "uploads"; 

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
        File uploadDir = new File(UPLOAD_DIRECTORY);
        if (!uploadDir.exists()) uploadDir.mkdir();

        // Xử lý phần upload file
        try {
            String fileName = "";
            for (Part part : req.getParts()) {
                if (part.getName().equals("profileImage")) {
                    fileName = extractFileName(part);
                    part.write(UPLOAD_DIRECTORY + File.separator + fileName);
                }
            }
            HttpSession session = req.getSession();
            User u = (User) session.getAttribute("account"); // Lấy User từ session
	        String username = u.getUserName(); // Lấy username từ User object 
	        req.setAttribute("username", username);
            
            System.out.print("**** " + username + " ****");
            IUserService service = new UserServiceImpl();
            boolean isUpdated = service.updateProfile(username, fullname, phone, UPLOAD_DIRECTORY + File.separator + fileName);

            if (isUpdated) {
                req.setAttribute("message", "Profile updated successfully with the image " + fileName);
            } else {
                req.setAttribute("message", "Failed to update profile. Please try again.");
            }
            
            req.setAttribute("message", "Profile updated successfully with the image " + fileName);
            req.setAttribute("fullname", fullname);
            req.setAttribute("phone", phone);
            req.setAttribute("imagePath", UPLOAD_DIRECTORY + File.separator + fileName);

        } catch (Exception e) {
            req.setAttribute("message", "There was an error: " + e.getMessage());
        }

        // Forward tới trang kết quả
        getServletContext().getRequestDispatcher("/view/profile.jsp").forward(req, resp);
	}
}
