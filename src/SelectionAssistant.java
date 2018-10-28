package Selection;

/* Author: Zack Oldham
 * CS 6359.002
 * 10/24/2018
 * This class defines the SelectionAssistant which can select/free spots and display spots by lot 
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import db.DbManager;
import java.sql.SQLException;


public class SelectionAssistant 
{
	static Connection conn; //connection to the database
	static PreparedStatement ps; //prepared statement for issuing sql queries
	DbManager db = new DbManager(); //DbManager object to initiate connection to database
	
	private final int maxCap = 200; //maximum number of spots in any given lot
	private final int numLots = 15; //number of parking lots
	
	
	SelectionAssistant(){}
	
	
	/* Method to reserve a spot for a user
	 * @param A Reservation object holding the information of the person wishing to reserve a spot and lot/spot numbers 
	 * @return an integer to indicate number of rows changed (0 or 1 in this case)
	 * throws ReservationException to indicate errors in attempting to reserve spot 
	 */
	public void selectSpot(Selection s) throws ParkingException
	{
		//check that entered spot and lot IDs are in correct ranges
		if(s.getParkingSpotId() < 0 || s.getParkingSpotId() > maxCap)
		{
			throw new ParkingException("Invalid Parking Exception: spot number must be between 1 and 200");
		}
		else if(s.getParkingLotId() < 0 || s.getParkingLotId() > numLots)
		{
			throw new ParkingException("Invalid Parking Exception: lot number must be between 1 and 15");
		}
		
		try
		{
			conn = db.getConnection();
			
			//grab the spot the user wishes to reserve
			ps = conn.prepareStatement("SELECT occupied, colorClass From parkingSpot WHERE parkingLotId=? AND parkingSpotId=?");
			ps.setInt(1, s.getParkingLotId());
			ps.setInt(2, s.getParkingSpotId());
			ResultSet rs = ps.executeQuery();
			
			if(!rs.next())
			{
				throw new ParkingException("Invalid parking lot or parking lot");
			}
			
			boolean occupied = rs.getBoolean(1);
			//if spot was not taken, and the user has the right pass to park in that spot, reserve it for the user. 
			if(!occupied && s.isEligible(rs.getString(2)))
			{
				ps = conn.prepareStatement("UPDATE parkingSpot SET currentVehicle=?, occupied=True WHERE parkingLotId=? AND parkingSpotId=?");
				ps.setString(1, s.getLicNum());
				ps.setInt(2, s.getParkingLotId());
				ps.setInt(3, s.getParkingSpotId());
				ps.executeUpdate();
			}
			else if(occupied) //otherwise throw an exception stating that the spot was taken
			{
				throw new ParkingException("Invalid Parking Exception: Lot " + s.getParkingLotId() + " spot " + s.getParkingSpotId() + " is already Occupied");
			}
			else
			{
				throw new ParkingException("Invalid Parking Exception: You are not eligible to park in this colorClass.");
			}
			
			conn.close();
		}	
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
	
	
	/* Method to free the spot a user is leaving
	 * @param: An integer containing the user's id
	 * @return: none
	 * throws ParkingException to indicate errors in attempting to free a spot, or SQL exception in the event of an underlying database error
	 */
	public void freeSpot(String licNum) throws ParkingException, SQLException
	{	
		try
		{
			conn = db.getConnection();
			ps = conn.prepareStatement("SELECT occupied FROM parkingSpot WHERE currentVehicle=?");
			ps.setString(1, licNum);
			ResultSet rs = ps.executeQuery();
			
			if(!rs.next())
			{
				throw new ParkingException("Parking Exception: You have no spot to free");
			}
			
			ps = conn.prepareStatement("UPDATE parkingSpot SET currentVehicle=NULL, occupied=False where currentVehicle=?");
			ps.setString(1,licNum);
			ps.executeUpdate();
			
			conn.close();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	/* Method to list the available parking lots
	 * @param: none
	 * @return: an integer array containing all of the parkingLotId's that are available
	 * throws: ParkingLot Exception to alert user if there are no lots, or an SQL exception if there is an underlying database related issue
	 */
	public int[] listLots() throws ParkingException, SQLException
	{
		try
		{
			conn = db.getConnection();
			ps = conn.prepareStatement("SELECT * FROM parkingLot");
			ResultSet rs = ps.executeQuery();
			conn.close();
			
			if(!rs.next())
			{
				throw new ParkingException("There are no lots available at this time.");
			}
			else
			{
				rs.last();
				int output[] = new int[rs.getRow()];
				rs.beforeFirst();
				
				int i = 0;
				
				while(rs.next())
				{
					output[i] = rs.getInt(1);
					i++;
				}
				return output;
			}
			
		}
		catch(Exception ex)
		{
			throw ex;
		}
	}
	
	/* Method to list all of the parking spots in a specific lot
	 * @param: the lotId of the selected lot
	 * @return: An array of strings that holds the parkingSpotIds and their associated colorClass
	 * throws: A parking exception to indicate that either the lot was empty or did not exist. Can also throw SQL exception in the event of underlying database error
	 */
	
	public String[][] listSpots(int lotId) throws ParkingException, SQLException
	{
		try
		{
			conn = db.getConnection();
			ps = conn.prepareStatement("SELECT parkingSpotId, ColorClass FROM parkingSpot where parkingLotId=?");
			ps.setInt(1, lotId);
			ResultSet rs = ps.executeQuery();
			
			if(!rs.next())
			{
				throw new ParkingException("Empty or non-existent parking lot");
			}
			else
			{
				rs.last();
				String output[][] = new String[rs.getRow()][2];
				rs.beforeFirst();
				
				int i = 0;
				
				while(rs.next())
				{
					output[i][0] = String.valueOf(rs.getInt(1));
					output[i][1] = rs.getString(2);
				}
				
				return output;
			}
		}
		catch(Exception ex)
		{
			throw ex;
		}
	}
	
	
	
	
}
