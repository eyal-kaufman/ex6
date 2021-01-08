package main;

import parser.LineType;
import parser.Parsers;
import parser.exception.ActionSyntaxInvalidException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

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
		Block mainBlock = new Block();
		mainBlock.setIsGlobal();
		blockArray.add(mainBlock);
			String line = reader.readLine();
			BasicChecks bc = new BasicChecks();
			while(line != null) {
				boolean wasReturn = false;
				LineType actionLine = Parsers.lineParser(line);
				switch (Objects.requireNonNull(actionLine).getLineType()){
					case CLOSER:
						if(wasReturn){
							
						}
					case IF_LINE:

						blockArray.add(new Block());

					case WHILE_LINE:

					case RETURN_LINE:
						wasReturn = true;
					case EMPTY_LINE:

					case COMMENT:

					case METHOD_INVOKE:

					case METHOD_SIGNATURE:

					case VARIABLE:
				}
				line = reader.readLine();
			}
	}
}
