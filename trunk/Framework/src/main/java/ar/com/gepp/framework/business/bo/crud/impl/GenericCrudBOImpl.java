package ar.com.gepp.framework.business.bo.crud.impl;

import java.io.Serializable;
import java.util.List;

import org.jboss.logging.Logger;

import ar.com.gepp.framework.business.bo.crud.GenericCrudBO;
import ar.com.gepp.framework.exceptions.FrameworkPersistenceException;
import ar.com.gepp.framework.persistence.dao.crud.GenericCrudHibernateDAO;

public class GenericCrudBOImpl<T> implements GenericCrudBO<T> {

	private static final Logger log = Logger.getLogger(GenericCrudBOImpl.class);
	private GenericCrudHibernateDAO<T> genericDAO;
	
	public List<T> getAll() throws FrameworkPersistenceException {
		try {
			return genericDAO.getAll();
		} catch (FrameworkPersistenceException e) {
			log.error("BO Generico - getAll", e);
			throw new FrameworkPersistenceException(e.getMessage(), e);
		}
	}

	public T getById(final Serializable id) throws FrameworkPersistenceException {
		try {
			return genericDAO.getById(id);
		} catch (FrameworkPersistenceException e) {
			log.error("BO Generico - getById", e);
			throw new FrameworkPersistenceException(e.getMessage(), e);
		}
	}

	public T update(final T entity) throws FrameworkPersistenceException {
		try {
			return genericDAO.update(entity);
		} catch (FrameworkPersistenceException e) {
			log.error("BO Generico - update", e);
			throw new FrameworkPersistenceException(e.getMessage(), e);
		}
	}

	public T create(final T entity) throws FrameworkPersistenceException {
		try {
			return genericDAO.create(entity);
		} catch (FrameworkPersistenceException e) {
			log.error("BO Generico - create", e);
			throw new FrameworkPersistenceException(e.getMessage(), e);
		}
	}

	public void delete(final T entity) throws FrameworkPersistenceException {
		try {
			genericDAO.delete(entity);
		} catch (FrameworkPersistenceException e) {
			log.error("BO Generico - delete", e);
			throw new FrameworkPersistenceException(e.getMessage(), e);
		}
	}

	public void deleteById(final Serializable id) throws FrameworkPersistenceException {
		try {
			genericDAO.deleteById(id);
		} catch (FrameworkPersistenceException e) {
			log.error("BO Generico - deleteById", e);
			throw new FrameworkPersistenceException(e.getMessage(), e);
		}
	}

	public GenericCrudHibernateDAO<T> getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(GenericCrudHibernateDAO<T> genericDAO) {
		this.genericDAO = genericDAO;
	}

}
