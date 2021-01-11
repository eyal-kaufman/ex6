package main;

import parser.LineType;
import variables.Types;
import variables.Variable;
import variables.VariableException;
import variables.VariableFactory;
import java.util.LinkedList;

/**
 * describe method block, hold all list of all the lines refer to it, can add variable that
 * declared as argument in the signature to new Variable objects, and contains a list of
 * the their types by order.
 */
public class Functions extends Block{
	/** list of all arguments' type by order for future validation*/
	private final LinkedList<Types> functionTypeParams = new LinkedList<>();
	/** hold all the parsed line extracted from a method in the first reading*/
	private final LinkedList<LineType> blockLines = new LinkedList<>();

	/**
	 * creating new function object.
	 * @param previousBlock the previous block, supposed to be global if valid.
	 */
	public Functions(Block previousBlock){
		super(previousBlock);

	}

	/**
	 * updates the variable block map with given line of variables, when creating new methods.
	 * @param lineType an object wrap line of javas and its purpose.
	 * @throws VariableException if one of the variables is not valid.
	 */
	public void updateVariable(LineType lineType) throws VariableException {
		if (!isGlobal) {
			for (String variable : lineType.getVariableList()) {
				if (!variable.equals("")) {
					Variable argument = VariableFactory
							.createVariableFromArgument(variable, this.variableMap);
					this.addParamType(argument);

				}
			}
		}
	}
	/**
	 * this function adds a new function's argument to the linked list hold all of the arguments.
	 * @param variable - the variable added
	 */
	public void addParamType(Variable variable){
		this.functionTypeParams.add(variable.getType());
	}
	/**
	 * add LineType object to the block's line pool, relevant for method blocks only.
	 * @param line object wrap a javas code line with its relevant params
	 */
	public void addLine(LineType line) {
		this.blockLines.add(line);
	}

	/**
	 * return the pool of lines this block has, relevant only for method blocks.
	 * @return linked list hold all lines refer ot this method block
	 */
	public LinkedList<LineType> getBlockLines() {
		return blockLines;
	}
	/**
	 * informs that it is not a function
	 * @return false
	 */
	public boolean isFunction(){
		return true;
	}
	/**
	 * return the types linked list, conatins all the types of method argument by order.
	 * @return linked list of arguments' types.
	 */
	public LinkedList<Types> getFunctionTypeParams(){
		return this.functionTypeParams;
	}

}
