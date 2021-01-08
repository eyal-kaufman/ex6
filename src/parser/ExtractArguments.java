package parser;

import parser.exception.ActionSyntaxInvalidException;

/**
 * this is an abstract class,
 * its purpose is to create new objects of type LineType
 */
public abstract class ExtractArguments {

	/**
	 * the line read from the file
	 */
	String line;

	/**
	 * constructor
	 * @param line - the line read from the file
	 */
	ExtractArguments(String line){
		this.line = line;
	}

	/**
	 * this function creates the relevant object LineType
	 * @return - an object holding all the relevant information from the line given
	 * @throws ActionSyntaxInvalidException - if the syntax in the line is invalid
	 */
	public abstract LineType createLineObject() throws ActionSyntaxInvalidException;
}