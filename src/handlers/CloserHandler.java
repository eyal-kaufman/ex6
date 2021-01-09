package handlers;

import main.Block;
import main.ExecuteLine;
import main.ReadFile;
import parser.LineType;
import parser.exception.ActionSyntaxInvalidException;

import java.util.Stack;

public class CloserHandler {

	public static void closer(LineType actionLine, Stack<Block> blocks, boolean globalFirst) throws ActionSyntaxInvalidException {
		if (globalFirst && !ExecuteLine.wasReturn && ReadFile.scopeCounter == 1 || ReadFile.scopeCounter < 1) {
			throw new ActionSyntaxInvalidException();
		} else if (globalFirst && ExecuteLine.wasReturn && ReadFile.scopeCounter==1) {
			blocks.peek().addLine(actionLine);
			blocks.pop();
//			TODO to check if should add "wasReutrn = false";
			return;
		} else if (!globalFirst) {
			ExecuteLine.wasReturn = false;
			blocks.pop();
		}
		blocks.peek().addLine(actionLine);
		ExecuteLine.wasReturn = false;

	}
}
