package org.formation.exception;

public class TransferException extends RuntimeException {
	// Exception lancée en cas de virement invalide

	private static final long serialVersionUID = 1L;

	public TransferException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TransferException(String message, Throwable cause) {
		super(message, cause);
	}

	public TransferException(String message) {
		super(message);
	}

	public TransferException(Throwable cause) {
		super(cause);
	}

}
