package permit;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.PermitRegisterService;

@WebServlet("/permit")
public class PermitController extends HttpServlet {
	
	private static final long serialVersionID = -1;
	private PermitRegisterService regService;
	
	
	public PermitController() {
		this.regService = new PermitRegisterService();
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean reponseStatus = false;
		try {
			reponseStatus = this.regService.withPermit(request).invoke();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(reponseStatus) {
			System.out.println("Permit is purchased");
			request.setAttribute("Permit is purchased","");
			request.getRequestDispatcher("successReg.jsp").forward(request, response);
		}
		else {
			System.out.println("Unsuccessful Purchase");
			request.setAttribute("Unsuccessful Purchase","");
			request.getRequestDispatcher("failure.jsp").forward(request, response);
		}
	}
	
	
}
