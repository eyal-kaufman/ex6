package parser;

import main.FindLineType;

/**
 * this is class inherits from ExtractArguments
 * its purpose is to create new objects of type LineType according creating new method
 */
public class MethodInvokeParser extends ExtractArguments{

	/**
	 * constructor
	 * @param line - the line read from the file
	 */
	MethodInvokeParser(String line) {
		super(line);
	}

	/**
	 * this function creates LineType object according to calling a function
	 * @return - an object holding all the relevant information for calling a function
	 */
	@Override
	public LineType createLineObject() {
		int leftParenthesis = line.indexOf('(');
//		int rightParenthesis = line.indexOf(')');
		String[] variableList = this.extractArguments().split(",");
		String name = line.substring(0, leftParenthesis).trim();
//		if (leftParenthesis +1 == rightParenthesis) {
//			variableList = new String[]{""};
//		}
//		else {
////			String name = line.substring(line.indexOf(0), line.indexOf("(")).trim();
//			String variablesString = line.substring(leftParenthesis + 1, rightParenthesis);
//			variableList = variablesString.split(",");
//		}
		return new LineType(FindLineType.METHOD_INVOKE, variableList, name);
	}
}
