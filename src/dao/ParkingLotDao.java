package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import db.DbManager;

public class ParkingLotDao {
	static Connection conn;
	static PreparedStatement ps;
	DbManager db = DbManager.getInstance();
	
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
