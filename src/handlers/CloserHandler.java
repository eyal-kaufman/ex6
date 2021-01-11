package handlers;

import handlers.exception.InvalidActionTerms;
import main.Block;
import main.Functions;
import main.ReadFile;
import parser.LineType;

import java.util.Stack;

/**
 * handle the case of encounter } sign.
 */
public class CloserHandler {
	/**
	 * invokes when the current line is a closer "}".
	 * if it is the first reading of the file, it would check if the closer is in a right place.
	 * if the closer comes after return statement and the scope counter indicate the closer is in the method
	 * scope, than it means the method is over.
	 * if it's the advanced reading, so it would escape the current block, and back to the previous one.
	 * @param actionLine the action line represents "}"
	 * @param blocks the blocks stock
	 * @param globalFirst boolean indicates if it's the first reading
	 * @param wasReturn
	 * @throws InvalidActionTerms in case of invalid }, if it placed wrong.
	 */
	public static void closer(LineType actionLine, Stack<Block> blocks, boolean globalFirst,
							  boolean wasReturn) throws InvalidActionTerms {
		if (globalFirst && !wasReturn && (ReadFile.scopeCounter == 1 || ReadFile.scopeCounter < 1)) {
				throw new InvalidActionTerms("there scope is not closed");
		} else if (blocks.peek().isFunction() && globalFirst && wasReturn && ReadFile.scopeCounter==1) {
			((Functions) blocks.peek()).addLine(actionLine);
			blocks.pop();
			return;
		} else if (!globalFirst) {
			blocks.pop();
			return;
		}
		((Functions) blocks.peek()).addLine(actionLine);
	}
}
