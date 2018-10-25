package domain.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import db.DbManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ParkingSpotsDao {
	static Connection conn;
	static PreparedStatement ps;
	DbManager db = new DbManager();
	
	public int Add(Admin p){
		int status = 0;
		String number = request.getParameter("number");
		int no = Integer.parseInt(number);
		while(no > 0)
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
	public List<int> list() throws SQLException {
		List<int> lotId = new ArrayList<>();

		try{
			String sql = "SELECT parkingLotId FROM parkingLot";
			Statement statement = connection.createStatement();
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
}
