package controllers;
/* Author: Zack Oldham
 * CS 6359.002
 * 10/24/2018
 * This class defines the UnselectController which allows the user to unselect a spot they were parked in and are now leaving 
 */



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import application.SelectionAssistant;

@WebServlet("/UnselectController")
public class UnselectController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	
	
	public UnselectController(){}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String licNum = (String)session.getAttribute("licenseNumber");
		
		try
	    {		
			SelectionAssistant SA = new SelectionAssistant();
			SA.freeSpot(licNum);
		}
		catch(Exception ex)
		{
			request.setAttribute("message", " " + ex);
			request.getRequestDispatcher("selection.jsp").forward(request, response);
		}
			
		request.setAttribute("message", " Success! You have freed your spot!\n"); 
		request.getRequestDispatcher("selection.jsp").forward(request, response);
	}
}
