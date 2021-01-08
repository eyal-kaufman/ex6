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
		String name = line.substring(line.indexOf(0) ,line.indexOf("(")).trim();
		String variablesString = line.substring(line.indexOf("(") + 1,line.indexOf(')'));
		String[] variableList = variablesString.split(",");
		LineType lineInfo = new LineType(FindLineType.METHOD_INVOKE,variableList,name);
		return lineInfo;
	}
}
