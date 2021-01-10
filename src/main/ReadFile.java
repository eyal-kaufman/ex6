package main;

import parser.LineType;
import parser.Parsers;
import parser.exception.ActionSyntaxInvalidException;
import variables.VariableException;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/**
 * attempt to read the Sjavac file.
 */
public class ReadFile {

	/** the counter of scope, indicates in what scope are we */
	public static int scopeCounter = 0;
 	/** function hashmap hold all the function blocks in the Sjavac file*/
	public static HashMap<String, Block> functionMap = new HashMap<>();

	/**
	 * attempt to read the file by grouping all the lines in a method together, and check the global
	 * variables.
	 * @param reader bufferReader for reading the file
	 * @throws IOException in case of problem when reading the file
	 * @throws ActionSyntaxInvalidException in case of invalid line.
	 * @throws VariableException in case of invalid variable.
	 */
	public static void readFirst(BufferedReader reader)
			throws IOException, ActionSyntaxInvalidException, VariableException {
		Block globalScope = new Block(null);
		Stack<Block> blockStack = new Stack<>();
		blockStack.add(globalScope);
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
	 * @throws ActionSyntaxInvalidException in case of invalid line.
	 * @throws VariableException in case of invalid variable.
	 */
	public static void readFunctionsData() throws ActionSyntaxInvalidException,VariableException {
		for (Block function: ReadFile.functionMap.values()) {
			Stack<Block> functionStack = new Stack<>();
			functionStack.add(function);
			for (LineType actionLine: function.getBlockLines()) {
				ExecuteLine.executeLine(actionLine, function, false, functionStack);
			}
		}
	}

}
