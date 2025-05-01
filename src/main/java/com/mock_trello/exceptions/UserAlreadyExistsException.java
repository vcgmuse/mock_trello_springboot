package com.mock_trello.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1974602690800904165L;

	public UserAlreadyExistsException(String message) {
        super(message);
    }
}
