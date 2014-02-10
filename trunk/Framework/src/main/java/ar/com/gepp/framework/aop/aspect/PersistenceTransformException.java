package ar.com.gepp.framework.aop.aspect;

import org.jboss.logging.Logger;

import ar.com.gepp.framework.exceptions.FrameworkPersistenceException;

/**
 * Aop para transformacion de runtime a persistence 
 * 
 * @author gpidote
 * 
 */
public class PersistenceTransformException {
	
	private static final Logger log = Logger.getLogger(PersistenceTransformException.class);
	
	public void errorRuntimeException(RuntimeException ex) throws FrameworkPersistenceException {
		log.infov("AOP - Transformacion de Exception");
		throw new FrameworkPersistenceException();
	}

}