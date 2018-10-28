
/* Author: Zack Oldham
 * CS 6359.002
 * 09/29/2018
 * This class defines the parkingLot which contains an organized list of parkingSpots and performs operations on them. 
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ReservationAssistant 
{
	static Connection conn; //connection to the database
	static PreparedStatement ps; //prepared statement for issuing sql queries
	DbManager db = new DbManager(); //DbManager object to initiate connection to database
	
	private final int maxCap = 200; //maximum number of spots in any given lot
	private final int numLots = 15; //number of parking lots
	private final double price = 20.00; //constant cost of $20 (for now)
	private final int maxReservations = 5; //maximum reservations per person (for now)
	
	ReservationAssistant(){}
	
	
	/* Method to reserve a spot for a user
	 * @param A Reservation object holding the information of the person wishing to reserve a spot and lot/spot numbers 
	 * @return an integer to indicate number of rows changed (0 or 1 in this case)
	 * throws ReservationException to indicate errors in attempting to reserve spot 
	 */
	public int createReservation(Reservation r) throws ReservationException
	{
		//check that entered spot and lot IDs are in correct ranges
		if(r.getParkingSpotId() < 0 || r.getParkingSpotId() > maxCap)
		{
			throw new ReservationException("Invalid Reservation Exception: spot number must be between 1 and 200");
		}
		else if(r.getParkingLotId() < 0 || r.getParkingLotId() > numLots)
		{
			throw new ReservationException("Invalid Reservation Exception: lot number must be between 1 and 15");
		}
		
		int status = 0;
		
		try
		{
			conn = db.getConnection();
			
			//check that user has not already maximum number of reservations
			ps = conn.prepareStatement("select * from parkingSpot where reserverId=?");
			ps.setInt(1, r.getUserId());
			ResultSet rs = ps.executeQuery();
			rs.last();
			if(rs.getRow() == maxReservations)
			{
				throw new ReservationException("Invalid Reservation Exception: You may only reserve five spots at one time");
			}
			
			//grab the spot the user wishes to reserve
			ps = conn.prepareStatement("select reserved from parkingSpot where parkingLotId=? AND parkingSpotId=?");
			ps.setInt(1, r.getParkingLotId());
			ps.setInt(2, r.getParkingSpotId());
			rs = ps.executeQuery();
			
			//if the spot didn't already exist, go ahead and insert it with proper values and mark as reserved
			if(!rs.next())
			{
				ps = conn.prepareStatement("insert into parkingSpot values ( ?, ?, ?, ?, ?, true )");
				ps.setInt(1, r.getParkingLotId());
				ps.setInt(2, r.getUserId());
				ps.setInt(3, r.getParkingSpotId());
				ps.setDouble(4, price);
				ps.setDate(5, r.getReservationDate());
				status = ps.executeUpdate();
			}//if the spot did already exist, but wasn't reserved, update its information accordingly and mark as reserved. 
			else if(!rs.getBoolean(1))
			{
				ps = conn.prepareStatement("update parkingSpot reserverId=?, reservedDate=?, reserved=true where parkingLotId=? AND parkingSpotId=?");
				ps.setInt(1, r.getUserId());
				ps.setDate(2, r.getReservationDate());
				ps.setInt(3, r.getParkingLotId());
				ps.setInt(4, r.getParkingSpotId());
				status = ps.executeUpdate();
			}
			else //otherwise throw an exception stating that the spot was taken
			{
				throw new ReservationException("Invalid Reservation Exception: Lot " + r.getParkingLotId() + " spot " + r.getParkingSpotId() + " is already reserved");
			}
			
			conn.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		
		return status;
	}
	
	/* Method to retrieve all reservations made by one user.
	 * @param: the userId of the user who wishes to retrieve their reservations, and an array to hold the output of the query
	 * @return: an integer to indicate number of rows retrieved. 
	 */
	public int getReservations(int uId, String output[][])
	{
		int row = 0;
		
		try
		{
			//select all parking spots corresponding to given userId
			conn = db.getConnection();
			ps = conn.prepareStatement("select * from parkingSpot where reserverId=?");
			ps.setInt(1, uId);
			ResultSet rs = ps.executeQuery();
			
			//While there are rows to be read, store them into the output array
			while(rs.next())
			{	
				for(int j = 0; j < 6; j++)
				{
					output[row][j] = rs.getString(j+1);
				}
					
				row++;
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		
		return row;
	}
	
	
	/* Method to cancel a reservation for a user
	 * @param: A reservation object containing the information of the reservation to be canceled
	 * @return: An integer indicating the number of rows changed (0 or 1 in this case)
	 * throws ReservationException to indicate errors in attempting to cancel a reservation
	 */
	public int cancelReservation(Reservation r) throws ReservationException
	{
		int status = 0;
		
		try
		{
			conn = db.getConnection();
			ps = conn.prepareStatement("select reserverId from parkingSpot where parkingLotId=? AND parkingSpotId=? AND reserved=true");
			ps.setInt(1, r.getParkingLotId());
			ps.setInt(2, r.getParkingSpotId());
			ResultSet rs = ps.executeQuery();
			
			//check that the user is trying to cancel a spot that they reserved and that the spot they are attempting to free is actually reserved at all
			if(!rs.next() || rs.getInt(1) != r.getUserId())
			{
				throw new ReservationException("Reservation Exception: You may not cancel a reservation on a spot you did not reserve");
			}
			else //if both conditions are true then update the spot's reserved flag to false
			{
				ps = conn.prepareStatement("update parkingSpot set reserved=false where parkingLotId=? AND parkingSpotId=?");
				ps.setInt(1, r.getParkingLotId());
				ps.setInt(2, r.getParkingSpotId());
				status = ps.executeUpdate();
			}
			
			conn.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		return status;
	}
	
}
