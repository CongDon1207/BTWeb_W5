package vn.iotstar.controllers;

import java.io.IOException;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.services.*;
import vn.iotstar.services.impl.UserServiceImpl;
import vn.iotstar.utils.Constant;
@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    HttpSession session = req.getSession(false);

	    // Kiểm tra nếu đã có session và username
	    if (session != null && session.getAttribute("username") != null) {
	        resp.sendRedirect(req.getContextPath() + "/admin");
	        return;
	    }

	    // Kiểm tra cookie nếu session chưa tồn tại
	    Cookie[] cookies = req.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if (cookie.getName().equals("username")) {
	                session = req.getSession(true);
	                session.setAttribute("username", cookie.getValue());
	                resp.sendRedirect(req.getContextPath() + "/admin");
	                return;
	            }
	        }
	    }

	    // Chuyển hướng tới trang đăng ký nếu không có session hoặc cookie
	    req.getRequestDispatcher(Constant.REGISTER).forward(req, resp);

	}
	@SuppressWarnings("static-access")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Set charset để hỗ trợ UTF-8 cho cả request và response
	    resp.setCharacterEncoding("UTF-8");
	    req.setCharacterEncoding("UTF-8");

	    // Lấy các tham số từ form
	    String username = req.getParameter("username");
	    String password = req.getParameter("password");
	    String email = req.getParameter("email");
	    String fullname = req.getParameter("fullname");
	    String phone = req.getParameter("phone");

	    // Khởi tạo UserService để xử lý nghiệp vụ
	    IUserService service = new UserServiceImpl();
	    String alertMsg = "";

	    // Kiểm tra xem email đã tồn tại hay chưa
	    if (service.checkExistEmail(email)) {
	        alertMsg = "Email đã tồn tại!";
	        req.setAttribute("alert", alertMsg);
	        req.getRequestDispatcher(Constant.REGISTER).forward(req, resp);
	        return;
	    }

	    // Kiểm tra xem username đã tồn tại hay chưa
	    if (service.checkExistUsername(username)) {
	        alertMsg = "Tài khoản đã tồn tại!";
	        req.setAttribute("alert", alertMsg);
	        req.getRequestDispatcher(Constant.REGISTER).forward(req, resp);
	        return;
	    }

	    // Đăng ký người dùng mới
	    boolean isSuccess = service.register(email, password, username,  fullname, phone);

	    // Kiểm tra trạng thái của việc đăng ký
	    if (isSuccess) {
	        // Có thể gửi email chào mừng ở đây nếu cần
	        // SendMail sm = new SendMail();
	        // sm.sendMail(email, "Shopping.iotstar.vn", "Welcome to Shopping. Please Login to use the service. Thanks!");
	    	req.setAttribute("alert", alertMsg);
	    	resp.sendRedirect(req.getContextPath() + "/login");
	    } else {
	        alertMsg = "System error!";
	        req.setAttribute("alert", alertMsg);
	        req.getRequestDispatcher(Constant.REGISTER).forward(req, resp);
	    }
	}
	
	public static void main(String[] args) {
		
	}
}
