package main;

import handlers.CloserHandler;
import handlers.IfAndWhileHandler;
import handlers.MethodHandler;
import handlers.VariableHandler;
import handlers.exception.InvalidActionTerms;
import parser.LineType;
import parser.exception.ActionSyntaxInvalidException;
import variables.VariableException;

import java.util.Stack;

/**
 * managing the action the have been just read from the Sjavac code.
 */
public class ExecuteLine {

	 /** indicates if the last statement was return;*/
	private static boolean wasReturn = false;

	/**
	 * manage the action the should be done when encounter a line from the code.
	 * @param actionLine the wrap object represents a line
	 * @param scope the current scope
	 * @param globalFirst expression indicates if this is the first reading
	 * @param blockStack stack of scopes
	 * @throws VariableException when the variable is not valid
	 * @throws ActionSyntaxInvalidException when a general problem occur.
	 */
	public static void executeLine(LineType actionLine, Block scope, boolean globalFirst,
								   Stack<Block> blockStack)
			throws VariableException, ActionSyntaxInvalidException, InvalidActionTerms {
		switch (actionLine.getLineType()) {
		case CLOSER:
			CloserHandler.closer(actionLine, blockStack, globalFirst, ExecuteLine.wasReturn);
			ExecuteLine.wasReturn = false;
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
			} else if(globalFirst && scope.isFunction()) {
				((Functions) scope).addLine(actionLine);
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
		if(globalFirst && scope.isFunction()) {
			((Functions) scope).addLine(actionLine);
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
