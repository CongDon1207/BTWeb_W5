package vn.iotstar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.utils.Constant;
@WebServlet(urlPatterns = {"/logout"})
public class LogoutController extends HttpServlet{
	private static final long serialVersionUID = 1865490755492686544L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

	    // Xóa session
	    session.removeAttribute("account");

	    // Lấy danh sách cookie
	    Cookie[] cookies = req.getCookies();

	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            // Nếu tìm thấy cookie nhớ đăng nhập
	            if (Constant.COOKIE_REMEMBER.equals(cookie.getName())) {
	                // Xóa cookie bằng cách đặt thời gian sống là 0
	                cookie.setMaxAge(0);
	                resp.addCookie(cookie); // Thêm lại cookie vào response
	                break;
	            }
	        }
	    }

	    // Chuyển hướng về trang đăng nhập
	    resp.sendRedirect("/bt_web_t3/login");
	}
}
