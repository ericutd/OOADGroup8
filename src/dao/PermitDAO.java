package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import db.DbManager;
import interfaces.ICRUDOperations;
import pojo.Permit;

public class PermitDAO implements ICRUDOperations<Permit> {
	
	private DbManager dbManager;
	
	public PermitDAO() {
		this.dbManager = DbManager.getInstance();
	}

	@Override
	public Permit insert(Permit permit) {
		// TODO Auto-generated method stub
		StringBuilder insertSql = new StringBuilder();
		insertSql.append("INSERT INTO PERMIT (ownerId,price, colorClass) VALUES (");
		
		insertSql.append(permit.getOwnerId() + ",");
		insertSql.append(permit.getPrice() + ",");
		insertSql.append(permit.getPermitColor().getColor() + ",");
		
		insertSql.setLength(insertSql.length()-1);
		insertSql.append(")");
		
		// executing phase
		try {
			System.out.println(insertSql.toString());
			dbManager.execute(insertSql.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in inserting" +  permit);
			e.printStackTrace();
		}
		return permit;
	}

	@Override
	public Permit updateFields(Map<String, Object> fields, Map<String, Object> conditions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Permit deleteById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Permit delete(Permit obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Permit> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Permit> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Permit find(List<String> fields) {
		// TODO Auto-generated method stub
		return null;
	}

}
