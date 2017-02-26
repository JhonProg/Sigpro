package co.com.sigpro.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class DatoException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public DatoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DatoException(Throwable cause) {
		super(cause);
	}

	public DatoException() {
	}

	public DatoException(String mensaje) {
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
