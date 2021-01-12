package oop.ex6.handlers.exception;


/**
 * this class throws an exception when the content of the action is invalid
 */
public class InvalidActionTermsException extends Exception {

	/**
	 * * this function has an informative message sent from the program explaining what the exception is
	 **/
	public InvalidActionTermsException(String message) {
		super(message);
	}

}

