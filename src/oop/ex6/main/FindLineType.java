package oop.ex6.main;

import oop.ex6.parser.exception.ActionSyntaxInvalidException;

import java.util.regex.Pattern;

/**
 * this enum finds and creates the type of the specific line being checked
 */
public enum FindLineType {

	/**
	 * the pattern matches a method signature
	 */
	METHOD_SIGNATURE(Pattern.compile("[\t ]*void[\t ]+\\w.*[\t ]*[(][ \t]*.*[ \t]*[)][\t ]*\\{[\t ]*")),
	/**
	 * the pattern matches an if action
	 */
	IF_LINE(Pattern.compile("[\t ]*if[ \t]*[(][ \t]*\\w.*[ \t]*[)][\t ]*\\{[\t ]*")),
	/**
	 * the pattern matches an while action
	 */
	WHILE_LINE(Pattern.compile("[\t ]*while[ \t]*[(][ \t]*\\w.*[ \t]*[)] *\\{[\t ]*")),
	/**
	 * the pattern matches a return
	 */
	RETURN_LINE(Pattern.compile("[\t ]*return[\t ]*;[\t ]*")),
	/**
	 * the pattern matches a comment
	 */
	COMMENT(Pattern.compile("^//.*")),
	/**
	 * the pattern matches a variable
	 */
	VARIABLE(Pattern.compile("[ \t]*\\w.*[\\w'\\\"][ \\t]*;[ \t]*")),
	/**
	 * the pattern matches a function being called
	 */
	METHOD_INVOKE(Pattern.compile("[ \t]*\\w+[ \t]*[(][\t ]*.*[\t ]*[)][\t ]*;[\t ]*")),
	/**
	 * the pattern matches a closer
	 */
	CLOSER(Pattern.compile("[\t ]*}[\t ]*")),
	/**
	 * the pattern matches an empty line
	 */
	EMPTY_LINE(Pattern.compile("\\s*"));

	/**
	 * the pattern being checked
	 */
	private final Pattern pattern;

	/**
	 * return the pattern being matched to
	 *
	 * @param pattern - the pattern matched to the line
	 */
	FindLineType(Pattern pattern) {
		this.pattern = pattern;
	}

	/**
	 * checksif the line sent matches the pattern
	 *
	 * @param line - the line being checked on
	 * @return - if the line is a match to the pattern
	 */
	public boolean validTemplate(String line) {
		return this.pattern.matcher(line).matches();
	}

	/**
	 * finds what kind of object the line is
	 *
	 * @param line- the line being checked on
	 * @return - returns the object the line is
	 * @throws ActionSyntaxInvalidException - if the line matches no object
	 */
	public static FindLineType lineAction(String line) throws ActionSyntaxInvalidException {
		for (FindLineType options : FindLineType.values()) {
			if (options.validTemplate(line)) {
				return options;
			}
		}
		throw new ActionSyntaxInvalidException();
	}
}
