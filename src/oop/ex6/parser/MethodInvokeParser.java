package oop.ex6.parser;

import oop.ex6.main.FindLineType;

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
		String[] variableList = this.extractArguments().split(",");
		String name = line.substring(0, leftParenthesis).trim();
		return new LineType(FindLineType.METHOD_INVOKE, variableList, name);
	}
}
