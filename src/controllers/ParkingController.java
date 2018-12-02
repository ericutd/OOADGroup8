package controllers;

/* Author: Zack Oldham
 * CS 6359.002
 * 10/24/2018
 * This class defines the ParkingController which allows the user to park/unpark and view spots.
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.ParkingManager;
import dao.VehicleDao;
import others.ParkingLot;
import others.ParkingSpot;
import pojo.Vehicle;


@WebServlet("/SelectionController")
public class ParkingController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	public ParkingController(){}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("userId");
		String action = request.getParameter("action"); //hidden parameter that is set based on the action desired by user
		int lotId = 0;
		int spotId = 0;
	
		try
		{
			ParkingManager PM = new ParkingManager();
			
			if(action.equals("viewSpots"))
			{
				ParkingLot[] lots = PM.getParkingLots();
				
				request.setAttribute("message", "LotId:\n");
				request.getRequestDispatcher("select.jsp").forward(request, response);
				
				for(int i = 0; i < lots.length; i++)
				{
					request.setAttribute("message", lots[i].getParkingLotId() + "\n");
					request.getRequestDispatcher("select.jsp").forward(request, response);
				}
				
				lotId = Integer.parseInt(request.getParameter("parkingLotId"));
				
				ParkingSpot[] selectedLot = PM.getLot(lotId, lots);
				
				if(selectedLot == null)
				{
					request.setAttribute("message", "No such parking lot\n");
					request.getRequestDispatcher("select.jsp").forward(request, response);
				}
				else
				{
					for(int j = 0; j < selectedLot.length; j++)
					{
						//create pojo for parking --> makes sending info to siva easier
						request.setAttribute("message", selectedLot[j].getSpotId() + "\t");
						request.getRequestDispatcher("select.jsp").forward(request, response);
						request.setAttribute("message", selectedLot[j].getColor() + "\t");
						request.getRequestDispatcher("select.jsp").forward(request, response);
						request.setAttribute("message", selectedLot[j].getOccupied() + "\n");
						request.getRequestDispatcher("select.jsp").forward(request, response);
					}
					
					spotId = Integer.parseInt(request.getParameter("parkingSpotId"));
				}
			}
			else if(action.equals("park"))
			{
				
				VehicleDao VD = new VehicleDao();
				Vehicle vehicles[] = VD.VehicleDetails(userId);
				if(vehicles != null)
				{
					request.setAttribute("message", "Select a vehicle by entering its license plate number\n");
					request.getRequestDispatcher("successReserve.jsp").forward(request, response);
					
					for(int i = 0; i < vehicles.length; i++)
					{
						request.setAttribute("message", (i+1) + ": " + vehicles[i].getLicenseNum());
						request.getRequestDispatcher("selection.jsp").forward(request, response);
					}
					
					
					String lNum = request.getParameter("LicenseNum");
					Vehicle v = vehicles[0];
					PM.park(v, lotId, spotId);
					
				}
				else
				{
					request.setAttribute("message", "You have no registered vehicles!\n");
				}
					
				request.setAttribute("message", " Success! You have occupied spot number " + spotId + "in lot number " + lotId + "."); 
				request.getRequestDispatcher("successReserve.jsp").forward(request, response);
			}
			else if(action.equals("unpark"))
			{	
				lotId = Integer.parseInt(request.getParameter("parkingLotId"));
				spotId = Integer.parseInt(request.getParameter("parkingSpotId"));
				
				PM.unPark(userId, lotId, spotId);
					
				request.setAttribute("message", " Success! You have freed your spot!\n"); 
				request.getRequestDispatcher("selection.jsp").forward(request, response);
			}
			else //error
			{
				request.setAttribute("message", "Error: Invalid Action");
				request.getRequestDispatcher("select.jsp").forward(request, response);
			}
		}
		catch(Exception ex)
		{
			request.setAttribute("message", " " + ex);
			request.getRequestDispatcher("select.jsp").forward(request, response);
		}
	}
}
