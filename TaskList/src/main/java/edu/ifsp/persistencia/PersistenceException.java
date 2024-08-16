package edu.ifsp.persistencia;

public class PersistenceException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public PersistenceException(String message) {
		super(message);
	}
	
	public PersistenceException(Throwable cause) {
		super(cause);
	}
}