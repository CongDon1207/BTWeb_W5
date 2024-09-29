package vn.iotstar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.services.IUserService;
import vn.iotstar.services.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/resetpassword"})
public class ResetPasswordController extends HttpServlet {
    private static final long serialVersionUID = 6009718046008870052L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/resetpassword.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        IUserService service = new UserServiceImpl();
        
        // Kiểm tra xem mật khẩu và xác nhận mật khẩu có khớp hay không
        if (!password.equals(confirmPassword)) {
            // Nếu không khớp, chuyển hướng về trang reset với thông báo lỗi
            req.setAttribute("error", "Passwords do not match.");
            req.getRequestDispatcher("/view/resetpassword.jsp").forward(req, resp);
            return;
        }

        // Cập nhật mật khẩu nếu hợp lệ
        if (service.updatePassword(email, password)) {
            // Nếu thành công, chuyển hướng về trang đăng nhập với thông báo thành công
            req.setAttribute("success", "Password has been successfully reset.");
            req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
        } else {
            // Nếu có lỗi khi cập nhật mật khẩu
            req.setAttribute("error", "Failed to reset password. Please try again.");
            req.getRequestDispatcher("/view/resetpassword.jsp").forward(req, resp);
        }
    }
}

