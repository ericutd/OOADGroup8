package dao;
 import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import db.DbManager;
import others.Admin;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

 public class AdminDao {
	static Connection conn;
	static PreparedStatement ps;
	DbManager db = DbManager.getInstance();
	
	public int insertParkingSpot(Admin p,int noOfSpots){
		int status = 0;
		
		while(noOfSpots > 0)
		{
			try{
				conn = db.getConnection();
				ps = conn.prepareStatement("insert into parkingSpot(parkingLotId,currentVehicle,occupied,colorClass) values(?,'',0,?)");
				ps.setInt(1, p.getParkingLotId());
				ps.setString(2, p.getColorPass());
				status = ps.executeUpdate();
			}catch(Exception e){
				System.out.println(e);
			}
			noOfSpots--;
		}
		
		return status;
	}
	public List<Integer> list() throws SQLException {
		List<Integer> lotId = new ArrayList<>();
 		try{
			conn = db.getConnection();
			String sql = "SELECT parkingLotId FROM parkingLot";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
 			while (result.next()) {
				int id = result.getInt("parkingLotId");
				lotId.add(id);
		    }          
 		} catch (SQLException ex) {
		    ex.printStackTrace();
		    throw ex;
		}      
 		return lotId;
	}
	
	public int getLotId(){
		int lotId =0;
		try{
			conn = db.getConnection();
			ps =conn.prepareStatement( "select max(parkingLotId) from parkingLot");	
			//ps.setString(1, email);

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				lotId = Integer.parseInt(rs.getString(1));
			}
			conn.close();
		}catch (SQLException ex) {
		    ex.printStackTrace();
		}
		return lotId;
	}
	
	public int insertParkingLot(int noOfLots) {
		int status = 0;
		try{
			conn = db.getConnection();
			while(noOfLots>0) {
				ps =conn.prepareStatement("insert into parkinglot values()");
				status = ps.executeUpdate();
				noOfLots--;
			}		
			conn.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return status;
	}
}