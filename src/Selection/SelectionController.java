package Selection;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import db.DbManager;

@WebServlet("/SelectionController")
public class SelectionController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	static Connection conn; //connection to the database
	static PreparedStatement ps; //prepared statement for issuing sql queries
	DbManager db = new DbManager(); //DbManager object to initiate connection to database
	
	
	public SelectionController(){}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int parkingLotId = Integer.parseInt(request.getParameter("parkingLotId"));
		int parkingSpotId = Integer.parseInt(request.getParameter("parkingSpotId"));
		HttpSession session = request.getSession();
		String licNum = "FFF123"; //(String)session.getAttribute("licenseNumber");
		String permitColor = "Green"; //(String)session.getAttribute("permitColor");
		
		//attempt to make selection
		try
	    {	
			/*conn = db.getConnection();
			ps = conn.prepareStatement("select colorClass from permit where ownerId=?");
			ps.setInt(1, 12345);
			ResultSet rs = ps.executeQuery();
			conn.close();
			
			rs.next();
			String permitColor = rs.getString(1);*/
			
			Selection S = new Selection(licNum, parkingLotId, parkingSpotId, permitColor);
			
			SelectionAssistant SA = new SelectionAssistant();
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
