package domain.login;
import java.io.IOException;
import java.sql.SQLException;

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
		System.out.println("here");
		AdminDao  psDao = new AdminDao();
		try {
			List<Integer> listId = psDao.list();
			request.setAttribute("listId", listId);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Admin.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Admin spot = new Admin();
		AdminDao psDao = new AdminDao();
		int noOfSpots= Integer.parseInt(request.getParameter("number"));
		spot.setParkingLotId(Integer.parseInt(request.getParameter("id")));
		String colorClass=request.getParameter("permit");
		spot.setColorPass(colorClass);
		psDao.Add(spot, noOfSpots);
		request.getRequestDispatcher("Admin.jsp").forward(request, response);
	}
}