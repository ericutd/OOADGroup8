package db;

public interface CRUDOperation<T> {
	
	T create(T obj);
	
	void findBy(long id);
	
	void findAll();
	
	T delete(T obj);
	
	void update(T obj);
	
}
