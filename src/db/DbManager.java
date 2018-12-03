package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 
 * @author mehra
 * getConnection method help us to connect to the appropriate database. In this project we only have one
 * database. Data comes from MyDB interface.
 */
public class DbManager implements MyDB{
	
	private static DbManager dbManager;
	
	private DbManager() {
		
	}
	
	public static DbManager getInstance() {
		if(dbManager == null) {
			dbManager = new DbManager();
		}
		return dbManager;
	}

	public Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection myConnection = DriverManager.getConnection(CONN_URL,USER,PASS);
			return myConnection;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public ResultSet execute(String sql) throws SQLException {
		Connection connection = getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public void update(String sql) throws SQLException{
		Connection connection = getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.executeUpdate();
	}
}
