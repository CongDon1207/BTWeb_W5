package vn.iotstar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.services.IUserService;
import vn.iotstar.services.UserServiceImpl;

@WebServlet(urlPatterns = {"/forgotpassword"})
public class ForgotPasswordController extends HttpServlet {

    private static final long serialVersionUID = -3989586959089272808L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/forgotpassword.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IUserService service = new UserServiceImpl();
        String email = req.getParameter("email");
        
        if (service.checkExistEmail(email)) {
            // Đẩy email sang trang resetpassword.jsp thông qua request attribute
            req.setAttribute("email", email);
            req.getRequestDispatcher("/view/resetpassword.jsp").forward(req, resp);
        } 
    }
}
