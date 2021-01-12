package oop.ex6.handlers;

import oop.ex6.parser.exception.IfAndWilException;
import oop.ex6.main.Block;
import oop.ex6.parser.LineType;
import oop.ex6.parser.exception.ActionSyntaxInvalidException;
import oop.ex6.variables.Types;
import oop.ex6.variables.Variable;

import java.util.Stack;

/**
 * handle the situation when encounter if or while statements.
 */
public class IfAndWhileHandler {

	/**
	 * invokes when encounter if or while statements in the advanced reading.
	 * checks if the expressions inside them are valid booleans, if so it would create new
	 * block that copies its "father" block's variable map.
	 *
	 * @param scope      the current scope
	 * @param actionLine object wrap the if/while statement
	 * @param blocks     the stack of the scopes
	 * @throws ActionSyntaxInvalidException in case of invalid if or while statement
	 */
	public static void ifAndWhile(Block scope, LineType actionLine, Stack<Block> blocks) throws
			ActionSyntaxInvalidException {
		for (String expression : actionLine.getVariableList()) {
			expression = expression.trim();
			Variable variable = scope.isVariableInBlock(expression);
			if (Types.BOOLEAN.checkValueType(expression)
					|| variable != null && variable.isInitialized()
					&& Types.BOOLEAN.approvedType(variable.getType())) {
				Block newBlock = new Block(blocks.peek());
				newBlock.updateMap();
				blocks.push(newBlock);
			} else {
				throw new IfAndWilException();
			}
		}

	}

}
