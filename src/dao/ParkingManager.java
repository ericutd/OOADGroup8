package dao;

/* Author: Zack Oldham
 * CS 6359.002
 * 10/24/2018
 * This class defines the SelectionAssistant which can select/free spots and display spots by lot 
 */


import java.sql.ResultSet;
import db.DbManager;
import exceptions.ParkingException;
import others.Color;
import others.ParkingLot;
import others.ParkingSpot;
import pojo.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;
import pojo.Permit;

public class ParkingManager 
{
	
	DbManager db = DbManager.getInstance(); //DbManager object query/update
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
				
				while(rs.next())
				{
					ParkingLot pl = new ParkingLot();
					pl.setParkingLotId(rs.getInt(1));
					
					this.lots.add(pl);
				}
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
	
	private ParkingLot findLotById(int lId)
	{
		ParkingLot pLots[] = this.getParkingLots();
		
		for(int i = 0; i < pLots.length; i++)
		{
			if(pLots[i].getParkingLotId() == lId)
			{
				return pLots[i];
			}
		}
		
		return null;
	}
	
	
	private int findSpotByUserId(int uId, ParkingSpot s)
	{
		VehicleDao VD = new VehicleDao();
		Vehicle v = VD.findByIdNew(uId);
		String lNum = v.getLicenseNum();
		ParkingLot pLots[] = this.getParkingLots();
		
		for(int i = 0; i < pLots.length; i++)
		{
			ParkingSpot curLot[] = pLots[i].getSpots();
			
			for(int j = 0; j < curLot.length; j++)
			{
				if(curLot[j].getCurVehicle().equals(lNum))
				{
					s = curLot[j];
					return i;
				}
			}
		}
		
		return -1;
	}
	
	private boolean checkIfValidParking(Permit p, ParkingSpot ps)
	{
		Color permColor = Color.valueOf(p);
		
		switch(ps.getColor())
		{
		case VISITOR:
			if(permColor == Color.VISITOR)
			{
				return true;
			}
			
			return false;
		
		case GREEN:
			if(permColor == Color.VISITOR)
			{
				return false;
			}
			
			return true;
			
		case GOLD: 
			if(permColor == Color.VISITOR || permColor == Color.GREEN)
			{
				return false;
			}
			
			return true;
			
		case ORANGE:
			if(permColor == Color.ORANGE || permColor == Color.PURPLE)
			{
				return true;
			}
			
			return false;
			
		case PURPLE:
			if(permColor != Color.PURPLE)
			{
				return false;
			}
			
			return true;
		
		case INVALID_COLOR:
			return false;
		
		default: 
			return false;
		}
	}
	
	public void park(int userId, String lNum, int lotId, int spotId) throws ParkingException
	{		
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
				Permit P = new Permit();
				P.setOwnerId(userId);
				Permit.PermitColor pColor = Permit.PermitColor.valueOf(rs.getString(1));
				P.setPermitColor(pColor);
				
				ParkingLot lot = this.findLotById(lotId);
				ParkingSpot spot = lot.getSpotById(spotId);
				 
				if(!checkIfValidParking(P, spot))
				{
					throw new ParkingException("You do not have an adequate permit for this spot");
				}
				
				if(spot.getOccupied())
				{
					throw new ParkingException("That spot is occupied!");
				}
				
				spot.setOccupied(true);
				spot.setCurrentVehicle(lNum);
				this.editSpot(spot, lotId);
				
			}
		}
		catch(Exception ex)
		{
			throw new ParkingException("Parking error");
		}
		
		
	}
	
	/* Method to free the spot a user is leaving
	 * @param: An integer containing the user's id
	 * @return: none
	 * throws ParkingException to indicate errors in attempting to free a spot, or SQL exception in the event of an underlying database error
	 */
	public void unPark(int uId) throws ParkingException, SQLException
	{	
		ParkingSpot spot = new ParkingSpot();
		int lId = this.findSpotByUserId(uId, spot);
		
		if(lId == -1)
		{
			throw new ParkingException("You have not parked anywhere!");
		}
		
		spot.setCurrentVehicle(null);
		spot.setOccupied(false);
		editSpot(spot, lId);
	}
	
	
	private void editSpot(ParkingSpot s, int lotId) throws SQLException
	{
		String sqlStr = "UPDATE parkingSpot SET currentVehicle=" + s.getCurVehicle() + ", occupied=true WHERE parkingLotId=" + String.valueOf(lotId) + ", AND parkingSpotId=" + s.getSpotId();
		db.update(sqlStr);
	}
	
	
	
	
	
	
}
