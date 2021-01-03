package exceptions;

/**
 * this class throws an exception when there is a note in the wrong place in the program
 */
public class NoteException extends Exception{

	/**
	 * this constructor informs the user with the specific exception
	 */
	public NoteException(){
		super("Error: you cannot write a note in that spot");
	}
}
