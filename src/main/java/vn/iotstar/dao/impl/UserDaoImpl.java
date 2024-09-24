package vn.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vn.iotstar.configs.DBConnectSQL;
import vn.iotstar.dao.IUserDao;

import java.util.ArrayList;
import java.util.List;
import vn.iotstar.models.User;


public class UserDaoImpl extends DBConnectSQL implements IUserDao{
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	
	public User Get(int id) {
		String sql = "SELECT * FROM [User] WHERE id = ?";
		try {
	        conn = new DBConnectSQL().getConnection();
	        ps = conn.prepareStatement(sql);
	        ps.setInt(1, id);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            User user = new User();
	            user.setId(rs.getInt("id"));
	            user.setEmail(rs.getString("email"));
	            user.setUserName(rs.getString("username"));
	            user.setFullName(rs.getString("fullname"));
	            user.setPassWord(rs.getString("password"));
	            user.setImages(rs.getString("images"));
	            user.setRoleid(Integer.parseInt(rs.getString("roleid")));
	            user.setPhone(rs.getString("phone"));
	            user.setCreatedDate(rs.getDate("createdDate"));
	            return user;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public User findByUserName(String username) {
		String sql = "SELECT * FROM [User] WHERE username = ?";
	    try {
	        conn = new DBConnectSQL().getConnection();
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, username);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            User user = new User();
	            user.setId(rs.getInt("id"));
	            user.setEmail(rs.getString("email"));
	            user.setUserName(rs.getString("username"));
	            user.setFullName(rs.getString("fullname"));
	            user.setPassWord(rs.getString("password"));
	            user.setImages(rs.getString("images"));
	            user.setRoleid(Integer.parseInt(rs.getString("roleid")));
	            user.setPhone(rs.getString("phone"));
	            user.setCreatedDate(rs.getDate("createdDate"));
	            return user;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	@Override
	public void insert(User user) {
	    String sql = "INSERT INTO [User](email, username, fullname, password, images, roleid, phone, createddate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

   try {
       // Kết nối cơ sở dữ liệu
       conn = new DBConnectSQL().getConnection();
       ps = conn.prepareStatement(sql);
       
       // Thiết lập các tham số cho câu lệnh SQL
       ps.setString(1, user.getEmail());
       ps.setString(2, user.getUserName());
       ps.setString(3, user.getFullName());
       ps.setString(4, user.getPassWord());
       ps.setString(5, user.getImages());
       ps.setInt(6, user.getRoleid());
       ps.setString(7, user.getPhone());
       ps.setDate(8, user.getCreatedDate());

       // Thực thi câu lệnh SQL
       ps.executeUpdate();
   } catch (Exception e) {
       // In ra thông tin lỗi nếu có
       e.printStackTrace();
   } finally {
       // Đảm bảo đóng kết nối và statement sau khi sử dụng
       try {
           if (ps != null) ps.close();
           if (conn != null) conn.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
		
	}
	
	@Override
    public boolean updatePassword(String email, String newPassword) {
        String sql = "UPDATE [User] SET password = ? WHERE email = ?";
        
        try {
        	conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
             
            ps.setString(1, newPassword);  // Đặt mật khẩu mới đã mã hóa
            ps.setString(2, email);           // Đặt email người dùng
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;  // Nếu cập nhật thành công, trả về true
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;  // Nếu có lỗi xảy ra, trả về false
    }
	@Override
	public boolean checkExistEmail(String email) {
	    boolean duplicate = false;
	    String query = "SELECT * FROM [User] WHERE email = ?";

	    try {
	        // Kết nối cơ sở dữ liệu
	        conn = new DBConnectSQL().getConnection();
	        ps = conn.prepareStatement(query);
	        ps.setString(1, email);

	        // Thực thi câu truy vấn
	        rs = ps.executeQuery();
	        
	        // Kiểm tra xem có kết quả trả về hay không
	        if (rs.next()) {
	            duplicate = true;
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace(); // Xử lý lỗi, in thông tin lỗi ra
	    } finally {
	        // Đảm bảo đóng tài nguyên sau khi sử dụng
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return duplicate;
	}

	@Override
	public boolean checkExistUsername(String username) {
	    boolean duplicate = false;
	    String query = "SELECT * FROM [User] WHERE username = ?";

	    try {
	        // Kết nối đến cơ sở dữ liệu
	        conn = new DBConnectSQL().getConnection();
	        ps = conn.prepareStatement(query);
	        ps.setString(1, username);

	        // Thực thi câu truy vấn
	        rs = ps.executeQuery();

	        // Kiểm tra xem có kết quả trả về hay không
	        if (rs.next()) {
	            duplicate = true;
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace(); // In ra thông tin lỗi nếu có
	    } finally {
	        // Đảm bảo đóng tài nguyên sau khi sử dụng
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace(); // In lỗi khi đóng tài nguyên
	        }
	    }

	    return duplicate;
	}

	@Override
	public boolean checkExistPhone(String phone) {
	    boolean exists = false;
	    String query = "SELECT * FROM [User] WHERE phone = ?";

	    try {
	        // Kết nối đến cơ sở dữ liệu
	        conn = new DBConnectSQL().getConnection();
	        ps = conn.prepareStatement(query);
	        ps.setString(1, phone);

	        // Thực thi câu truy vấn
	        rs = ps.executeQuery();

	        // Kiểm tra nếu có bản ghi nào tồn tại với số điện thoại này
	        if (rs.next()) {
	            exists = true;
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // In thông báo lỗi nếu có
	    } finally {
	        // Đảm bảo đóng tài nguyên sau khi sử dụng
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace(); // In thông báo lỗi khi đóng tài nguyên
	        }
	    }

	    return exists;
	}

	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<>();
	    String sql = "SELECT * FROM [User]";
	    try {
	        Connection conn = new DBConnectSQL().getConnection();
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {
	            User user = new User();
	            user.setId(rs.getInt("id"));
	            user.setEmail(rs.getString("email"));
	            user.setUserName(rs.getString("username"));
	            user.setFullName(rs.getString("fullname"));
	            user.setPassWord(rs.getString("password"));
	            user.setImages(rs.getString("images"));
	            user.setRoleid(rs.getInt("roleid"));
	            user.setPhone(rs.getString("phone"));
	            user.setCreatedDate(rs.getDate("createdDate"));
	            
	            users.add(user);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return users;
	}
	


	@Override
	public boolean updateProfile(String username, String fullname, String phone, String imagePath) {
		String sql = "UPDATE [User] SET fullname = ?, phone = ?, images = ? WHERE username = ?";
        
        try {
        	conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
             
            ps.setString(1, fullname);
            ps.setString(2, phone);
            ps.setString(3, imagePath);  
            ps.setString(4, username);
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;  // Nếu cập nhật thành công, trả về true
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;  // Nếu có lỗi xảy ra, trả về false
	}
	
	public static void main(String[] args) {
		try {
			IUserDao userDao = new UserDaoImpl();
			userDao.updatePassword("donnc@gmail.com", "111");
			System.out.print(userDao.findByUserName("donnc"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
