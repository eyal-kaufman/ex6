package oop.ex6.parser;

import oop.ex6.main.FindLineType;
import oop.ex6.parser.exception.ActionSyntaxInvalidException;


/**
 * this is class inherits from ExtractArguments
 * its purpose is to create new objects of type LineType according to calling a function
 */
public class MethodSignatureParser extends ExtractArguments {

	/**
	 * constructor
	 * @param line - the line read from the file
	 */
	MethodSignatureParser(String line) {
		super(line);
	}

	/**
	 * this function creates LineType object according a new method that is created
	 * @return - an object holding all the relevant information for a new method
	 */
	@Override
	public LineType createLineObject() throws ActionSyntaxInvalidException {
		String[] splitInformation = line.split("[//(//]");
		String[] splitName = splitInformation[0].trim().split(" ");
		if (splitName.length != 2) {
			throw new ActionSyntaxInvalidException();
		}
		String name = splitName[1].trim();

		String variablesString = line.substring(line.indexOf("(") + 1,line.indexOf(')'));
		String[] variableList = variablesString.split(",");
		LineType lineInfo = new LineType(FindLineType.METHOD_SIGNATURE,variableList,name);
		return lineInfo;
	}
}
