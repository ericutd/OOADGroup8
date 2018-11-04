package controllers;
/* Author: Zack Oldham
 * CS 6359.002
 * 10/24/2018
 * This class defines the selectionController which allows the user to select a spot
 */



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import application.SelectionAssistant;
import application.reservation.Selection;

@WebServlet("/SelectionController")
public class SelectionController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	public SelectionController(){}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int parkingLotId = Integer.parseInt(request.getParameter("parkingLotId"));
		int parkingSpotId = Integer.parseInt(request.getParameter("parkingSpotId"));
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("userId");
		String[] usrInfo;
		
		//attempt to make selection
		try
	    {	
			SelectionAssistant SA = new SelectionAssistant();
			usrInfo = SA.getUserInfo(userId);
			Selection S = new Selection(usrInfo[0], parkingLotId, parkingSpotId, usrInfo[1]);
			
			SA.selectSpot(S);
		}
		catch(Exception ex)
		{
			request.setAttribute("message", " " + ex);
			request.getRequestDispatcher("select.jsp").forward(request, response);
		}
			
		request.setAttribute("message", " Success! You have occupied spot number " + parkingSpotId + "in lot number " + parkingLotId + "."); 
		request.getRequestDispatcher("successReserve.jsp").forward(request, response);
	}
}