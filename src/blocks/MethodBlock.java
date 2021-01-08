//package blocks;
////import VariableFactory;
//import blocks.blockexception.BlockTitleException;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * this class makes sure that al elements connected to a method are valid
// */
//public class MethodBlock extends AllBlocks{
//
//	/**
//	 * this is the constructor
//	 * @param line - the method signature being checked
//	 */
//	public MethodBlock(String line){
//		super(line);
//	}
//
//	/**
//	 * this function checks the validity of the method signature
//	 * @return - true if all is valid
//	 * @throws BlockTitleException - this exception is thrown when the name is invalid
//	 */
//	@Override
//	public boolean isValid() throws BlockTitleException {
//		String[] sigArray;
//		String[] paramArray;
////		VariableFactory vf = new VariableFactory();
//		Pattern myPattern = Pattern.compile("void\\s[\\s\\w,]*[(][\\s\\w,]*[)][{]");
//		Matcher match = myPattern.matcher(line);
//		if(!match.matches()){
//			throw new BlockTitleException();            // checks if the function signature is valid
//		}
//		sigArray = line.split("\\(|\\)" );         // sends all parameters to be checked
//		paramArray = sigArray[1].split(",");
//		for( String param: paramArray){
////			vf.parseDeclaration(param);                 // ask eyal if i sent it to the right function
//		}
//		sigArray = sigArray[0].split(" ");          // sends all function name to be checked
////		vf.parseDeclaration(sigArray[1]);               // ask eyal if i sent it to the right function
//		return true;
//	}
//
//}
//
//
