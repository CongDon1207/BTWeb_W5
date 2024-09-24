package vn.iotstar.dao;
import java.util.List;

import vn.iotstar.models.User;



public interface IUserDao {
	User findByUserName(String username);
	User Get(int id);
	List<User> findAll();
	void insert(User user);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	boolean checkExistPhone(String phone);
	boolean updatePassword(String email, String newPassword);
	boolean updateProfile(String username, String fullname, String phone, String imagePath);
}
