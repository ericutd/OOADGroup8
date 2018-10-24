package domain.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import db.DbManager;

public class ParkingSpotsDao {
	static Connection conn;
	static PreparedStatement ps;
	DbManager db = new DbManager();
	
	public int Add(ParkingSpots p){
		int status = 0;
		String number = request.getParameter("id");
		int no = Integer.parseInt(number);
		while(no != 0)
		{
			try{
			conn = db.getConnection();
			ps = conn.prepareStatement("insert into parkingSpot values(?,?)");
			ps.setString(2, p.getParkingLotId());
			ps.setString(5, p.getColorPass());
			status = ps.executeUpdate();
			}catch(Exception e){
				System.out.println(e);
			}
			no--;
		}
		
		return status;
	}
	public getLotId(){
		conn = db.getConnection();
		ps =conn.prepareStatement("select parkingLotId from parkingLot");
		ResultSet rs = ps.executeQuery();
	}
}
