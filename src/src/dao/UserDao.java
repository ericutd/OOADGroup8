package dao;

import application.login.Login;
import others.User;

/**
 * 
 * @author mehra
 * The methods that we need to save and retrieve data from the database
 */
public interface UserDao {

	/**
	 * 
	 * @param c
	 * @return
	 */
	public int register(User c);
	
	/*
	 * Retrieve the Customer object from the database
	 */
	public User validateUser(Login login);

	
	//public Customer getCustomer(String username, String pass); This method does not needed as we have the Login object

	
}

