package handlers;

import main.Block;
import main.ReadFile;
import parser.LineType;
import parser.exception.ActionSyntaxInvalidException;
import variables.Types;
import variables.Variable;
import variables.VariableException;
import variables.VariableFactory;

import java.util.LinkedList;
import java.util.Stack;

public class MethodHandler {
	public static void methodSignature(Block block, LineType lineType, Stack<Block> blocks) throws ActionSyntaxInvalidException,
																					 VariableException {
		String functionName = lineType.getName();
		if (!block.isGlobal() || !Variable.isValidName(functionName) || ReadFile.functionMap.containsKey(functionName)) {
			throw new ActionSyntaxInvalidException("invalid method signature");
		}
		Block functionBlock = new Block(block);
		functionBlock.updateVariable(lineType);
		ReadFile.functionMap.put(functionName, functionBlock);
		blocks.add(functionBlock);
	}
//TODO
	/**
	 * checks if the method invoke is valid: the name is of existing method, and the params are known.
	 * @param lineType
	 * @param scope
	 * @throws ActionSyntaxInvalidException
	 */
	public static void validMethodCall(LineType lineType, Block scope) throws ActionSyntaxInvalidException {
		String functionName = lineType.getName();
		if (ReadFile.functionMap.containsKey(functionName)) {
			Block function = ReadFile.functionMap.get(functionName);
			LinkedList<Types> params = function.getFunctionParams();
			if (params.size() != lineType.getVariableList().length) {
				throw new ActionSyntaxInvalidException("invalid number of argument in method call");
			}
			for (int i = 0; i < params.size(); i++) {
//			for (String variable :lineType.getVariableList()){
				String variable = lineType.getVariableList()[i];
				Variable variableObject = scope.isVariableInBlock(variable);
				if (variableObject != null &&   (!variableObject.isInitialized()
					|| !params.get(i).approvedType(variableObject.getType()))
					|| (variableObject == null && !params.get(i).checkValueType(variable))) {
					throw new ActionSyntaxInvalidException("invalid method call");
				}

			}
		} else {
			throw new ActionSyntaxInvalidException("invaid method call");
		}
	}
}
