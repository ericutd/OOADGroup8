package others;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Statement;

import application.login.Login;
import dao.UserDao;
import db.DbManager;



public class UserDaoImpl implements UserDao {

	static Connection conn;
	static PreparedStatement ps;
	DbManager db = DbManager.getInstance();
	
	@Override
	public int register(User c) {
		int status = 0;
		try{
			conn = db.getConnection();
			ps =conn.prepareStatement("insert into user(name,password,email,accountType) values(?,?,?,?)");
			ps.setString(1, c.getName());
			ps.setString(2, c.getPassword());
			ps.setString(3, c.getEmail());
			ps.setString(4,c.getAccounttype());
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
			ps =conn.prepareStatement("select userId,password,name,accountType from user where email=? and password=?");
			ps.setString(1, login.getEmail());
			ps.setString(2, login.getPassword());

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				c.setUserId((Integer.parseInt(rs.getString(1))));
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
			ps.setInt(4, u.getUserId());
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
	
	public int getUserId(String email){
		int userId =0;
		try{
			conn = db.getConnection();
			ps =conn.prepareStatement( "select userId from user where email=?");	
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				userId = Integer.parseInt(rs.getString(1));
			}
			conn.close();
		}catch (SQLException ex) {
		    ex.printStackTrace();
		}
		return userId;
	}
}
