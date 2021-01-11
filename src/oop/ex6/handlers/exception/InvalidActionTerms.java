package oop.ex6.handlers.exception;


/**
 * this class throws an exception when the content of the action is invalid
 */
public class InvalidActionTerms extends Exception{

	/**
	 * * this function has an informative message sent from the program
	 **/
	public InvalidActionTerms(String message){
		super("Error: " + message);
	}

}

