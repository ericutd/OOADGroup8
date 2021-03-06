/*
 * Author: Zack Oldham
 * 11/11/2018
 * This class defines a parking spot which contains the information describing a particular parking spot
 */

package others;

import exceptions.ParkingException;

//parkingLot is same idea but has arrayList of spots.   (see below line)

public class ParkingSpot //DOES NOT access the database - will just be given the info that it needs. This class is just a container!
{
	private int spotId = -1;
	private boolean occupied = false;
	private String currentVehicle = "";
	private Color colorClass = null;
	
	
	public ParkingSpot(){}
	
	
	public void setSpotId(int sId)
	{
		this.spotId = sId;
	}
	
	public void setCurrentVehicle(String cV)
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
			this.colorClass = Color.valueOf(c);
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
	
	public String getCurVehicle()
	{
		return this.currentVehicle;
	}
	
	public Color getColor()
	{
		return this.colorClass;
	}
}
