package Reservation;

/* Author: Zack Oldham
 * CS 6359.002
 * 09/27/2018
 * This class defines the parkingLot which contains an organized list of parkingSpots and performs operations on them. 
 */
import java.sql.Date;

public class Reservation 
{
	private int userId = 0;
	private int parkingLotId = 0;
	private int parkingSpotId = 0;
	private Date reservationDate;
	
	
	public Reservation(){}
	
	public Reservation(int uId, int lId, int sId, String rD)
	{
		this.userId = uId;
		this.parkingLotId = lId;
		this.parkingSpotId = sId;
		this.reservationDate = Date.valueOf(rD);
	}
	
	public int getUserId()
	{
		return this.userId;
	}
	
	public int getParkingLotId()
	{
		return this.parkingLotId;
	}
	
	public int getParkingSpotId()
	{
		return this.parkingSpotId;
	}
	
	public Date getReservationDate()
	{
		return this.reservationDate;
	}
}










