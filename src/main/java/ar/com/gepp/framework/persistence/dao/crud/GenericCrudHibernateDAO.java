package ar.com.gepp.framework.persistence.dao.crud;

import java.io.Serializable;
import java.util.List;

import ar.com.gepp.framework.exceptions.FrameworkPersistenceException;

/**
 * 
 * @author gpidote
 *
 * @param <T>
 */
public interface GenericCrudHibernateDAO<T> {

	void delete(final T entity) throws FrameworkPersistenceException;
	
	void deleteById(final Serializable id) throws FrameworkPersistenceException;

	List<T> getAll() throws FrameworkPersistenceException;

	T getById(final Serializable id) throws FrameworkPersistenceException;

	T create(final T entity) throws FrameworkPersistenceException;

	T update(final T entity) throws FrameworkPersistenceException;
}