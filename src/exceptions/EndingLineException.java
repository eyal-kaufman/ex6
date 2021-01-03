package exceptions;

/**
 * this class throws an exception when there is no },{,; at the end of the line
 */
public class EndingLineException extends Exception{
	/**
	 * this constructor informs the user with the specific exception
	 */
	public EndingLineException(){
		super("Error: you cannot write a note in that spot");
	}

}
