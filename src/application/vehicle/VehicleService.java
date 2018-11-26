package application.vehicle;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.VehicleDao;
import pojo.Vehicle;

public class VehicleService {

	private VehicleDao dao;
	
	public VehicleService() {
		dao = new VehicleDao();
	}
	
	public List<Vehicle> getVehicles(int userId) {
		return dao.findById(userId);		
	}
	
}
