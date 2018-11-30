package application.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import others.UserDaoImpl;
import others.User;

public class UserService {

	private UserDaoImpl dao;
	
	public UserService() {
		dao = new UserDaoImpl();
	}
	
	public User getUser(int userId) {
		return dao.getUserDetails(userId);		
	}
	
}
