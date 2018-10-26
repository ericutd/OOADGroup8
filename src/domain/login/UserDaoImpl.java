package domain.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DbManager;



public class UserDaoImpl implements UserDao {

	static Connection conn;
	static PreparedStatement ps;
	DbManager db = new DbManager();
	
	@Override
	public int register(User c) {
		int status = 0;
		try{
			conn = db.getConnection();
			ps =conn.prepareStatement("insert into user values(?,?,?,?,?)");
			ps.setInt(1, c.getUserid());
			ps.setString(2, c.getName());
			ps.setString(3, c.getPassword());
			ps.setString(4, c.getEmail());
			ps.setString(5,c.getAccounttype());
			status = ps.executeUpdate();
			conn.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return status;
	}

	@Override
	public User validateUser(Login login) {
		User c = new User();
		try{
			conn = db.getConnection();
			ps =conn.prepareStatement("select userId,password,name,accountType from user where userId=? and password=?");
			ps.setInt(1, login.getUsername());
			ps.setString(2, login.getPassword());

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				c.setUsername(rs.getString(1));
				c.setPassword(rs.getString(2));
				c.setName(rs.getString(3));
				c.setAccounttype(rs.getString(4));
			}
			conn.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return c;
	}
	
	public void updateUserDetails(User u) {
		try{
			conn = db.getConnection();
			ps =conn.prepareStatement("update user set name=?, password=?, email= ? where userId=?");
			ps.setString(1, u.getName());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getEmail());
			ps.setInt(4, u.getUserid());
			ps.executeUpdate();
			conn.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public User getUserDetails(int userId) {
		User c = new User();
		try{
			conn = db.getConnection();
			ps =conn.prepareStatement("select name,password,email from user where userId=? ");
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				c.setName(rs.getString(1));
				c.setPassword(rs.getString(2));
				c.setEmail(rs.getString(3));
			}
			conn.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return c;
	}
}
