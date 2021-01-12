package oop.ex6.variables.exceptions;

/**
 * exception called the variable name is invalid
 */
public class InvalidVariableNameException extends VariableException {

	/**
	 * returns an informative message about the exception
	 *
	 * @param v - the name of the variable that is not valid
	 */
	public InvalidVariableNameException(String v) {
		super("Error: variable named " + v + " is not valid");
	}
}
