package oop.ex6.parser.exception;


/**
 * this class throws an exception when the lines syntax is invalid
 */
public class ActionSyntaxInvalidException extends Exception {
	/**
	 * * this function has an informative message explaining what the exception is
	 **/
	public ActionSyntaxInvalidException() {
		super("ERROR: the lines syntax is invalid ");
	}

	/**
	 * * this function has an informative message sent from the program explaining what the exception is
	 **/
	public ActionSyntaxInvalidException(String message) {
		super(message);
	}

}

