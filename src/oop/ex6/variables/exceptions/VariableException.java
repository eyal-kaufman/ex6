package oop.ex6.variables.exceptions;

/**
 * exception called when a variable is not valid
 */
public class VariableException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * get the message for the exception
	 *
	 * @param message message of the error
	 */
	public VariableException(String message) {
		super(message);
	}
}
