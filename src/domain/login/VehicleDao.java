package domain.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DbManager;

public class VehicleDao {
	static Connection conn;
	static PreparedStatement ps;
	DbManager db = new DbManager();

	public Vehicle VehicleDetails(Login login) {
		Vehicle  v= new Vehicle();
		try{
			conn = db.getConnection();
			ps =conn.prepareStatement("select make,model,licenseNum from vehicle where ownerId=? ");
			ps.setInt(1, login.getUsername());

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				v.setMake(rs.getString(1));
				v.setModel(rs.getString(2));
				v.setLicenseNum(rs.getString(3));
			}
			conn.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return v;
	}
	
	public int register(Vehicle v,int userId) {
		int status = 0;
		try{
			conn = db.getConnection();
			ps =conn.prepareStatement("insert into vehicle values(?,?,?,?,?,?)");
			ps.setInt(1, userId);
			ps.setString(2, v.getLicenseNum());
			ps.setString(3, v.getMake());
			ps.setString(4, v.getModel());
			ps.setString(5,v.getYear());
			ps.setString(6, v.getColor());
			status = ps.executeUpdate();
			conn.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return status;
	}
}
