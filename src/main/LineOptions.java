package main;

import java.util.regex.Pattern;

public enum LineOptions {

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
	LineOptions(Pattern pattern) {
		this.pattern = pattern;

	}

	public boolean validTemplate(String line) {
		return this.pattern.matcher(line).matches();
	}


}
