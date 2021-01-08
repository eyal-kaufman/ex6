package blocks.blockexception;

/**
 * this class throws an exception when the start of a block is not a valid action
 */
public class NoValidBlockActionException extends Exception{

		public NoValidBlockActionException(){
			super("ERROR: there is no block that has this action ");
		}

	}
