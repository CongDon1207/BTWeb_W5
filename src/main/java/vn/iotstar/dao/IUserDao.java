package vn.iotstar.dao;
import vn.iotstar.models.User;



public interface IUserDao {
	User findByUserName(String username);
	void insert(User user);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	boolean checkExistPhone(String phone);
}
