package ar.com.gepp.framework.business.bo.crud;

import java.io.Serializable;
import java.util.List;

import ar.com.gepp.framework.exceptions.FrameworkPersistenceException;

/**
 * Interfaz de BO
 * 
 * @author gpidote
 * 
 * @param <T>
 */
public interface GenericCrudBO<T> {
	
	List<T> getAll() throws FrameworkPersistenceException;

	T getById(final Serializable id) throws FrameworkPersistenceException;

	T update(final T entity) throws FrameworkPersistenceException;

	T create(final T entity) throws FrameworkPersistenceException;

	void delete(final T entity) throws FrameworkPersistenceException;

	void deleteById(final Serializable id) throws FrameworkPersistenceException;
}
