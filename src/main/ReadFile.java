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

//	BufferedReader reader;

//	ArrayList<String> parenthesesArray;

//	public static LinkedList<Block> blockStack = new LinkedList<>();
//	public static Stack<Block> blockStack = new Stack<>();
	public static int scopeCounter = 0;

	public static HashMap<String, Block> functionMap = new HashMap<>();
	public static final Block globalScope = new Block(null);
	ReadFile(BufferedReader reader){
//		this.reader = reader;
//		ArrayList<String> parenthesesArray = new ArrayList<>();
//		ArrayList<Block> blockStack = new ArrayList<>();
//		HashMap<String, Block> functionMap = new HashMap<>();
	}
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
//	public void reader() throws IOException, ActionSyntaxInvalidException, VariableException {
//		Block mainBlock = new Block(null);
//		mainBlock.setIsGlobal();
//		blockStack.add(mainBlock);
//			String line = reader.readLine();
////			BasicChecks bc = new BasicChecks();
//			while(line != null) {
//				boolean wasReturn = false;
//				LineType actionLine = Parsers.lineParser(line);
//				if (actionLine == null) {
//					throw new ActionSyntaxInvalidException();
//				}
//				ExecuteLine.executeLine(actionLine, blockStack.peek(), false);
////				switch (Objects.requireNonNull(actionLine).getLineType()){
////					case CLOSER:
////						if(wasReturn){
////
////						}
////					case IF_LINE:
////
////						blockArray.add(new Block());
////
////					case WHILE_LINE:
////
////					case RETURN_LINE:
////						wasReturn = true;
////					case EMPTY_LINE:
////
////					case COMMENT:
////
////					case METHOD_INVOKE:
////
////					case METHOD_SIGNATURE:
////
////					case VARIABLE:
////				}
//				line = reader.readLine();
//			}
//	}
}
