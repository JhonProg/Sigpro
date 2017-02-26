package co.com.sigpro.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class LogicaException extends Exception {

	private static final long serialVersionUID = 1L;

	public LogicaException(String message, Throwable cause) {
		super(message, cause);
	}

	public LogicaException(Throwable cause) {
		super(cause);
	}

	public LogicaException() {
		super();
	}

	public LogicaException(String mensaje) {
		super(mensaje);
	}

	@Override
	public String toString() {
		return getMessage();
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
}
