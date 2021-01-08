package parser;

import main.FindLineType;

public class LineType {

	private final FindLineType lineType;

	private final String[] variableList;

	LineType(FindLineType lineType, String[] variableList){
		this.lineType = lineType;
		this.variableList = variableList;
	}

	public String[] getVariableList() {
		return variableList;
	}

	public FindLineType getLineType() {
		return lineType;
	}
}
