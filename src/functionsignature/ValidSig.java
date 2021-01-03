package functionsignature;
import functionsignature.functionnameexceptions.SignatureFunctionException;
//import name.ValidName;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidSig {

	String sigLine;

	public ValidSig(String sigLine){
		this.sigLine = sigLine;
	}

	public void manageSignature() throws  SignatureFunctionException {
		String[] sigArray;
		Pattern myPattern = Pattern.compile("void\\s[\\s\\w,]*[(][\\s\\w,]*[)][{]");
		Matcher match = myPattern.matcher(sigLine);
		try{
			if(!match.matches()){
				throw new SignatureFunctionException();     // checks if the function signature is valid
			}
			sigArray = sigLine.split("\\(|\\)" );
//			new ValidName(sigArray[1]);
			sigArray = sigArray[0].split(" ");
//			new ValidName(sigArray[1]);
		}
		catch(SignatureFunctionException e){
			System.err.println(e.getMessage());
		}
	}

}


