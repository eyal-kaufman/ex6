package oop.ex6.variables.exceptions;


/**
 * exception called when a variable declaration is invalid
 */
public class InvalidDeclarationException extends VariableException {

	/**
	 * returns an informative message about the exception
	 */
	public InvalidDeclarationException() {
		super("Error: invalid variable declaration, wrong format of declaration");
	}
}
