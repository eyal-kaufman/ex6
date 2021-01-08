package blocks;

import blocks.blockexception.BlockTitleException;

public class AllBlocks {

	String line;


	public AllBlocks(String line){
		this.line = line;
	}

	public boolean isValid() throws BlockTitleException {
		return true;
	}
}
