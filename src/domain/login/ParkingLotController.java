package domain.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/ParkingLotController")
public class ParkingLotController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ParkingLotController() {}
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int noOfLots = Integer.parseInt(request.getParameter("lot"));
		
		ParkingLotDao plDao=new ParkingLotDao();
		plDao.insertParkingLot(noOfLots);
		response.sendRedirect("admin.jsp");
	}

}
