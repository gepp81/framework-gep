package ar.com.gepp.framework.persistence.dao.crud.impl;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.gepp.framework.exceptions.FrameworkPersistenceException;
import ar.com.gepp.framework.persistence.dao.crud.GenericCrudHibernateDAO;

import com.google.common.base.Preconditions;

/**
 * 
 * @author gpidote
 *
 * @param <T>
 */
@Transactional
public class GenericCrudHibernateDAOImpl<T extends Serializable> implements GenericCrudHibernateDAO<T> {

	private static final Logger log = Logger.getLogger(GenericCrudHibernateDAOImpl.class);
	private Class<T> clazz;
	@Autowired
	protected SessionFactory sessionFactory;
	
	protected final Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public void setClazz(final Class<T> clazz) {
		this.clazz = clazz;
	}

	public void delete(final T entity) throws FrameworkPersistenceException {
		log.infov("Borrando: {0}", clazz.getName());
		Preconditions.checkNotNull(entity);
		this.getCurrentSession().delete(entity);
	}

	public void deleteById(Serializable id) throws FrameworkPersistenceException {
		log.infov("Borrando objeto tipo: {0} con id: {1}", clazz.getName(), id);
		final T entity = this.getById(id);
		Preconditions.checkState(entity != null);
		this.delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() throws FrameworkPersistenceException {
		log.infov("Obteniendo todos los tipos de: {0}", clazz.getName());
		return this.getCurrentSession().createQuery("from " + this.clazz.getName()).list();
	}

	@SuppressWarnings("unchecked")
	public T getById(Serializable id) throws FrameworkPersistenceException {
		log.infov("Obteniendo objeto tipo: {0} con id: {1}", clazz.getName(), id);
		Preconditions.checkArgument(id != null);
		return (T) this.getCurrentSession().get(this.clazz, id);
	}

	public T create(T entity) throws FrameworkPersistenceException {
		log.infov("Creando: {0}", entity);
		Preconditions.checkNotNull(entity);
		this.getCurrentSession().persist(entity);

		return entity;
	}

	public T update(T entity) throws FrameworkPersistenceException {
		log.infov("Actualizando: {0}", entity);
		Preconditions.checkNotNull(entity);
		this.getCurrentSession().merge(entity);

		return entity;
	}

}
