package vn.iotstar.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.models.*;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/home"})

public class HomeController extends HttpServlet{
	
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session != null && session.getAttribute("account") != null) {
	        User u = (User) session.getAttribute("account"); // Lấy User từ session
	        String username = u.getUserName(); // Lấy username từ User object
	        
	        req.setAttribute("username", username); // Đặt username vào request attribute

	        // Chuyển tiếp đến home.jsp
	        req.getRequestDispatcher("/view/home.jsp").forward(req, resp);
	    } else {
	        resp.sendRedirect(req.getContextPath() + "/login"); // Nếu session không tồn tại, chuyển hướng về login
	    }
	}	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("view/home.jsp").forward(req, resp);
	}
}
