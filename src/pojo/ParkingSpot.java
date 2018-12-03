/*
 * Author: Zack Oldham
 * 11/11/2018
 * This class defines a parking spot which contains the information describing a particular parking spot
 */

package pojo;

import exceptions.ParkingException;
import others.Color;


//parkingLot is same idea but has arrayList of spots.   (see below line)

public class ParkingSpot //DOES NOT access the database - will just be given the info that it needs. This class is just a container!
{
	private int spotId = -1;
	private boolean occupied = false;
	private Vehicle currentVehicle;
	private Color colorClass = null;
	private int lotId;

	public ParkingSpot(){}
	
	public int getLotId() {
		return lotId;
	}


	public void setLotId(int lotId) {
		this.lotId = lotId;
	}

	
	
	public void setSpotId(int sId)
	{
		this.spotId = sId;
	}
	
	public void setCurrentVehicle(Vehicle cV)
	{
		this.currentVehicle = cV;
	}
	
	public void setOccupied(boolean o)
	{
		this.occupied = o;
	}
	
	public void setColor(String c) throws ParkingException
	{
		try
		{
			this.colorClass = Color.getColor(c);
		}
		catch(Exception ex)
		{
			throw new ParkingException("Invalid spot color"); 
		}
	}
	
	
	public int getSpotId()
	{
		return this.spotId;
	}
	
	public boolean getOccupied()
	{
		return this.occupied;
	}
	
	public Vehicle getCurrentVehicle()
	{
		return this.currentVehicle;
	}
	
	public Color getColor()
	{
		return this.colorClass;
	}
}
