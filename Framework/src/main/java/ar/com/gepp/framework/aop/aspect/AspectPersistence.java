package ar.com.gepp.framework.aop.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import ar.com.gepp.framework.exceptions.FrameworkPersistenceException;

/**
 * Aop para DAO
 * 
 * @author gpidote
 * 
 */
@Aspect
public class AspectPersistence {

	@AfterThrowing(pointcut = "execution(* ar.com.gepp.framework.persistence..*(..))", throwing = "ex")
	public void errorRuntimeException(RuntimeException ex) throws FrameworkPersistenceException {
		throw new FrameworkPersistenceException();
	}

}