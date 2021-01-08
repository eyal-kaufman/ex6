package parser;

import main.FindLineType;

public class IfParser extends ExtractArguments {

	String line;

	IfParser(String line){
		super(line);
	}

	@Override
	public LineType createLineObject() {
		String[] splitInformation = line.split("[//(//]");
		String name = splitInformation[0];
		String variablesString = line.substring(line.indexOf("(") + 1,line.indexOf(')'));
		String[] variableList = variablesString.split("|||||&&");
		LineType returnObj = new LineType(FindLineType.IF_LINE,variableList);
		return returnObj;
	}
}
