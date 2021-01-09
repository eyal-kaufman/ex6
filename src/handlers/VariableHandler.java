package handlers;

import main.Block;
import parser.LineType;
import variables.VariableException;
import variables.VariableFactory;

public class VariableHandler {
	public static void variableDeclare(Block scope, boolean globalFirst, LineType actionLine)
			throws VariableException {
		if (scope.isGlobal() && globalFirst) {
			VariableFactory.parseVariableLine(actionLine.getVariableList()[0], Block.globalVariables);
		}
		else if (!scope.isGlobal() && globalFirst)  {
			scope.addLine(actionLine);
		}
		else if (!scope.isGlobal() && !globalFirst)  {
			VariableFactory.parseVariableLine(actionLine.getVariableList()[0], scope.getVariableMap());
		}
	}
}
