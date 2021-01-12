package oop.ex6.handlers.exception;


/**
 * this class throws an exception when the scope is not closed properly
 */
public class ScopeNotClosedException extends InvalidActionTermsException {
	/**
	 * * this function has an informative message sent from the program
	 */
	public ScopeNotClosedException() {
		super("Error: there scope is not closed");
	}
}
