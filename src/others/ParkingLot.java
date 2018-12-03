package others;

import java.util.ArrayList;
import java.sql.SQLException;
import db.DbManager;
import exceptions.ParkingException;
import pojo.ParkingSpot;
import pojo.Vehicle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import dao.VehicleDao;


public class ParkingLot 
{
	private int lotId;
	private ArrayList<ParkingSpot> spots;
	static Connection conn; //connection to the database
	static PreparedStatement ps; //prepared statement for issuing sql queries
	DbManager db = DbManager.getInstance(); //DbManager object to initiate connection to database
	
	
	
	public ParkingLot() throws SQLException, ParkingException
	{	
		loadSpots();
	}
	
	private void loadSpots() throws ParkingException, SQLException
	{
		try
		{
			String sqlStr = "SELECT parkingSpotId, currentVehicle, occupied, colorClass FROM parkingSpot WHERE parkingLotId=" + String.valueOf(lotId);
			ResultSet rs = db.execute(sqlStr);
		
			if(rs.next())
			{
				spots = new ArrayList<>();
				
				ResultSet rs2;
				Vehicle v = new Vehicle();
				VehicleDao VD = new VehicleDao();
				
				while(rs.next())
				{
					ParkingSpot ps = new ParkingSpot();
					ps.setSpotId(rs.getInt(1));
					sqlStr = "SELECT ownerId FROM vehicle WHERE licenseNum=" + rs.getString(2);
					rs2 = db.execute(sqlStr);
					
					if(rs2.next())
					{
						v = VD.VehicleDetails(rs2.getInt(1))[0];
					}
					else
					{
						v = null;
					}
					
					ps.setCurrentVehicle(v);
					ps.setOccupied(rs.getBoolean(3));
					ps.setColor(rs.getString(4));
					
					spots.add(ps);
				}
			}
		}
		catch(Exception ex)
		{
			throw ex;
		}
	}
	
	public void setParkingLotId(int lId)
	{
		this.lotId = lId;
	}
	
	public int getParkingLotId()
	{
		return this.lotId;
	}
	
	public ParkingSpot[] getSpots()
	{
		return spots.toArray(new ParkingSpot[spots.size()]);
	}
	
	public ParkingSpot getSpotById(int spotId)
	{
		for(int i = 0; i < spots.size(); i++)
		{
			if(spots.get(i).getSpotId() == spotId)
			{
				return spots.get(i);
			}
		}
		
		return null;
	}
}
