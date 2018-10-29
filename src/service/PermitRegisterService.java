package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.servlet.http.HttpServletRequest;

import permit.PermitDAO;
import permit.PermitImpl;

public class PermitRegisterService {

	private PermitImpl permitImpl;
	private PermitDAO permit;

	public PermitRegisterService() {
		this.permitImpl = new PermitImpl();
	}
	
	public PermitRegisterService withPermit(HttpServletRequest request) throws ParseException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		Double price = Double.parseDouble(request.getParameter("price"));
		String color = request.getParameter("color");
		String date = request.getParameter("date");
		this.permit = PermitDAO.builder(userId, price, color, date);
		return this;
	}
	
	public PermitDAO getPermit() {
		return permit;
	}

	public void setPermit(PermitDAO permit) {
		this.permit = permit;
	}
	
	public boolean invoke() {
		if(permit != null) {
			if(this.permitImpl.create(permit) != null) {	
				return true;
			}
		}
		return false;
	}
	
}
