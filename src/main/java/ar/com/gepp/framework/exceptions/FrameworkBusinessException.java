package ar.com.gepp.framework.exceptions;

/**
 * Excepcion de negocio
 * 
 * @author gpidote
 *
 */
public class FrameworkBusinessException extends Exception {

	private static final long serialVersionUID = 1451230441998680163L;

	public FrameworkBusinessException() {
		super();
	}

	public FrameworkBusinessException(String message, Exception exception) {
		super(message, exception);
	}

	public FrameworkBusinessException(String message) {
		super(message);
	}

}
