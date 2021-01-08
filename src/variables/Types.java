package variables;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public enum Types {
	STRING("String", Pattern.compile("^\".*\"")) {
		@Override
		public boolean checkValueType(String value) {
			return validateValue(value);
		}
		//		final Pattern pattern = Pattern.compile("^\".*[\"]");
//		Matcher matcher = pattern.matcher();
//		@Override
//		public boolean validateValue(String value) {
//			return this.pattern.matcher(value.trim()).matches();
//		}
	},
	CHAR("char", Pattern.compile("^'.*'")) {
		@Override
		public boolean checkValueType(String value) {
			return validateValue(value);
		}
	},

	INT("int", Pattern.compile("\\d+")) {
		@Override
		public boolean checkValueType(String value) {

			return validateValue(value);
		}
	},
	DOUBLE("double", Pattern.compile("\\d+\\.\\d+")) {
		@Override
		public boolean checkValueType(String value) {
			return validateValue(value) || INT.validateValue(value);
		}
	},
	BOOLEAN("boolean", Pattern.compile("true|false")) {
		@Override
		public boolean checkValueType(String value) {
			return validateValue(value) || DOUBLE.validateValue(value);
		}
	};


	private final Pattern pattern;
	private final String typeName;
	public static Map<String, Types> typeMap = Types.loadTypes();

	Types(String name, Pattern pattern) {
		this.pattern = pattern;
		this.typeName = name;
	}

	public abstract boolean checkValueType(String value);

	protected boolean validateValue(String value) {

		return this.pattern.matcher(value.trim()).matches();
	}

	public String getTypeName() {
		return typeName;
	}

	public static boolean isVariableCasting(String value) {
		return !BOOLEAN.checkValueType(value) && !STRING.checkValueType(value)
			   && !CHAR.checkValueType(value);
	}

	private static Map<String, Types>  loadTypes() {
		HashMap<String,Types> typeMap = new HashMap<>();
		System.out.println("hi");
		for (Types type : Types.values()) {
			typeMap.put(type.getTypeName(), type);
		}
		return typeMap;
	}
}
