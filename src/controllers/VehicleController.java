package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.VehicleDao;
import others.Vehicle;

/**
 * Servlet implementation class Login
 */
@WebServlet("/VehicleController")
public class VehicleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public VehicleController() {}
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		VehicleDao vehicleDao = new VehicleDao();
		HttpSession session= request.getSession();
		int userid = (int)session.getAttribute("userId");
		String licnum = request.getParameter("licnum");
		String make = request.getParameter("make");
		String model = request.getParameter("model");
		String year = request.getParameter("year");
		String color = request.getParameter("color");
		
		Vehicle v=new Vehicle();
		v.setOwnerid(userid);
		v.setLicenseNum(licnum);
		v.setMake(make);
		v.setModel(model);
		v.setYear(year);
		v.setColor(color);
		vehicleDao.updateVehicleDetails(v);
		response.sendRedirect("manageaccount.jsp");

	}

}
