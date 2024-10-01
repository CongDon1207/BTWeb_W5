package vn.iotstar.dao.impl;

import vn.iotstar.configs.DBConnectSQL;
import vn.iotstar.dao.ICategoryDao;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.models.Category;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.services.impl.CategoryServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl extends DBConnectSQL implements ICategoryDao{
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	
	@Override
	public void insert(Category category) {
		String sql = "INSERT INTO Category(categoryname,images,status) VALUES (?,?,?)";
		try {
			conn = new DBConnectSQL().getConnection();
		    ps = conn.prepareStatement(sql);
		    ps.setString(1, category.getCategoryname());
		    ps.setString(2, category.getImages());
		    ps.setInt(3, category.getStatus());
		    ps.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void edit(Category category) {
		String sql = "UPDATE Category SET categoryname = ?, images = ?, status = ? WHERE categoryId = ?";
		try {
			conn = new DBConnectSQL().getConnection();
		    ps = conn.prepareStatement(sql);
		    ps.setString(1, category.getCategoryname());
		    ps.setString(2, category.getImages());
		    ps.setInt(3, category.getStatus());
		    ps.setInt(4, category.getCategoryId());
		    ps.executeUpdate();
		    
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM category WHERE categoryId = ?";
		try {
			conn = new DBConnectSQL().getConnection();
		    ps = conn.prepareStatement(sql);
		    
		    ps.setInt(1, id);
		    ps.executeUpdate();

		    
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Category get(int id) {
		String sql = "SELECT * FROM category WHERE categoryId = ? ";
		try {
			conn = new DBConnectSQL().getConnection();
		    ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Category category = new Category();
				category.setCategoryId(rs.getInt("categoryId"));
				category.setCategoryname(rs.getString("categoryname"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));
				return category;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Category get(String name) {
		String sql = "SELECT * FROM category WHERE categoryname = ?";
	    try {
	        // Mở kết nối với cơ sở dữ liệu
	        conn = new DBConnectSQL().getConnection();
	        ps = conn.prepareStatement(sql);

	        // Gán tham số tên vào câu truy vấn
	        ps.setString(1, name);
	        ResultSet rs = ps.executeQuery();
	        
	        // Xử lý kết quả truy vấn
	        while (rs.next()) {
	            Category category = new Category();
	            category.setCategoryId(rs.getInt("categoryId"));
	            category.setCategoryname(rs.getString("categoryname"));
	            category.setImages(rs.getString("images"));
	            category.setStatus(rs.getInt("status"));
	            return category;
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        // Đảm bảo đóng kết nối và tài nguyên khi hoàn thành
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return null; 
	}

	@Override
	public List<Category> getAll() {
		List<Category> categories = new ArrayList<Category>();
		String sql = "SELECT * FROM Category";
		try {
			conn = new DBConnectSQL().getConnection();
		    ps = conn.prepareStatement(sql);
		    
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			Category category = new Category();
			category.setCategoryId(rs.getInt("categoryId"));
			category.setCategoryname(rs.getString("categoryname"));
			category.setImages(rs.getString("images"));
			category.setStatus(rs.getInt("status"));
			categories.add(category);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return categories;
	
	}

	@Override
	public List<Category> search(String keyword) {
		List<Category> categories = new ArrayList<>();
	    String sql = "SELECT * FROM Category WHERE categoryname LIKE ?";
	    try {
	        // Mở kết nối với cơ sở dữ liệu
	        conn = new DBConnectSQL().getConnection();
	        ps = conn.prepareStatement(sql);
	        
	        // Thiết lập tham số cho câu truy vấn
	        ps.setString(1, "%" + keyword + "%");  // Sử dụng LIKE với từ khóa
	        
	        ResultSet rs = ps.executeQuery();
	        
	        // Duyệt kết quả truy vấn và thêm các đối tượng Category vào danh sách
	        while (rs.next()) {
	            Category category = new Category();
	            category.setCategoryId(rs.getInt("categoryId"));
	            category.setCategoryname(rs.getString("categoryname"));
	            category.setImages(rs.getString("images"));
	            category.setStatus(rs.getInt("status"));
	            categories.add(category);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        // Đảm bảo đóng tài nguyên
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return categories; 
	}
	
	
	public static void main(String[] args) {
		try {
			ICategoryDao dao = new CategoryDaoImpl();
			String categoryname = "Oppo12";
	        
	        int statuss = 0;
	        String image = "https://cdn11.dienmaycholon.vn/filewebdmclnew/DMCL21/Picture/Apro/Apro_product_33149/oppo-a58_main_172.png";
	        
	        Category category = new Category();
	        category.setCategoryname(categoryname);
	        category.setImages(image);
	        category.setStatus(statuss);
	        category.setCategoryId(7);
	        dao.edit(category);
	        System.out.print(dao.get(7).getCategoryname());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
