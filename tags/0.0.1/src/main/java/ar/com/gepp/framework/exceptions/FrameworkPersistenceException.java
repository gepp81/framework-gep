package ar.com.gepp.framework.exceptions;

/**
 * Excepcion generica
 * 
 * @author gpidote
 *
 */
public class FrameworkPersistenceException extends Exception {

	private static final long serialVersionUID = -267227716452066163L;

	public FrameworkPersistenceException() {
		super();
	}
	
	public FrameworkPersistenceException(String message, Exception exception) {
		super(message, exception);
	}
	
	public FrameworkPersistenceException(String message) {
		super(message);
	}	

}
