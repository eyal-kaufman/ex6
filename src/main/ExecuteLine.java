package main;

import handlers.CloserHandler;
import handlers.IfAndWhileHandler;
import handlers.MethodHandler;
import handlers.VariableHandler;
import parser.LineType;
import parser.exception.ActionSyntaxInvalidException;
import variables.VariableException;

import java.util.Stack;

public class ExecuteLine {
	public static boolean wasReturn = false;
	public static void executeLine(LineType actionLine, Block scope, boolean globalFirst,
								   Stack<Block> blockStack)
			throws VariableException, ActionSyntaxInvalidException{
		switch (actionLine.getLineType()) {
		case CLOSER:
			CloserHandler.closer(actionLine, blockStack, globalFirst);
			wasReturn = false;
			ReadFile.scopeCounter--;
			return;
		case METHOD_SIGNATURE:
			MethodHandler.methodSignature(scope, actionLine, blockStack);
			ReadFile.scopeCounter++;
			return;
		case VARIABLE:
			VariableHandler.variableDeclare(scope, globalFirst, actionLine);
			return;
		case RETURN_LINE:
			if (scope.isGlobal()) {
				throw new ActionSyntaxInvalidException("line supposed to be inside a method");
			} else if(globalFirst) {
				scope.addLine(actionLine);
			}
			ExecuteLine.wasReturn = true;
			return;
		case EMPTY_LINE:
			return;
		case COMMENT:
			if (ExecuteLine.wasReturn) {
				throw new ActionSyntaxInvalidException("line not supposed to be after return statement");
			}
			return;
		}
		if(globalFirst) {
			ExecuteLine.isInsideLine(scope);
			scope.addLine(actionLine);
		}

		switch (actionLine.getLineType()) {
		case IF_LINE: case WHILE_LINE:
			if (!globalFirst){
				IfAndWhileHandler.ifAndWhile(scope, actionLine,blockStack);
			}
			ReadFile.scopeCounter ++;
			break;
		case METHOD_INVOKE:
			if (!globalFirst){
				MethodHandler.validMethodCall(actionLine, scope);
			}
		}
	}

	/**
	 * make sure the line is declared in valid place.
	 * @param scope the current scope.
	 * @throws ActionSyntaxInvalidException if a line is not between return statement nad '}' do nothing, else
	 * it would raise an exception
	 */
	private static void isInsideLine(Block scope) throws ActionSyntaxInvalidException{
		//TODO is this true? if was declared return, it's wrong to declare a variable?
		if (scope.isGlobal() || ExecuteLine.wasReturn) {
			throw new ActionSyntaxInvalidException("line supposed to be inside a method and must not be " +
												   "after return statement");
		}
	}
}
