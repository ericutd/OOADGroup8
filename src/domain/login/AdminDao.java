package domain.login;
 import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import db.DbManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

 public class AdminDao {
	static Connection conn;
	static PreparedStatement ps;
	DbManager db = new DbManager();
	
	public int Add(Admin p,int noOfSpots){
		int status = 0;
		
		while(noOfSpots > 0)
		{
			try{
				conn = db.getConnection();
				ps = conn.prepareStatement("insert into parkingSpot values(?,?,?,?)");
				ps.setInt(1, p.getParkingLotId());
				ps.setString(4, p.getColorPass());
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
}