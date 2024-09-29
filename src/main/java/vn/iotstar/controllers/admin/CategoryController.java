package vn.iotstar.controllers.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.iotstar.models.Category;
import vn.iotstar.services.*;
import vn.iotstar.services.impl.*;
@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/add", "/admin/category/insert", "/admin/category/edit", "/admin/category/update",
		"/admin/category/delete", "/admin/category/search"})
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
            int statuss = 1;
            String image = "https://cdn11.dienmaycholon.vn/filewebdmclnew/DMCL21/Picture/Apro/Apro_product_33149/oppo-a58_main_172.png";
            
            Category category = new Category();
            category.setCategoryname(categoryname);
            category.setImages(image);
            category.setStatus(statuss);
            
            //cateService.insert(category);
            resp.sendRedirect(req.getContextPath() 	+ "/admin/categories");
        } 
    }
}

