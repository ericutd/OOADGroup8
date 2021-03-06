package application.permit;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PermitDAO;
import dao.VehicleDao;
import helpers.Builder;
import pojo.Permit;
import pojo.Permit.PermitColor;
import pojo.Vehicle;

public class PermitService {
	
	public PermitService() {
		
	}
	
	public void addPermit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session= request.getSession();
		int userId = (int)session.getAttribute("userId");
		double price = Double.valueOf(request.getParameter("price"));
		String date = request.getParameter("exp_date");
		PermitColor color = PermitColor.valueOf(request.getParameter("color"));
		
		Permit permit = Builder.of(Permit::new)
				.with(Permit::setOwnerId, userId)
				.with(Permit::setPrice, price)
				.with(Permit::setPermitColor, color)
				.with(Permit::setExpDate, date)
				.build();
		
		PermitDAO permitDAO = new PermitDAO();
		permit = permitDAO.insert(permit);
		
		VehicleDao vehicleDAO = new VehicleDao();
		List<Vehicle> vehicle = vehicleDAO.findById(userId);
		for(Vehicle v : vehicle) {
			v.setPermitId(permit.getPermitId());
			vehicleDAO.updatePermit(v);
		}
		
		System.out.println("Added new Permit");
		response.sendRedirect("welcome.jsp");
	}

}
