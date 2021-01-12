package oop.ex6.parser.exception;


/**
 * this class throws an exception when there is an action being called to be done in an invalid location
 */
public class InvalidLineLocationException extends ActionSyntaxInvalidException {

	/**
	 * * this function has an informative message sent from the program
	 */
	public InvalidLineLocationException() {
		super("Error: line supposed to be inside a method and must not be after return statement");
	}
}
