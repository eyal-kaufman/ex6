package oop.ex6.parser.exception;

/**
 * this class throws an exception when the syntax of if or while are invalid
 */
public class IfAndWilException extends ActionSyntaxInvalidException {

	/**
	 * * this function has an informative message sent from the program
	 */
	public IfAndWilException() {
		super("Error: if or while statement are invalid");
	}
}
