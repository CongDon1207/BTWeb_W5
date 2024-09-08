package vn.iotstar.services;

import vn.iotstar.models.User;
import vn.iotstar.dao.*;
import vn.iotstar.dao.impl.UserDaoImpl;

public class UserServiceImpl implements IUserService{
	IUserDao userDao = new UserDaoImpl();
	@Override
	public User login(String username, String password) {
		User user = this.findByUserName(username);
	    if (user != null && password.equals(user.getPassWord())) {
	        return user;
	    }
	    return null;
	}

	@Override
	public User get(String username) {
		return this.findByUserName(username);
	}
	
	public User findByUserName(String username) {
		return userDao.findByUserName(username);
	}

	@Override
	public void insert(User user) {
		userDao.insert(user);
	}

	@Override
	public boolean register(String email, String password, String username, String fullname, String phone) {
	    // Kiểm tra xem username đã tồn tại hay chưa
	    if (userDao.checkExistUsername(username)) {
	        return false;
	    }

	    // Lấy thời gian hiện tại
	    long millis = System.currentTimeMillis(); 
	    java.sql.Date date = new java.sql.Date(millis);

	    // Chèn thông tin người dùng mới vào cơ sở dữ liệu
	    User newUser = new User(0, email, username, fullname, password, null, 5, phone, date);
	    userDao.insert(newUser);

	    return true;
	}

	@Override
	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmail(email);

	}

	@Override
	public boolean checkExistUsername(String username) {
		return userDao.checkExistUsername(username);
	}

	@Override
	public boolean checkExistPhone(String phone) {
		return userDao.checkExistPhone(phone);
	}
}
