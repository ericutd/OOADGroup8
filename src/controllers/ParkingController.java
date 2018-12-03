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

import application.reservation.ParkingLotService;
import dao.ParkingManager;
import dao.VehicleDao;
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
		String action = request.getParameter("reserveSubmit"); //hidden parameter that is set based on the action desired by user
		int lotId = 0;
		int spotId = 0;
	
		try
		{
			ParkingManager PM = new ParkingManager();
			
			if(action.equals("viewSpots"))
			{	
				lotId = Integer.parseInt(request.getParameter("parkingLotId"));
				
				ParkingLotService selectedLot = PM.getLot(lotId);
				
				if(selectedLot == null)
				{
					request.setAttribute("message", "No such parking lot\n");
					request.getRequestDispatcher("select.jsp").forward(request, response);
				}
				else
				{
					spotId = Integer.parseInt(request.getParameter("parkingSpotId"));
				}
			}
			else if(action.equals("park"))
			{
				
				VehicleDao VD = new VehicleDao();
				Vehicle vehicles[] = VD.VehicleDetails(userId);
				lotId = Integer.parseInt(request.getParameter("parkingLotId"));
				spotId = Integer.parseInt(request.getParameter("parkingSpotId"));
				
				String lNum = request.getParameter("LicenseNum");
				Vehicle v = vehicles[0];
				PM.park(v, lotId, spotId);
					
					
				request.setAttribute("message", " Success! You have occupied spot number " + spotId + "in lot number " + lotId + "."); 
				request.getRequestDispatcher("successReserve.jsp").forward(request, response);
			}
			else if(action.equals("unpark"))
			{	
				lotId = Integer.parseInt(request.getParameter("parkingLotId"));
				spotId = Integer.parseInt(request.getParameter("parkingSpotId"));
				
				PM.unPark(userId, lotId, spotId);
					
				request.setAttribute("message", " Success! You have freed your spot!\n"); 
				request.getRequestDispatcher("welcome.jsp").forward(request, response);
			}
			else //error
			{
				request.setAttribute("message", "Error: Invalid Action");
				request.getRequestDispatcher("select.jsp").forward(request, response);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			request.setAttribute("message", " " + ex);
			request.getRequestDispatcher("select.jsp").forward(request, response);
		}
	}
}
