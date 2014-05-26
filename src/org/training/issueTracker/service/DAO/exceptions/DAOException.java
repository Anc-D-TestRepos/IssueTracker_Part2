package org.training.issueTracker.service.DAO.exceptions;

public class DAOException extends Exception {

	private String message;
	public DAOException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOException(String message, Throwable cause) {
		super(cause);
		this.message = message;
	}

	public DAOException(String message) {
		this.message = message;
	}

	public DAOException(Throwable cause) {
		super(cause);

	}
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

}
