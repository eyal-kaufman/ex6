package parser;

import parser.exception.ActionSyntaxInvalidException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


	public int lineParser(String line) throws ActionSyntaxInvalidException {
		Pattern pattern;
		Matcher match;
		int sign = 0;
		for (int i = 0; i < ACTION_VALIDITY.length; i++) {
			pattern = Pattern.compile(ACTION_VALIDITY[i]);
			match = pattern.matcher(line);
			if (match.matches()) {
				sign = i + 1;
				break;
			}
		}
		if (sign == 0) {
			throw new ActionSyntaxInvalidException();
		}
		return sign;
//		switch (sign) {
//			case VARIABLE:
//				VariableFactory vf = new VariableFactory();
//				vf.parseDeclaration(line);
//
//			case IF:case WHILE:
//
//			case RETURN:
//
//			case CLOSE_BLOCK:
//
//			case EMPTY_LINE:
//			case COMMENT:
//			case FUNC_SIG:
//			case CALL_FUNC:
//		}
	}

}
