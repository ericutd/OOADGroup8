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

import java.sql.SQLException;
import java.util.ArrayList;

import application.reservation.ParkingLotService;
import pojo.ParkingSpot;
import pojo.Permit;
import pojo.Vehicle;
import dao.PermitDAO;

public class ParkingManager 
{
	
	DbManager db = DbManager.getInstance(); //DbManager object query/update
	private ArrayList<ParkingLotService> lots; 
	VehicleDao VD;
	PermitDAO PD;
	
	
	public ParkingManager() throws ParkingException, SQLException
	{
		loadParkingLots();
		VD = new VehicleDao();
		PD = new PermitDAO();
	}
	

	private void loadParkingLots() throws ParkingException, SQLException
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
					ParkingLotService pl = new ParkingLotService();
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
	
	public ParkingLotService getLot(int lId) throws ParkingException
	{
		for(int i = 0; i < this.lots.size(); i++)
		{
			if(this.lots.get(i).getParkingLotId() == lId)
			{
				if(this.lots.get(i).getSpots() == null)
				{
					throw new ParkingException("That lot is currently unavailable.");
				}
				
				return this.lots.get(i);
			}
		}
		
		return null;
	}
	
	private ParkingLotService findLotById(int lId)
	{	
		for(int i = 0; i < this.lots.size(); i++)
		{
			if(this.lots.get(i).getParkingLotId() == lId)
			{
				return this.lots.get(i);
			}
		}
		
		return null;
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
	
	public void park(Vehicle v, int lotId, int spotId) throws ParkingException, SQLException
	{	
		int permitId = v.getPermitId();
		
		Permit P = this.PD.findByIdNew(permitId);
		
		if(P == null)
		{
			throw new ParkingException("You do not have a permit!");
		}
		
		ParkingLotService lot = this.findLotById(lotId);
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
		spot.setCurrentVehicle(v);
		this.editSpot(spot, lotId);
	}
	
	/* Method to free the spot a user is leaving
	 * @param: An integer containing the user's id
	 * @return: none
	 * throws ParkingException to indicate errors in attempting to free a spot, or SQL exception in the event of an underlying database error
	 */
	public void unPark(int userId, int lotId, int spotId) throws ParkingException, SQLException
	{	
		ParkingLotService lot = this.findLotById(lotId);
		ParkingSpot spot = lot.getSpotById(spotId);
		Vehicle currentVehicle = spot.getCurrentVehicle();		
		if(currentVehicle.getOwnerId() != userId)
		{
			throw new ParkingException("You are not parked in this spot!");
		}
		
		
		spot.setCurrentVehicle(null);
		spot.setOccupied(false);
		editSpot(spot, lotId);
	}
	
	
	private void editSpot(ParkingSpot s, int lotId) throws SQLException
	{
		String lNum = s.getCurrentVehicle().getLicenseNum();
		String sqlStr = "UPDATE parkingSpot SET currentVehicle=" + lNum + ", occupied=true WHERE parkingLotId=" + lotId + " AND parkingSpotId=" + s.getSpotId();
		db.executeUpdate(sqlStr);
	}
	
	
	
	
	
	
}
