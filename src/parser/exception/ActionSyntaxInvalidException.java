package parser.exception;


/**
 * this class throws an exception when the lines syntax is invalid
 */
public class ActionSyntaxInvalidException extends Exception{
	/**
	 * * this function has an informative message explaining what the exception is
	**/
	public ActionSyntaxInvalidException(){
		super("ERROR: the lines syntax is invalid ");
	}

}

