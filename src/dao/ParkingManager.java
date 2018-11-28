package dao;

/* Author: Zack Oldham
 * CS 6359.002
 * 10/24/2018
 * This class defines the SelectionAssistant which can select/free spots and display spots by lot 
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import db.DbManager;
import exceptions.ParkingException;
import others.Color;
import others.ParkingLot;
import others.ParkingSpot;
import pojo.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;


public class ParkingManager 
{
	static Connection conn; //connection to the database
	static PreparedStatement ps; //prepared statement for issuing sql queries
	DbManager db = DbManager.getInstance(); //DbManager object to initiate connection to database
	private ArrayList<ParkingLot> lots; 
	
	
	public ParkingManager() throws ParkingException, SQLException
	{
		try
		{
			ResultSet rs = db.execute("SELECT * FROM parkingLot");
			
			if(!rs.next())
			{
				throw new ParkingException("There are no lots available at this time.");
			}
			else
			{
				this.lots = new ArrayList<>();
				
				int i = 0;
				while(rs.next())
				{
					this.lots.add(new ParkingLot(rs.getInt(i)));
					i++;
				}
			}
		}
		catch(Exception ex)
		{
			throw ex;
		}	
	}
	
	
	/* Method to free the spot a user is leaving
	 * @param: An integer containing the user's id
	 * @return: none
	 * throws ParkingException to indicate errors in attempting to free a spot, or SQL exception in the event of an underlying database error
	 */
	public void unPark(int uId) throws ParkingException, SQLException
	{	
		String sqlStr = "";
		try
		{
			sqlStr = "SELECT * FROM parkingSpot WHERE currentVehicle=(SELECT licenseNum FROM vehicle WHERE ownerId=" + String.valueOf(uId) + ")";
			ResultSet rs = db.execute(sqlStr);
			
			if(!rs.next())
			{
				throw new ParkingException("You have not parked anywhere!");
			}
			else
			{
				sqlStr = "UPDATE parkingSpot SET currentVehicle=NULL AND occupied=False WHERE currentVehicle=(SELECT licenseNum FROM vehicle WHERE ownerId=" + String.valueOf(uId) + ")";
			}
			
		}
		catch(Exception ex)
		{
			throw ex;
		}
	}
	
	/* Method to list the available parking lots
	 * @param: none
	 * @return: an integer array containing all of the parkingLotId's that are available
	 * throws: ParkingLot Exception to alert user if there are no lots, or an SQL exception if there is an underlying database related issue
	 */
	
	//change this up to just return the lot arrayList
	public ParkingLot[] getParkingLots()
	{
		return lots.toArray(new ParkingLot[lots.size()]);
	}
	
	public ParkingSpot[] getLot(int lId, ParkingLot[] lotArr) throws ParkingException
	{
		for(int i = 0; i < lotArr.length; i++)
		{
			if(lotArr[i].getParkingLotId() == lId)
			{
				if(lotArr[i].getSpots() == null)
				{
					throw new ParkingException("That lot is currently unavailable.");
				}
				
				return lotArr[i].getSpots();
			}
		}
		
		return null;
	}
	
	public void park(int userId, String lNum, int lotId, int spotId) throws ParkingException
	{
		VehicleDao VD = new VehicleDao();
		Vehicle v = VD.findByIdNew(userId);
		//Should we still go get vehicle info from vehicleDao? I don't need it since I have the licenseNumber....
		//String lNum = v.getLicenseNum();
		String lId = String.valueOf(lotId);
		String sId = String.valueOf(spotId);
		
		String sqlStr = "SELECT colorClass FROM permit WHERE ownerId=" + String.valueOf(userId);
		
		try
		{
			ResultSet rs = db.execute(sqlStr);
			
			if(!rs.next())
			{
				throw new ParkingException("You must purchase a permit to park!");
			}
			else
			{
				String permit = rs.getString(1);
				try
				{
					Color.valueOf(permit);
				}
				catch(Exception ex)
				{
					throw new ParkingException("You do not have a permit for this spot");
				}
				
				
				sqlStr = "SELECT occupied FROM parkingSpot WHERE parkingLotId=" + lId + " AND parkingSpotId=" + sId;
				rs = db.execute(sqlStr);
				
				rs.next();
				if(rs.getBoolean(1))
				{
					throw new ParkingException("That spot is occupied!");
				}
				else
				{
					sqlStr = "UPDATE parkingSpot SET currentVehicle=" + lNum + ", occupied=True WHERE parkingLotId=" + lId + "AND parkingSpotId=" + sId;
					rs = db.execute(sqlStr);
				}
			}
		}
		catch(Exception ex)
		{
			throw new ParkingException("Parking error");
		}
		
		
	}
	
	
	
	
	
	
}
