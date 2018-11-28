package others;

import java.util.ArrayList;
import java.sql.SQLException;
import db.DbManager;
import exceptions.ParkingException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;


public class ParkingLot 
{
	private int lotId;
	private ArrayList<ParkingSpot> spots;
	static Connection conn; //connection to the database
	static PreparedStatement ps; //prepared statement for issuing sql queries
	DbManager db = DbManager.getInstance(); //DbManager object to initiate connection to database
	
	
	public ParkingLot(){}
	
	public ParkingLot(int lId) throws SQLException, ParkingException
	{
		lotId = lId;
		
		try
		{
			String sqlStr = "SELECT parkingSpotId, currentVehicle, occupied, colorClass FROM parkingSpot WHERE parkingLotId=" + String.valueOf(lotId);
			ResultSet rs = db.execute(sqlStr);
		
			if(rs.next())
			{
				spots = new ArrayList<>();
				
				while(rs.next())
				{
					spots.add(new ParkingSpot(rs.getInt(1), rs.getString(2), rs.getBoolean(3), rs.getString(4)));
				}
			}
		}
		catch(Exception ex)
		{
			throw ex;
		}
	}
	
	public int getParkingLotId()
	{
		return this.lotId;
	}
	
	public ParkingSpot[] getSpots()
	{
		return spots.toArray(new ParkingSpot[spots.size()]);
	}
}
