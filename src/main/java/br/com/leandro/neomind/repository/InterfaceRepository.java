package br.com.leandro.neomind.repository;

import java.util.List;

public interface InterfaceRepository<T> {
	
	public T store(T o);
	
	public void update(T o);
	
	public T findById(int id);
	
	public void delete(int id);
	
	public List<T> list();
	
}
