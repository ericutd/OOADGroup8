package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import application.login.Login;
import dao.UserDao;
import dao.VehicleDao;
import others.User;
import others.UserDaoImpl;
import others.Vehicle;

/**
 * Servlet implementation class Login
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginController() {}
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserDao customerDao = new UserDaoImpl();		
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String submitType = request.getParameter("submit");
		Login login = new Login(email, pass);	
		
		UserDaoImpl uDao = new UserDaoImpl();
		int userid = uDao.getUserId(email);
		HttpSession session = request.getSession();
		session.setAttribute("userId", userid);
		
		User c = customerDao.validateUser(login);
		
		if(submitType.equals("login") && c!=null && c.getName()!=null){
			request.setAttribute("message", "Hello "+c.getName());
			VehicleDao vehicleDao = new VehicleDao();
			Vehicle[] v = vehicleDao.VehicleDetails(userid);
			session.setAttribute("acctType", c.getAccounttype());
			ArrayList<String> vehicleDetails= new ArrayList<>();
			ArrayList<String> licenseNum= new ArrayList<>();
			
			for(int i=0;i<v.length;i++) {
				vehicleDetails.add(v[i].getMake()+" "+v[i].getModel());
				licenseNum.add(v[i].getLicenseNum());
			}
			request.setAttribute("vehicleDetails", vehicleDetails);
			request.setAttribute("licenseNum", licenseNum);
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
		}else if(submitType.equals("register")){
			c.setName(request.getParameter("name"));
			c.setPassword(request.getParameter("password"));
			c.setEmail(request.getParameter("email"));
			c.setAccounttype(request.getParameter("dropdown"));
			customerDao.register(c);
			userid = uDao.getUserId(email);
			Vehicle v=new Vehicle();
			VehicleDao vDao= new VehicleDao();
			v.setLicenseNum(request.getParameter("licnum"));
			v.setMake(request.getParameter("make"));
			v.setModel(request.getParameter("model"));
			v.setYear(request.getParameter("year"));
			v.setColor(request.getParameter("color"));
			vDao.register(v,userid);
			request.setAttribute("successMessage", "Registration done, please login!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else{
			request.setAttribute("message", "Data Not Found! Please register!");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}

	}

}
