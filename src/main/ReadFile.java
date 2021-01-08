package main;

import parser.Parsers;
import parser.exception.ActionSyntaxInvalidException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadFile {

	BufferedReader reader;

	ArrayList<String> parenthesesArray;

	ArrayList<Block> blockArray;

	HashMap<String, Functions> functionMap;

	ReadFile(BufferedReader reader){
		this.reader = reader;
		ArrayList<String> parenthesesArray = new ArrayList<>();
		ArrayList<Block> blockArray = new ArrayList<>();
		HashMap<String, Functions> functionMap = new HashMap();
	}

	public void reader() throws IOException, ActionSyntaxInvalidException {
		Parsers parser = new Parsers();
		Block mainBlock = new Block();
		mainBlock.setIsGlobal();
		blockArray.add(mainBlock);
			String line = reader.readLine();
			BasicChecks bc = new BasicChecks();
			while(line != null) {
//				int actionLine = parser.lineParser(line);
//				switch (actionLine){
//			            case VARIABLE:
//							VariableFactory vf = new VariableFactory();
//							vf.parseDeclaration(line);
//						case IF:case WHILE:
//
//						case RETURN:
//
//						case CLOSE_BLOCK:
//
//						case EMPTY_LINE:
//						case COMMENT:
//						case FUNC_SIG:
//
//						case CALL_FUNC:
//				}
				line = reader.readLine();
			}
	}
}
