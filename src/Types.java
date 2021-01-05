import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum Types {
	STRING("String", Pattern.compile("^\".*\"")) {
		@Override
		boolean checkValueType(String value) {
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
		boolean checkValueType(String value) {
			return validateValue(value);
		}
	},

	INT("int", Pattern.compile("\\d+")) {
		@Override
		boolean checkValueType(String value) {
			return validateValue(value);
		}
	},
	DOUBLE("double", Pattern.compile("\\d+\\.\\d+")) {
		@Override
		boolean checkValueType(String value) {
			return validateValue(value) || INT.validateValue(value);
		}
	},
	BOOLEAN("boolean", Pattern.compile("true|false")) {
		@Override
		boolean checkValueType(String value) {
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

	abstract boolean checkValueType(String value);

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
		for (Types type : Types.values()) {
			typeMap.put(type.getTypeName(), type);
		}
		return typeMap;
	}
}
