package oop.ex6.variables;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * define the types in Sjavac. support the following:
 * checking if given value is in the same type as the enum member.
 * if the given type is approved by enum member.
 */
public enum Types {
	/**
	 * String type member
	 */
	STRING("String", Pattern.compile("\".*\"")) {
		@Override
		public boolean checkValueType(String value) {
			return validateValue(value);
		}

	},
	/**
	 * char type member
	 */
	CHAR("char", Pattern.compile("'.?'")) {
		@Override
		public boolean checkValueType(String value) {
			return validateValue(value);
		}
	},
	/**
	 * int type member
	 */
	INT("int", Pattern.compile("-?\\d+")) {
		@Override
		public boolean checkValueType(String value) {

			return validateValue(value);
		}
	},
	/**
	 * double type member, accept INT as well
	 */
	DOUBLE("double", Pattern.compile("-?\\d*\\.\\d+|-?\\d+\\.\\d*")) {
		@Override
		public boolean approvedType(Types type) {
			return this.equals(type) || INT.approvedType(type);
		}

		@Override
		public boolean checkValueType(String value) {
			return validateValue(value) || INT.checkValueType(value);
		}
	},
	/**
	 * boolean type member, accept double and int as well
	 */
	BOOLEAN("boolean", Pattern.compile("true|false")) {
		@Override
		public boolean approvedType(Types type) {
			return this.equals(type) || DOUBLE.approvedType(type);
		}

		@Override
		public boolean checkValueType(String value) {
			return validateValue(value) || DOUBLE.checkValueType(value);
		}
	};

	/**
	 * the pattern of the enum member to define a value
	 */
	private final Pattern pattern;
	/**
	 * the name of the enum member in the code
	 */
	private final String typeName;
	/**
	 * map the type name and its type enum member
	 */
	public static Map<String, Types> typeMap = Types.loadTypes();

	/**
	 * constructor of enum member
	 *
	 * @param name    the name of the enum type
	 * @param pattern the pattern of the enum member to define a value.
	 */
	Types(String name, Pattern pattern) {
		this.pattern = pattern;
		this.typeName = name;
	}

	/**
	 * check if given type is approved by enum member
	 *
	 * @param type the type to check for approving.
	 * @return true if it's accepted else false.
	 */
	public boolean approvedType(Types type) {
		return this.equals(type);
	}

	/**
	 * check if the given value is fit to the enum member
	 *
	 * @param value value to check
	 * @return true if it's fit, else false.
	 */
	public abstract boolean checkValueType(String value);

	/**
	 * execute the validation for checkValueType.
	 *
	 * @param value the value to check
	 * @return true if it's fit, else false.
	 */
	protected boolean validateValue(String value) {

		return this.pattern.matcher(value.trim()).matches();
	}

	/**
	 * get type name
	 *
	 * @return String of the type name.
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * checks if the value doesn't fit to any type, and if so it would consider a variable
	 * and not a constant.
	 *
	 * @param value the value to check
	 * @return true if it's not fit any type member, false if it's fit to one of them.
	 */
	public static boolean isVariableCasting(String value) {
		return !BOOLEAN.checkValueType(value) && !STRING.checkValueType(value)
				&& !CHAR.checkValueType(value);
	}

	/**
	 * load the map between name of types and the types members,
	 *
	 * @return the map created.
	 */
	private static Map<String, Types> loadTypes() {
		HashMap<String, Types> typeMap = new HashMap<>();
		for (Types type : Types.values()) {
			typeMap.put(type.getTypeName(), type);
		}
		return typeMap;
	}
}
