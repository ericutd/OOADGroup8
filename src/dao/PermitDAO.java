package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.sql.ResultSet;

import db.DbManager;
import interfaces.ICRUDOperations;
import pojo.Permit;
import pojo.Permit.PermitColor;

public class PermitDAO implements ICRUDOperations<Permit> {
	
	private DbManager dbManager;
	
	public PermitDAO() {
		this.dbManager = DbManager.getInstance();
	}

	@Override
	public Permit insert(Permit permit) {
		// TODO Auto-generated method stub
		StringBuilder insertSql = new StringBuilder();
		insertSql.append("INSERT INTO PERMIT (ownerId,price, colorClass, expirationDate) VALUES (");
		
		insertSql.append(permit.getOwnerId() + ",");
		insertSql.append(permit.getPrice() + ",");
		insertSql.append("\"" + permit.getPermitColor().name() + "\""+ ",");
		insertSql.append("\"" + permit.getExpDate() + "\"");
		insertSql.append(")");
		
		// executing phase
		try {
			System.out.println(insertSql.toString());
			dbManager.executeUpdate(insertSql.toString());
			
			ResultSet rs = dbManager.execute("select permitId from permit where ownerId= " + permit.getOwnerId());
			while(rs.next()) {
				permit.setPermitId(rs.getInt("permitId"));
			}
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
	
	public Permit findByIdNew(int id) throws SQLException
	{
		String sqlStr = "SELECT ownerId, colorClass FROM permit WHERE permitId=" + String.valueOf(id);
		
		ResultSet rs = dbManager.execute(sqlStr);
		
		if(rs.next())
		{
			Permit p = new Permit();
			p.setOwnerId(rs.getInt(1));
			p.setPermitId(id);
			Permit.PermitColor pColor = PermitColor.valueOf(rs.getString(2));
			p.setPermitColor(pColor);
			return p;
		}
		
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