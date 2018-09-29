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
}
