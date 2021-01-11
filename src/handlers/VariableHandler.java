package handlers;

import main.Block;
import main.Functions;
import parser.LineType;
import variables.VariableException;
import variables.VariableFactory;
/**
 * handle the case of encounter variable declaration line.
 */
public class VariableHandler {
	/**
	 * if it's the first reading it would check the line placed in global scope, and if so
	 * would check the line for validity and would add the other lines into the function lines pool.
	 * if it's not the first reading, it would check the line for validity.
	 * @param scope the current scope
	 * @param globalFirst boolean indicates if it's the first reading
	 * @param actionLine the action line wrap the variable declaration
	 * @throws VariableException in case of invalid declaration or variable casting.
	 */
	public static void variableDeclare(Block scope, boolean globalFirst, LineType actionLine)
			throws VariableException {
		if (scope.isGlobal() && globalFirst) {
			VariableFactory.parseVariableLine(actionLine.getVariableList()[0], Block.globalVariables);
		}
		else if (globalFirst && scope.isFunction())  {
			((Functions) scope).addLine(actionLine);
		}
		else if (!scope.isGlobal() && !globalFirst)  {
			VariableFactory.parseVariableLine(actionLine.getVariableList()[0], scope.getVariableMap());
		}
	}
}
