package permit;

import java.sql.Connection;
import java.sql.PreparedStatement;

import db.CRUDOperation;
import db.DbManager;

public class PermitImpl implements CRUDOperation<PermitDAO> {

	private PermitDAO permit;
	private Connection conn;
	private PreparedStatement ps;
	private DbManager db;
	
	public PermitImpl() {
	
	}
	
	@Override
	public PermitDAO create(PermitDAO obj) {
		// TODO Auto-generated method stub
		db = new DbManager();
		String insertSQL = "insert into permit (ownerId, price, colorClass, expirationDate) "
				+ " values (?, ?, ?, ?) ";
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(insertSQL);
			ps.setInt(1, obj.getOwnerId());
			ps.setDouble(2,  obj.getPrice());
			ps.setString(3, obj.getColourClass());
			ps.setString(4,  obj.getExpirationDate());
			ps.executeUpdate();
			conn.close();
			return obj;
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public void findBy(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PermitDAO delete(PermitDAO obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(PermitDAO obj) {
		// TODO Auto-generated method stub
		
	}
	
}
