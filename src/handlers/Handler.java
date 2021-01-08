package handlers;

import main.Block;
import parser.LineType;

public abstract class Handler {

	LineType myLineType;

	Block myScope;

	Handler(LineType myLineType, Block myScope){
		this.myLineType = myLineType;
		this.myScope = myScope;
	}

}
