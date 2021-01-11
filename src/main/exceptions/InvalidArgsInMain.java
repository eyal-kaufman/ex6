package main.exceptions;

/**
 * this class throws an exception when the user sends invalid amount of arguments to the program
 */
public class InvalidArgsInMain extends Exception{

	/**
	 * this constructor informs the user with the specific exception
	 */
	public InvalidArgsInMain(){
		super("Error: are only allowed to have one argument sent to the main");
	}
}
