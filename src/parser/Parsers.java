package parser;

import main.FindLineType;
import parser.exception.ActionSyntaxInvalidException;

public class Parsers {

	private final static int VARIABLE = 1;
	private final static int IF = 2;
	private final static int WHILE = 3;
	private final static int RETURN = 4;
	private final static int CLOSE_BLOCK = 5;
	private final static int COMMENT = 6;
	private final static int EMPTY_LINE = 7;
	private final static int FUNC_SIG = 8;
	private final static int CALL_FUNC = 9;

	private final static String[] ACTION_VALIDITY = new String[]{"a", "b", "c"};


	public static LineType lineParser(String line) throws ActionSyntaxInvalidException {
		FindLineType findLineType = FindLineType.lineAction(line);
		IfParser lineInfo = new IfParser(line);
		switch (findLineType){
			case CLOSER:

			case IF_LINE:
				return lineInfo.createLineObject();
			case WHILE_LINE:

			case RETURN_LINE:

			case EMPTY_LINE:

			case COMMENT:

			case METHOD_INVOKE:

			case METHOD_SIGNATURE:

			case VARIABLE:
		}
		return lineInfo.createLineObject();
	}

}
