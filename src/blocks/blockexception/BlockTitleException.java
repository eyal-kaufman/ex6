package blocks.blockexception;
/**
 * this class throws an exception when blocks title is invalid.
 */
public class BlockTitleException extends Exception{

	/**
	 * this function has an informative message explaining what the exception is
	 */
	public BlockTitleException(){
		super("ERROR: the blocks title is invalid ");
	}

}
