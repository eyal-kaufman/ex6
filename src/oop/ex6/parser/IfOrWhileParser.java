package oop.ex6.parser;

import oop.ex6.main.FindLineType;
import oop.ex6.parser.exception.ActionSyntaxInvalidException;
import oop.ex6.parser.exception.IfAndWilException;

/**
 * this is class inherits from ExtractArguments
 * its purpose is to create new objects of type LineType according to if and while lines
 */
public class IfOrWhileParser extends ExtractArguments {

	/**
	 * constructor
	 *
	 * @param line - the line read from the file
	 */
	IfOrWhileParser(String line) {
		super(line);
	}

	/**
	 * this function creates LineType object according to a if or while line
	 *
	 * @return - an object holding all the relevant information for if or while action
	 * @throws ActionSyntaxInvalidException - if the syntax in the line is invalid
	 */
	@Override
	public LineType createLineObject() throws ActionSyntaxInvalidException {
		String variablesString = this.extractArguments();
		if (variablesString.trim().equals(""))
			throw new IfAndWilException();
		String[] variableList = variablesString.split("[|][|]|&&");
		return new LineType(FindLineType.lineAction(line), variableList);
	}
}
