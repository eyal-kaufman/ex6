package parser;

import main.FindLineType;
import parser.exception.ActionSyntaxInvalidException;

/**
 * this class gathers all the relevant information each line has,
 * saves it in an object for future usage
 */
public class Parsers {
	/**
	 * this function gathers all information of the lines action
	 * @param line - the line being read
	 * @return - an LineType object
	 * @throws ActionSyntaxInvalidException - if the line syntax is invalid.
	 */
	public static LineType lineParser(String line) throws ActionSyntaxInvalidException {
		FindLineType findLineType = FindLineType.lineAction(line);
		ExtractArguments lineInfo;
		switch (findLineType){
			case CLOSER:
				return new LineType(FindLineType.CLOSER);
			case IF_LINE:case WHILE_LINE:
				lineInfo = new IfOrWhileParser(line);
				return lineInfo.createLineObject();
			case RETURN_LINE:
				return new LineType(FindLineType.RETURN_LINE);
			case EMPTY_LINE: case COMMENT:
				return new LineType(FindLineType.lineAction(line));
			case METHOD_INVOKE:
				lineInfo = new MethodInvokeParser(line);
				return lineInfo.createLineObject();
			case METHOD_SIGNATURE:
				lineInfo = new MethodSignatureParser(line);
				return lineInfo.createLineObject();
			case VARIABLE:
				return new LineType(FindLineType.VARIABLE,new String[]{line});
		}
		throw new ActionSyntaxInvalidException();
	}


}
