package handlers;

import main.Block;
import parser.LineType;
import parser.exception.ActionSyntaxInvalidException;
import variables.Types;
import variables.Variable;

import java.util.Stack;

/**
 * handle the situation when encounter if or while statements.
 */
public class IfAndWhileHandler {
//TODO exception, check if  the updateMap in Block works

	/**
	 * invokes when encounter if or while statements in the advanced reading.
	 * checks if the expressions inside them are valid booleans, if so it would create new
	 * block that copies its "father" block's variable map.
	 * @param scope the current scope
	 * @param actionLine object wrap the if/while statement
	 * @param blocks the stack of the scopes
	 * @throws ActionSyntaxInvalidException in case of invalid if or while statement
	 */
	public static void ifAndWhile(Block scope, LineType actionLine, Stack<Block> blocks) throws
																						 ActionSyntaxInvalidException {
		for (String expression: actionLine.getVariableList()) {
			expression = expression.trim();
			Variable variable = scope.isVariableInBlock(expression);
			if (Types.BOOLEAN.checkValueType(expression)
				|| variable != null && Types.BOOLEAN.approvedType(variable.getType())) {
				Block newBlock = new Block(blocks.peek());
				newBlock.updateMap();
				blocks.push(newBlock);
			} else {
				throw new ActionSyntaxInvalidException("invalid expression in if/while statement");
			}
		}

	}

}
