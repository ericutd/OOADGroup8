package controllers;
/* Author: Zack Oldham
 * CS 6359.002
 * 10/24/2018
 * This class defines the displayLotsController which displays all available parking lots 
 */



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.SelectionAssistant;

@WebServlet("/displayLotsController")
public class displayLotsController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	
	
	public displayLotsController(){}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		try
	    {		
			SelectionAssistant SA = new SelectionAssistant();
			int lots[] = SA.listLots();
			
			request.setAttribute("message", "LotId\n");
			request.getRequestDispatcher("select.jsp").forward(request, response);
			
			for(int i = 0; i < lots.length; i++)
			{
				request.setAttribute("message", lots[i] + "\n");
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
