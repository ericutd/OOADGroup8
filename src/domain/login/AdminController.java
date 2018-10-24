package domain.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public AdminController() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ParkingSpotsDao  psDao = new CategoryDAO();

		try {

		    List<int> listId = psDao.list();
		    request.setAttribute("listId", listId);

		    RequestDispatcher dispatcher = request.getRequestDispatcher("Admin.jsp");
		    dispatcher.forward(request, response);

		} catch (SQLException e) {
		    e.printStackTrace();
		    throw new ServletException(e);
        }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int number = Integer.parseInt(request.getParameter("id"));
		String colorPass = request.getParameter("permit");
		ParkingSpots spot = new 
	}

}
