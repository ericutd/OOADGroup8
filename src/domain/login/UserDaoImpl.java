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
			ps =conn.prepareStatement("insert into customer values(?,?,?,?,?)");
			ps.setString(1, c.getUsername());
			ps.setString(2, c.getPassword());
			ps.setString(3, c.getName());
			ps.setString(4, c.getEmail());
			ps.setString(5, c.getAccountType());
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
			ps =conn.prepareStatement("select email,password,name from user where email=? and password=?");
			ps.setInt(4, login.getEmail());
			ps.setString(2, login.getPassword());

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				c.setUsername(rs.getString(1));
				c.setPassword(rs.getString(2));
				c.setName(rs.getString(3));
				c.setEmail(rs.getString(4));
				c.setAccountType(rs.getString(5));
			}
			conn.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return c;
	}
	
	public int getUserId(String email){
		int userId;
		try{
			conn = db.getConnection();
			ps =conn.prepareStatement("select userId from user where email=?");
			ps.setUserId(1, email);
			ResultSet rs = ps.executeQuery();

}
