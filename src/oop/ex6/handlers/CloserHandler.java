package oop.ex6.handlers;

import oop.ex6.handlers.exception.InvalidActionTermsException;
import oop.ex6.handlers.exception.ScopeNotClosedException;
import oop.ex6.main.Block;
import oop.ex6.main.Functions;
import oop.ex6.main.ReadFile;
import oop.ex6.parser.LineType;

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
	 *
	 * @param actionLine  the action line represents "}"
	 * @param blocks      the blocks stock
	 * @param globalFirst boolean indicates if it's the first reading
	 * @param wasReturn   if the there was an action return during the method
	 * @throws InvalidActionTermsException in case of invalid }, if it placed wrong.
	 */
	public static void closer(LineType actionLine, Stack<Block> blocks, boolean globalFirst,
	                          boolean wasReturn) throws InvalidActionTermsException {
		if (globalFirst && !wasReturn && (ReadFile.scopeCounter == 1 || ReadFile.scopeCounter < 1)) {
			throw new ScopeNotClosedException();
		} else if (blocks.peek().isFunction() && globalFirst && wasReturn && ReadFile.scopeCounter == 1) {
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
