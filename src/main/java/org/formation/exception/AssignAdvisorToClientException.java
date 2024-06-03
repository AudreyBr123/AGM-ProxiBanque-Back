package org.formation.exception;

public class AssignAdvisorToClientException extends Exception {
	// Exception lancée en cas d'erreur quand on assigne un advisor à un client

	private static final long serialVersionUID = 1L;

	public AssignAdvisorToClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AssignAdvisorToClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public AssignAdvisorToClientException(String message) {
		super(message);
	}

	public AssignAdvisorToClientException(Throwable cause) {
		super(cause);
	}

}
