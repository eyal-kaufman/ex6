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

public class ReadFile {


	public static int scopeCounter = 0;

	public static HashMap<String, Block> functionMap = new HashMap<>();
	private static final Block globalScope = new Block(null);

	public static void readFirst(BufferedReader reader)
			throws IOException, ActionSyntaxInvalidException, VariableException {
		Stack<Block> blockStack = new Stack<>();
		blockStack.add(ReadFile.globalScope);
		String line = reader.readLine();
		while (line != null) {
			Block scope = blockStack.peek();
			LineType actionLine = Parsers.lineParser(line);
			ExecuteLine.executeLine(actionLine, scope, true, blockStack);
			line = reader.readLine();
		}

	}
	public static void readFunctionsData() throws VariableException, ActionSyntaxInvalidException {
		for (Block function: ReadFile.functionMap.values()) {
			Stack<Block> functionStack = new Stack<>();
			functionStack.add(function);
			for (LineType actionLine: function.getBlockLines()) {
				ExecuteLine.executeLine(actionLine, function, false, functionStack);
			}
		}
	}

}
