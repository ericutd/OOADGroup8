package domain.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.fabric.xmlrpc.base.Array;

import db.DbManager;

public class VehicleDao {
	static Connection conn;
	static PreparedStatement ps;
	DbManager db = new DbManager();

	public Vehicle[] VehicleDetails(int userid) {
		ArrayList<Vehicle> vi= new ArrayList<>();
		try{
			conn = db.getConnection();
			ps =conn.prepareStatement("select make,model,licenseNum from vehicle where ownerId=? ");
			ps.setInt(1, userid);

			ResultSet rs = ps.executeQuery();
			
				while(rs.next()){
					Vehicle v = new Vehicle();
					v.setMake(rs.getString(1));
					v.setModel(rs.getString(2));
					v.setLicenseNum(rs.getString(3));
					vi.add(v);
				}
				
			conn.close();
			
		}catch(Exception e){
			System.out.println(e);
		}
		return vi.toArray(new Vehicle[vi.size()]);
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
	
	public void updateVehicleDetails(Vehicle v) {
		try{
			conn = db.getConnection();
			ps =conn.prepareStatement("update vehicle set licenseNum=?, make=?, model= ?, year=?, color=? where ownerId=?");
			ps.setString(1, v.getLicenseNum());
			ps.setString(2, v.getMake());
			ps.setString(3, v.getModel());
			ps.setString(4, v.getYear());
			ps.setString(5, v.getColor());
			ps.setInt(6, v.getOwnerid());
			ps.executeUpdate();
			conn.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
