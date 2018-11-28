package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDao;
import dao.VehicleDao;

import others.User;
import others.UserDaoImpl;
import pojo.Vehicle;


/**
 * Servlet implementation class Login
 */
@WebServlet("/VehicleController")
public class VehicleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public VehicleController() {}
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
	}
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String submitType = request.getParameter("submit");
		HttpSession session= request.getSession();
		int userid = (int)session.getAttribute("userId");
		

		if(submitType.equals("UpdateVehicleDetails")){
			VehicleDao vehicleDao = new VehicleDao();
			String licnum = request.getParameter("licnum");
			String make = request.getParameter("make");
			String model = request.getParameter("model");
			String year = request.getParameter("year");
			String color = request.getParameter("color");
			
			Vehicle v=new Vehicle();
			v.setOwnerId(userid);
			v.setLicenseNum(licnum);
			v.setMake(make);
			v.setModel(model);
			v.setYear(year);
			v.setColor(color);
			vehicleDao.updateVehicleDetails(v);
			response.sendRedirect("manageaccount.jsp");
		}else if(submitType.equals("Delete")) {
			
			//String licNum = request.getParameter("licnum");
			//String licNum ="ayfgajfg";
			Vehicle v= new Vehicle();
			v.setOwnerId(userid);
			VehicleDao vDao= new VehicleDao();
			try {
				vDao.delete(v);
				response.sendRedirect("manageaccount.jsp");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(submitType.equals("UpdateUserDetails")) {
			UserDaoImpl userDao = new UserDaoImpl();
			String pass = request.getParameter("password");
			String email = request.getParameter("email");
			String name = request.getParameter("name");
			
			User u=new User();
			u.setName(name);
			u.setEmail(email);
			u.setPassword(pass);
			u.setUserId(userid);
			userDao.updateUserDetails(u);
			response.sendRedirect("manageaccount.jsp");
		}
	

	}
	
	
}
