package main;

import parser.exception.ActionSyntaxInvalidException;

import java.util.regex.Pattern;

public enum FindLineType {

	METHOD_SIGNATURE(Pattern.compile("true|false")),
	IF_LINE(Pattern.compile("true|false")),
	WHILE_LINE(Pattern.compile("true|false")),
	RETURN_LINE(Pattern.compile("true|false")),
	COMMENT(Pattern.compile("true|false")),
	VARIABLE(Pattern.compile("true|false")),
	METHOD_INVOKE(Pattern.compile("true|false")),
	CLOSER(Pattern.compile("true|false")),
	EMPTY_LINE(Pattern.compile("true|false"));

	private final Pattern pattern;
	//TODO
	FindLineType(Pattern pattern) {
		this.pattern = pattern;
	}

	public boolean validTemplate(String line) {
		return this.pattern.matcher(line).matches();
	}

	public static FindLineType lineAction(String line) throws ActionSyntaxInvalidException {
		for (FindLineType options : FindLineType.values()) {
			if (options.validTemplate(line)) {
				return options;
			}
		}
		throw new ActionSyntaxInvalidException();
	}


}
