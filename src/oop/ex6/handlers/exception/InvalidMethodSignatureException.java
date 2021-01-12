package oop.ex6.handlers.exception;


/**
 * this class inherits from InvalidActionTermException throws an exception when the signature is invalid
 */
public class InvalidMethodSignatureException extends InvalidActionTermsException {

	/**
	 * * this function has an informative message sent from the program
	 */
	public InvalidMethodSignatureException() {
		super("Error: the methods signature is invalid");
	}
}
