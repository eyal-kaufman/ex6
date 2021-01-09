package parser;

import main.FindLineType;
import parser.exception.ActionSyntaxInvalidException;

/**
 * this is class inherits from ExtractArguments
 * its purpose is to create new objects of type LineType according to if and while lines
 */
public class IfOrWhileParser extends ExtractArguments {

	/**
	 * constructor
	 * @param line - the line read from the file
	 */
	IfOrWhileParser(String line){
		super(line);
	}

	/**
	 * this function creates LineType object according to a if or while line
	 * @return - an object holding all the relevant information for if or while action
	 * @throws ActionSyntaxInvalidException - if the syntax in the line is invalid
	 */
	@Override
	public LineType createLineObject() throws ActionSyntaxInvalidException {
		String[] splitInformation = line.split("[//(//]");
//		String variablesString = line.substring(line.indexOf("(") + 1,line.indexOf(')'));
		String variablesString = this.extractArguments();
		if (variablesString.trim().equals(""))
			throw new ActionSyntaxInvalidException("no arguments in while/if statement");
		String[] variableList = variablesString.split("|||||&&");
		return new LineType(FindLineType.lineAction(line), variableList);
	}
}
