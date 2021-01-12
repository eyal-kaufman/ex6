package oop.ex6.variables.exceptions;


/**
 * exception called when a variable casted in an invalid way
 */
public class InvalidCastingException extends VariableException {
	/**
	 * returns an informative message about the exception
	 */
	public InvalidCastingException() {
		super("Error: it's not allowed to cast multi oop.ex6.variables in single line");
	}
}
