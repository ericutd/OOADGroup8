package controllers;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDao;
import others.Admin;

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
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noOfLots = Integer.parseInt(request.getParameter("lot"));	
		AdminDao psDao = new AdminDao();
		psDao.insertParkingLot(noOfLots);
		
		Admin spot = new Admin();
		int lotId= psDao.getLotId();
		
		while(noOfLots>0) {
			int noOfSpots= Integer.parseInt(request.getParameter("spotnumber"));
			String colorClass=request.getParameter("permit");
			spot.setParkingLotId(lotId);
			spot.setColorPass(colorClass);
			psDao.insertParkingSpot(spot, noOfSpots);
			noOfLots--;
			lotId--;
		}
		
		request.getRequestDispatcher("admin.jsp").forward(request, response);
	}
}