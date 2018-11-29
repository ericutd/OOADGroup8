package dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import db.DbManager;
import helpers.Builder;
import interfaces.ICRUDOperations;
import others.Vehicle;


public class VehicleDao implements ICRUDOperations<Vehicle> {
	static Connection conn;
	static PreparedStatement ps;
	
	private DbManager dbManager;
	
	public VehicleDao() {
		this.dbManager = DbManager.getInstance();
	}

	public Vehicle[] VehicleDetails(int userid) {
		ArrayList<Vehicle> vi= new ArrayList<>();
		try{
			conn = DbManager.getInstance().getConnection();
			ps =conn.prepareStatement("select make,model,licenseNum from vehicle where ownerId=?");
			ps.setInt(1, userid);

			ResultSet rs = ps.executeQuery();
			
				while(rs.next()){
					Vehicle v = new Vehicle();
					v.setMake(rs.getString(1));
					v.setModel(rs.getString(2));
					v.setLicenseNum(rs.getString(3));
					vi.add(v);
				}
				
			conn.close();
			
		}catch(Exception e){
			System.out.println(e);
		}
		return vi.toArray(new Vehicle[vi.size()]);
	}
	
	public int register(Vehicle v,int userId) {
		int status = 0;
		try{
			conn = DbManager.getInstance().getConnection();
			ps =conn.prepareStatement("insert into vehicle(ownerId,licenseNum,make,model,year,color) values(?,?,?,?,?,?)");
			ps.setInt(1, userId);
			ps.setString(2, v.getLicenseNum());
			ps.setString(3, v.getMake());
			ps.setString(4, v.getModel());
			ps.setString(5,v.getYear());
			ps.setString(6, v.getColor());
			status = ps.executeUpdate();
			conn.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return status;
	}
	
	public void updateVehicleDetails(Vehicle v) {
		try{
			conn = DbManager.getInstance().getConnection();
			ps =conn.prepareStatement("update vehicle set licenseNum=?, make=?, model= ?, year=?, color=? where ownerId=?");
			ps.setString(1, v.getLicenseNum());
			ps.setString(2, v.getMake());
			ps.setString(3, v.getModel());
			ps.setString(4, v.getYear());
			ps.setString(5, v.getColor());
			ps.setInt(6, v.getOwnerid());
			ps.executeUpdate();
			conn.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	Field[] fields = Vehicle.class.getFields();
	
	@Override
	public Vehicle insert(Vehicle obj) {		
		StringBuilder insertSql = new StringBuilder();
		insertSql.append("INSERT INTO VEHICLE (ownerId,licenseNum,make,model,year,color) VALUES (");
		try {
			// construction phase
			for(Field f : fields) {
					insertSql.append(f.get(obj) + ",");
			}
			insertSql.setLength(insertSql.length()-1);
			insertSql.append(")");
			
			// executing phase
			dbManager.execute(insertSql.toString());
		} catch (SQLException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Vehicle deleteById(long id) {
		return null;
	}


	public Vehicle delete(Vehicle obj) throws SQLException {
        String sql = "DELETE FROM vehicle WHERE ownerId = ?";
		conn = DbManager.getInstance().getConnection();
		ps =conn.prepareStatement(sql);
		ps.setInt(1, obj.getOwnerId());
		ps.executeUpdate();
		return null;
	}
	@Override
	public List<Vehicle> findById(long id) {
		StringBuilder select = new StringBuilder();
		List<Vehicle> vehicles = new ArrayList<>();
		select.append("select * from vehicle where ownerId = " + id  + ";");
		try {
			System.out.println(select.toString());
			ResultSet rs = dbManager.execute(select.toString());
			while(rs.next()) {
                Vehicle vehicle = Builder.of(Vehicle::new)
                		.with(Vehicle::setOwnerId, rs.getInt(1))
                		//.with(Vehicle::setPermitId, rs.getInt(2))
                		.with(Vehicle::setLicenseNum, rs.getString(2))
                		.with(Vehicle::setMake, rs.getString(3))
                		.with(Vehicle::setModel, rs.getString(4))
                		.with(Vehicle::setYear, rs.getString(5))
                		.with(Vehicle::setColor, rs.getString(6))
                		.build();
                vehicles.add(vehicle);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Internal Error");
			e.printStackTrace();
		}
		
		return vehicles;
	}

	public Vehicle findByIdNew(long id) {
		
		String sqlStr = "SELECT licenseNum, make, model, year, color FROM vehicle WHERE ownerId=" + String.valueOf(id);
		try
		{
			ResultSet rs = dbManager.execute(sqlStr);
			
			rs.next();
			
			Vehicle v = new Vehicle();
			v.setLicenseNum(rs.getString(1));
			v.setMake(rs.getString(2));
			v.setModel(rs.getString(3));
			v.setYear(rs.getString(4));
			v.setColor(rs.getString(5));
			
			return v;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Vehicle> findAll() {
		return null;
	}

	@Override
	public Vehicle find(List<String> fields) {
		
		StringBuilder selectQuery = new StringBuilder();
		selectQuery.append("select");
		fields.forEach(e -> {
			selectQuery.append(e + ",");
		});	
		selectQuery.setLength(selectQuery.length()-1);
		selectQuery.append("from Vehicle");
		
		try {
			dbManager.execute(selectQuery.toString());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Vehicle updateFields(Map<String, Object> fields, Map<String, Object> conditions) {
		
		StringBuilder updateSql = new StringBuilder();
		updateSql.append("update vehicle set licenseNum=?, make=?, model= ?, year=?, color=? where ownerId=?");
		fields.entrySet().forEach(e -> {
			updateSql.append(e.getKey() + "=" + e.getValue() + ",");
		});
		
		updateSql.setLength(updateSql.length()-1);
		updateSql.append("where ");
		
		conditions.entrySet().forEach(e -> {
			updateSql.append(e.getKey() + "=" + e.getValue() + ",");
		});
		updateSql.setLength(updateSql.length()-1);
		
		try {
			dbManager.execute(updateSql.toString());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}
}
