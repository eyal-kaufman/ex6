package handlers;

import main.Block;
import parser.LineType;
import variables.Types;
import variables.Variable;

import java.util.Stack;

public class IfAndWhileHandler {

	public static void ifAndWhile(Block scope, LineType actionLine, Stack<Block> blocks){
		for (String expression: actionLine.getVariableList()) {
			expression = expression.trim();
			Variable variable = scope.isVariableInBlock(expression);
			if (Types.BOOLEAN.checkValueType(expression)
				|| variable != null && variable.getType().equals(Types.BOOLEAN)) {

				Block newBlock = new Block(blocks.peek());
				newBlock.updateMap();
				blocks.add(newBlock);
			}
		}

	}

}
