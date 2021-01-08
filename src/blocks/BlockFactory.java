package blocks;

import blocks.blockexception.NoValidBlockActionException;
import blocks.blockexception.BlockTitleException;

public class BlockFactory {

	String line;

	public BlockFactory(String line){
		this.line = line;
	}

	public void factory() throws NoValidBlockActionException,NoValidBlockActionException {
		try {
			if (line.startsWith("void")) {
				new MethodBlock(line).isValid();
			} else if (line.startsWith("while") || line.startsWith("if")) {
				new IfAndWhileBlock(line).isValid();
			}else{
				throw new NoValidBlockActionException();
			}
		} catch (BlockTitleException | NoValidBlockActionException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
