package handlers;

import main.Block;
import parser.LineType;
import parser.exception.ActionSyntaxInvalidException;
import variables.Types;
import variables.Variable;

import java.util.Stack;

public class IfAndWhileHandler {
//TODO exception
	public static void ifAndWhile(Block scope, LineType actionLine, Stack<Block> blocks) throws
																						 ActionSyntaxInvalidException {
		for (String expression: actionLine.getVariableList()) {
			expression = expression.trim();
			Variable variable = scope.isVariableInBlock(expression);
			if (Types.BOOLEAN.checkValueType(expression)
				|| variable != null && variable.getType().equals(Types.BOOLEAN)) {

				Block newBlock = new Block(blocks.peek());
//				TODO is the deep copy works?
				newBlock.updateMap();
				blocks.add(newBlock);
			}
		}

	}

}
