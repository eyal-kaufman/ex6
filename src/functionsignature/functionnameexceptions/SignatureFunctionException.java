package functionsignature.functionnameexceptions;
/**
 * this class throws an exception when the function signature is invalid
 */
public class SignatureFunctionException extends Exception{

	public SignatureFunctionException(){
		super("ERROR: the function signature is invalid ");
	}

}
