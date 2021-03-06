package application.reservation;
/* Author: Zack Oldham
 * CS 6359.002
 * 10/24/2018
 * This class defines a Selection which stores relevant information needed to select a spot (validation is done in both this class and SelectionAssistant)
 */

import exceptions.ParkingException;

enum pass {Visitor, Green, Gold, Orange, Purple};

public class Selection 
{
	private String licNum = "";
	private pass colorClass = null;
	private int parkingLotId = 0;
	private int parkingSpotId = 0;
	
	
	public Selection(){}
	
	public Selection(String lN, int lId, int sId, String c) throws ParkingException
	{
		this.licNum = lN;
		this.parkingLotId = lId;
		this.parkingSpotId = sId;
		colorClass = validateColor(c);
	}
	
	public String getLicNum()
	{
		return this.licNum;
	}
	
	public int getParkingLotId()
	{
		return this.parkingLotId;
	}
	
	public int getParkingSpotId()
	{
		return this.parkingSpotId;
	}
	
	public pass getColorClass()
	{
		return colorClass;
	}
	
	//This method determines if the 
	public pass validateColor(String c) throws ParkingException
	{
		if(c.equals("Green"))
		{
			return pass.valueOf(c);
		}
		else if(c.equals("Gold"))
		{
			return pass.valueOf("Orange");
		}
		else if(c.equals("Purple"))
		{
			return pass.valueOf(c);
		}
		else if (c.equals("Visitor"))
		{
			return pass.valueOf(c);
		}
		else
		{
			throw new ParkingException("Invalid Color");
		}
	}
	
	
	
	//This method determines if user can is able to park in the given spot based on their colorClass level.
	//@param String color: the color of the spot in which the user would like to park
	//@return: A boolean value that indicates whether the user is eligible to park in a spot of this color.
	//@except: A ParkingException is thrown if the spot has an invalid color.
	public boolean isEligible(String color) throws ParkingException
	{
		pass input = pass.valueOf(color);
		
		switch(input)
		{
		case Green:
			if(this.colorClass == pass.Visitor)
			{
				return false;
			}
			return true;
		case Gold:
			if(this.colorClass == pass.Green || this.colorClass == pass.Visitor)
			{
				return false;
			}
			return true;
		case Orange: 
			if(this.colorClass == pass.Orange || this.colorClass == pass.Purple)
			{
				return true;
			}
			return false;
		case Purple: 
			if(this.colorClass != pass.Purple)
			{
				return false;
			}
			return true;
		case Visitor:
			if(this.colorClass != pass.Visitor)
			{
				return false;
			}
			return true;
		default: 
				throw new ParkingException("Invalid Color");
		}
	}
}










