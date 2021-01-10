package main;

import parser.exception.ActionSyntaxInvalidException;

import java.util.regex.Pattern;

public enum FindLineType {

	METHOD_SIGNATURE(Pattern.compile("[\t ]*void[\t ]+\\w.*[\t ]*[(][ \t]*.*[ \t]*[)][\t ]*\\{[\t ]*")),
	IF_LINE(Pattern.compile("[\t ]*if[ \t]*[(][ \t]*\\w.*[ \t]*[)][\t ]*\\{[\t ]*")),
	WHILE_LINE(Pattern.compile("[\t ]*while[ \t]*[(][ \t]*\\w.*[ \t]*[)] *\\{[\t ]*")),
	RETURN_LINE(Pattern.compile("[\t ]*return[\t ]*;[\t ]*")),
	COMMENT(Pattern.compile("^//.*")),
	VARIABLE(Pattern.compile("[ \t]*\\w.*[\\w'\\\"][ \\t]*;[ \t]*")),
	METHOD_INVOKE(Pattern.compile("[ \t]*\\w+[ \t]*[(][\t ]*.*[\t ]*[)][\t ]*;[\t ]*")),
	CLOSER(Pattern.compile("[\t ]*}[\t ]*")),
	EMPTY_LINE(Pattern.compile("\\s*"));

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
