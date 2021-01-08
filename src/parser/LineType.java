package parser;

import main.FindLineType;

/**
 * this class created the object maintaining all the information needed
 */
public class LineType {

	/**
	 * the type of action dealing with
	 */
	private final FindLineType lineType;

	/**
	 * all the relevant variables given in this action
	 */
	private final String[] variableList;

	/**
	 * name of the function if relevant
	 */
	private final String name;

	/**
	 * constructor
	 * @param lineType - the type of action
	 */
	LineType(FindLineType lineType){ /// for COMMENT, RETURN, EMPTY_LINE
		this.lineType = lineType;
		this.variableList = null;
		this.name = null;
	}

	/**
	 * constructor
	 *  @param lineType - the type of action
	  * @param variableList - the list of variables
	 */
	LineType(FindLineType lineType, String[] variableList){ // IF, WHILE
		this.lineType = lineType;
		this.variableList = variableList;
		this.name = null;
	}

	/**
	 * constructor
	 * @param lineType - the type of action
	 * @param variableList - the list of variables
	 * @param name - name of the function
	 */
	LineType(FindLineType lineType, String[] variableList,String name){ // INVOKE_METHOD, SIGNATURE_METHOD
		this.lineType = lineType;
		this.variableList = variableList;
		this.name = name;
	}

	/**
	 * getter for the variables
	 * @return - variable list
	 */
	public String[] getVariableList() {
		return variableList;
	}

	/**
	 * getter for LineType
	 * @return - LineType
	 */
	public FindLineType getLineType() {
		return lineType;
	}

	/**
	 * getter for name
	 * @return the name of the action
	 */
	public String getName() {
		return name;
	}
}
