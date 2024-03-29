package oop.ex6.handlers;

import oop.ex6.handlers.exception.InvalidActionTermsException;
import oop.ex6.handlers.exception.InvalidMethodSignatureException;
import oop.ex6.main.Block;
import oop.ex6.main.Functions;
import oop.ex6.main.ReadFile;
import oop.ex6.parser.LineType;
import oop.ex6.variables.Types;
import oop.ex6.variables.Variable;
import oop.ex6.variables.exceptions.VariableException;

import java.util.LinkedList;
import java.util.Stack;

/**
 * handle the situation when encounter method signature or method call.
 */
public class MethodHandler {
	/**
	 * this method gets invoke when attempting first reading, it would check for the validity of the
	 * method signature, and the variable declaration inside it, in case of valid input it would add this
	 * new function to the function map, and change the current scope to be this new method.
	 *
	 * @param lineType object wrap the method signature
	 * @param blocks   stack of blocks
	 * @throws InvalidActionTermsException in case of invalid name of a method/wrong place/exists name.
	 * @throws VariableException  in case of invalid variable declaration in the method's signature
	 */
	public static void methodSignature(LineType lineType, Stack<Block> blocks) throws InvalidActionTermsException,
																					  VariableException {
		Block scope = blocks.peek();
		String functionName = lineType.getName();

		if (!scope.isGlobal() || functionName.startsWith("_") || !Variable.isValidName(functionName)
				|| ReadFile.functionMap.containsKey(functionName) || functionName.trim().contains(" ")) {
			throw new InvalidMethodSignatureException();
		}
		Functions functionBlock = new Functions(scope);
		functionBlock.updateVariable(lineType);
		ReadFile.functionMap.put(functionName, functionBlock);
		blocks.push(functionBlock);
	}

	/**
	 * checks if the method invoke is valid:
	 * - the name is of existing method, and the params are known.
	 * - checks the number of argument in the method call match the needed amount in the
	 * exists method.
	 * -check all the arguments' types in the method call match the types wanted the
	 * known method
	 *
	 * @param lineType the object wrap the method call
	 * @param scope    the current scope
	 * @throws InvalidActionTermsException when the method call is invalid
	 */
	public static void validMethodCall(LineType lineType, Block scope) throws InvalidActionTermsException {
		String functionName = lineType.getName();
		// the name of the function is known:
		if (ReadFile.functionMap.containsKey(functionName)) {
			Functions function = ReadFile.functionMap.get(functionName);
			LinkedList<Types> params = function.getFunctionTypeParams();
			// if the number of arguments match the number of arguments in the known function
			if (params.size() == 0 && !lineType.getVariableList()[0].equals("")
					|| params.size() != 0 && params.size() != lineType.getVariableList().length) {
				throw new InvalidMethodSignatureException();
			}
//			iterate over the the number of arguments in the function
			for (int i = 0; i < params.size(); i++) {

				String variable = lineType.getVariableList()[i];
				Variable variableObject = scope.isVariableInBlock(variable);
//				if the the variable doesn't exist in this block or it isint initialized
//				or the wanted type of the argument doesn't expect the new param,
//				or if the given variable is a constant and it's not fit the wanted type.
				if (variableObject != null && (!variableObject.isInitialized()
						|| !params.get(i).approvedType(variableObject.getType()))
						|| (variableObject == null && !params.get(i).checkValueType(variable))) {
					throw new InvalidMethodSignatureException();
				}

			}
		} else {
			throw new InvalidMethodSignatureException();
		}
	}
}
