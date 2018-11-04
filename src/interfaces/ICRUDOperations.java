package interfaces;

import java.util.List;
import java.util.Map;

public interface ICRUDOperations<T> {

	T insert(T obj);
	
	T updateFields(Map<String, Object> fields, Map<String, Object> conditions);
	
	T deleteById(long id);
	
	T delete(T obj);
	
	T findById(long id);
	
	T findAll();
	
	T find(List<String> fields);
	
}