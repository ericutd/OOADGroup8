package Selection;
/* Author: Zack Oldham
 * CS 6359.002
 * 10/24/2018
 * This class defines the displaySpotsController which displays all spots in the lot of the user's choice. 
 */



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/displaySpotsController")
public class displaySpotsController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	
	
	public displaySpotsController(){}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int lotId = Integer.parseInt(request.getParameter("lotId"));
		
		try
	    {		
			SelectionAssistant SA = new SelectionAssistant();
			String spots[][] = SA.listSpots(lotId);
			
			request.setAttribute("message", "SpotId\tColor\n");
			request.getRequestDispatcher("select.jsp").forward(request, response);
			
			for(int i = 0; i < spots.length; i++)
			{
				request.setAttribute("message", spots[i][0] + "\t");
				request.getRequestDispatcher("select.jsp").forward(request, response);
				request.setAttribute("message", spots[i][1] + "\n");
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
