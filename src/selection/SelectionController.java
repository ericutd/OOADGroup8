package selection;




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
		int parkingLotId = Integer.parseInt(request.getParameter("lotId"));
		int parkingSpotId = Integer.parseInt(request.getParameter("spotId"));
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("userId");
		//is there a session variable for the permitColor?
		
		//attempt to make selection
		try
	    {	
			conn = db.getConnection();
			ps = conn.prepareStatement("select colorClass from permit where ownerId=?");
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			
			String permitColor = rs.getString(1);
			
			Selection S = new Selection(userId, parkingLotId, parkingSpotId, permitColor);
			
			SelectionAssistant SA = new SelectionAssistant();
			SA.selectSpot(S);
		}
		catch(Exception ex)
		{
			request.setAttribute("message", " " + ex);
			request.getRequestDispatcher("selection.jsp").forward(request, response);
		}
			
		request.setAttribute("message", " Success! You have occupied spot number " + parkingSpotId + "in lot number " + parkingLotId + "."); 
		request.getRequestDispatcher("selection.jsp").forward(request, response);
	}
}
