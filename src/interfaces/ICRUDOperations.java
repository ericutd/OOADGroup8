package interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ICRUDOperations<T> {

	T insert(T obj);
	
	T updateFields(Map<String, Object> fields, Map<String, Object> conditions);
	
	T deleteById(long id);
	
	T delete(T obj) throws SQLException;
	
	List<T> findById(long id);
	
	List<T> findAll();
	
	T find(List<String> fields);
	
}
