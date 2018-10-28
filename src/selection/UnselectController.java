package selection;




import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UnselectController")
public class UnselectController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	
	
	public UnselectController(){}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("userId");
		
		try
	    {		
			SelectionAssistant SA = new SelectionAssistant();
			SA.freeSpot(userId);
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
