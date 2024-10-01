package vn.iotstar.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iotstar.models.Category;
import vn.iotstar.services.*;
import vn.iotstar.services.impl.*;
import vn.iotstar.utils.Constant;
@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/add", "/admin/category/insert", "/admin/category/edit", "/admin/category/update",
		"/admin/category/delete", "/admin/category/search"})
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024, // Ngưỡng kích thước file tạm thời (1MB)
	    maxFileSize = 1024 * 1024 * 5,   // Kích thước file tối đa (5MB)
	    maxRequestSize = 1024 * 1024 * 5 * 5 // Kích thước yêu cầu tối đa (25MB)
	)

public class CategoryController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    // Khởi tạo service cho Category
    public ICategoryService cateService = new CategoryServiceImpl();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        if (url.contains("categories")) {
            List<Category> list = cateService.getAll();
            req.setAttribute("listcate", list);
            req.getRequestDispatcher("/view/admin/category-list.jsp").forward(req, resp);
        } else if (url.contains("add")) {
            req.getRequestDispatcher("/view/admin/category-add.jsp").forward(req, resp);
        } else if (url.contains("edit")) {
        	int id = Integer.parseInt(req.getParameter("id"));
            
            Category category = cateService.get(id);
            
            req.setAttribute("cate", category);
            
            req.getRequestDispatcher("/view/admin/category-edit.jsp").forward(req, resp);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        if (url.contains("insert")) {
            String categoryname = req.getParameter("categoryname");
            String status = req.getParameter("status");
            int statuss = Integer.parseInt(status);

            // Lấy file ảnh từ request
            Part filePart = req.getPart("images");
            String imageFileName = null;

            if (filePart != null && filePart.getSize() > 0) {
                // Lấy tên file ảnh
                imageFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                // Đường dẫn lưu trữ file ảnh
                String uploadDir = Constant.DIR ;
                File uploadDirFile = new File(uploadDir);
                if (!uploadDirFile.exists()) {
                    uploadDirFile.mkdirs();
                }

                // Lưu file ảnh vào thư mục trên máy chủ
                File imageFile = new File(uploadDir + "/" + imageFileName);
                filePart.write(imageFile.getAbsolutePath());
            } else {
                imageFileName = "default-image.png"; // Hoặc sử dụng ảnh mặc định nếu không upload ảnh
            }

            // Tạo đối tượng Category và lưu trữ thông tin
            Category category = new Category();
            category.setCategoryname(categoryname);
            category.setImages(imageFileName);
            category.setStatus(statuss);

            // cateService.insert(category);
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        } else if (url.contains("update")) {
            int categoryid = Integer.parseInt(req.getParameter("categoryid"));
            String categoryname = req.getParameter("categoryname");
            String status = req.getParameter("status");
            int statuss = Integer.parseInt(status);

            // Lấy file ảnh từ request
            Part filePart = req.getPart("images");
            String imageFileName = null;

            if (filePart != null && filePart.getSize() > 0) {
                // Lấy tên file ảnh
                imageFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                // Đường dẫn lưu trữ file ảnh
                String uploadDir = Constant.DIR ;
                File uploadDirFile = new File(uploadDir);
                if (!uploadDirFile.exists()) {
                    uploadDirFile.mkdirs();
                }

                // Lưu file ảnh vào thư mục trên máy chủ
                File imageFile = new File(uploadDir + "/" + imageFileName);
                filePart.write(imageFile.getAbsolutePath());
            }

            // Tạo đối tượng Category và cập nhật thông tin
            Category category = new Category();
            category.setCategoryId(categoryid);
            category.setCategoryname(categoryname);
            category.setImages(imageFileName != null ? imageFileName : "white-goods.png"); // Sử dụng ảnh mới hoặc ảnh mặc định nếu không upload ảnh
            category.setStatus(statuss);

            cateService.edit(category);
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        }
    }

}

