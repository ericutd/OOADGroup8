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
import com.google.gson.Gson;

import dao.AdminDao;
import dao.VehicleDao;
import others.Vehicle;

/**
 * Servlet implementation class Login
 */
@WebServlet("/VehicleController")
public class VehicleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public VehicleController() {}
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session= request.getSession();
		int userid = (int)session.getAttribute("userId");
    	VehicleDao  vDao = new VehicleDao();
    	Vehicle v= new Vehicle();
		v= vDao.findById(userid);
		System.out.println("Inside Get");
		System.out.println(request.getParameter("msg"));
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		//PrintWriter out= response.getWriter();
		//out.print(v);
		//out.flush();
		//out.close();
		response.getWriter().write(new Gson().toJson(v));
		//response.sendRedirect("manageaccount.jsp");
	}
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String submitType = request.getParameter("submit");
		
		if(submitType.equals("UpdateVehicleDetails")){
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
		}else if(submitType.equals("Delete")) {
			//String licNum = request.getParameter("licnum");
			String licNum ="ayfgajfg";
			Vehicle v= new Vehicle();
			v.setLicenseNum(licNum);
			VehicleDao vDao= new VehicleDao();
			try {
				vDao.delete(v);
				response.sendRedirect("manageaccount.jsp");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	

	}

}
