package application.permit;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PermitDAO;
import helpers.Builder;
import pojo.Permit;
import pojo.Permit.PermitColor;

public class PermitService {
	
	public PermitService() {
		
	}
	
	public void addPermit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session= request.getSession();
		int userId = (int)session.getAttribute("userId");
		double price = Double.valueOf(request.getParameter("price"));
		PermitColor color = PermitColor.valueOf(request.getParameter("color"));
		
		Permit permit = Builder.of(Permit::new)
				.with(Permit::setOwnerId, userId)
				.with(Permit::setPrice, price)
				.with(Permit::setPermitColor, color)
				.build();
		
		PermitDAO permitDAO = new PermitDAO();
		permitDAO.insert(permit);
		
		System.out.println("Added new Permit");
		response.sendRedirect("welcome.jsp");
	}

}
