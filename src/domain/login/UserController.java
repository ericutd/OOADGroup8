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
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UserController() {}
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("I am in User Controller");
		UserDaoImpl userDao = new UserDaoImpl();
		HttpSession session= request.getSession();
		int userid = (int)session.getAttribute("userId");
		String pass = request.getParameter("password");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		
		User u=new User();
		u.setName(name);
		u.setEmail(email);
		u.setPassword(pass);
		u.setUserid(userid);
		userDao.updateUserDetails(u);
		response.sendRedirect("manageaccount.jsp");
	}

}
