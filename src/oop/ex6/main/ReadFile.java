package oop.ex6.main;

import oop.ex6.handlers.exception.InvalidActionTermsException;
import oop.ex6.handlers.exception.ScopeNotClosedException;
import oop.ex6.parser.LineType;
import oop.ex6.parser.Parsers;
import oop.ex6.parser.exception.ActionSyntaxInvalidException;
import oop.ex6.variables.Variable;
import oop.ex6.variables.exceptions.VariableException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;

/**
 * attempt to read the Sjavac file.
 */
public class ReadFile {
	/**
	 * indicates if the last statement was return;
	 */
	public static boolean wasReturn;
	/**
	 * the counter of scope, indicates in what scope are we
	 */
	public static int scopeCounter;
	/**
	 * function hashmap hold all the function blocks in the Sjavac file
	 */
	public static HashMap<String, Functions> functionMap;
	/**
	 * hold mapping of oop.ex6.variables objects in global scope and their name
	 */
	public static HashMap<String, Variable> globalVariables;

	/**
	 * attempt to read the file by grouping all the lines in a method together, and check the global
	 * oop.ex6.variables.
	 *
	 * @param reader bufferReader for reading the file
	 * @throws IOException                  in case of problem when reading the file
	 * @throws ActionSyntaxInvalidException in case of invalid line.
	 * @throws VariableException            in case of invalid variable.
	 * @throws InvalidActionTermsException  in case of invalid content in line
	 */
	public static void readFirst(BufferedReader reader)
			throws IOException, ActionSyntaxInvalidException, VariableException, InvalidActionTermsException {
		scopeCounter = 0;
		functionMap = new HashMap<>();
		wasReturn = false;
		globalVariables = new HashMap<>();
		Block globalScope = new Block(null);
		Stack<Block> blockStack = new Stack<>();
		blockStack.push(globalScope);
		String line = reader.readLine();
		while (line != null) {
			Block scope = blockStack.peek();
			LineType actionLine = Parsers.lineParser(line);
			ExecuteLine.executeLine(actionLine, scope, true, blockStack);
			line = reader.readLine();
		}

	}

	/**
	 * advanced reading of the Sjavac file, iterates over the function in this file that has been found
	 * in the first reading, and check all the lines refer to it.
	 *
	 * @throws ActionSyntaxInvalidException in case of invalid line.
	 * @throws VariableException            in case of invalid variable.
	 * @throws InvalidActionTermsException  in case of invalid content in line
	 */
	public static void readFunctionsData() throws ActionSyntaxInvalidException, VariableException,
												  InvalidActionTermsException {

		wasReturn = false;
		for (Functions function : ReadFile.functionMap.values()) {

			Stack<Block> functionStack = new Stack<>();
			functionStack.push(function);
			for (LineType actionLine : function.getBlockLines()) {
				ExecuteLine.executeLine(actionLine, functionStack.peek(), false, functionStack);
			}
		}
		if (ReadFile.scopeCounter == 1) {
			throw new ScopeNotClosedException();
		}
	}
}
