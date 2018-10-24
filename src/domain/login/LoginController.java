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
		Login login = new Login(username, pass);
		User c = customerDao.validateUser(login);
		
		//int userid= getUserId(email);
		HttpSession session = request.getSession();
		session.setAttribute("userId", userid);

		if(submitType.equals("login") && c!=null && c.getName()!=null){
			request.setAttribute("message", "Hello "+c.getName());
			VehicleDao vehicleDao = new VehicleDao();
			Vehicle v = vehicleDao.VehicleDetails(login);
			request.setAttribute("vehicleDetails", v.getMake() + " "+ v.getModel());
			request.setAttribute("licenseNum", v.getLicenseNum());
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
		}else if(submitType.equals("register")){
			c.setUserid(Integer.parseInt(request.getParameter("userid")));
			c.setName(request.getParameter("name"));
			c.setPassword(request.getParameter("password"));
			c.setEmail(request.getParameter("email"));
			c.setAccounttype(request.getParameter("dropdown"));
			customerDao.register(c);
			Vehicle v=new Vehicle();
			VehicleDao vDao= new VehicleDao();
			v.setLicenseNum(request.getParameter("licnum"));
			v.setMake(request.getParameter("make"));
			v.setModel(request.getParameter("model"));
			v.setYear(request.getParameter("year"));
			v.setColor(request.getParameter("color"));
			vDao.register(v, Integer.parseInt(request.getParameter("userid")));
			request.setAttribute("successMessage", "Registration done, please login!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else{
			request.setAttribute("message", "Data Not Found! Please register!");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}

	}

}
