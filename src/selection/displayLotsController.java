package selection;




import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			
		request.setAttribute("message", " Success! You have freed your spot!\n"); 
		request.getRequestDispatcher("select.jsp").forward(request, response);
	}
}
