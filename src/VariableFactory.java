import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VariableFactory {
	private final Pattern validNamePattern = Pattern.compile("_+[a-zA-Z0-9]+|_*[a-zA-Z]\\w*");
	private Boolean isFinal;
	private String type;

	public boolean checkName(String name) {
		return this.validNamePattern.matcher(name).matches() && !Variable.isReserved(name);
	}

	/**
	 * this method get line of variable declaration that is finished with , or ;
	 * and create from the given line the appropriate variables
	 * @param line
	 */
	public void parseDeclaration(String line){
		try {
			int numberOfPrefix = this.getTypeAndModifier(line);
			String[] splitLine = line.trim().split(" ", numberOfPrefix+1);
//			this is new variable declaration
//				Pattern pattern = Pattern.compile("[^,;]+? *=* *[^,;]+ *[,;]");
//				Pattern pattern = Pattern.compile("[,;]");
//				Matcher matcher = pattern.matcher(splitLine[numberOfPrefix]);
//				while (matcher.find()) {
//					splitLine[1].substring(matcher.start(), matcher.end());
//				}
			String[] variables = splitLine[numberOfPrefix].split("[,;]");
			if (variables.length != 1 && this.type == null) {
				throw new Exception("it's not allowed to cast multi variables in single line");
			}
			for (String var: splitLine[numberOfPrefix].split("[,;]")) {
				this.checkVariable(var);
			}
		}
		catch (Exception e) {
//			Todo
		}
	}

	/**
	 * checks if a variable already exists in this block
	 * @param name
	 * @return 0 exists in this block, 1 exists in outer block,  -1 doesn't exists
	 */
	private int isAlreadyExists(String name) {
		return 0;
	}
	private boolean checkValue(String value) {
		return true;
	}
	private void checkVariable(String var) throws Exception{
		String[] nameAndValue = var.split("[^\"'] *= *");
		String variableName = nameAndValue[0];
		if (nameAndValue.length > 2 || !this.checkName(variableName)
			||nameAndValue.length == 2 && !this.checkValue(nameAndValue[1])) {
			throw new Exception("invalid variable name");
		} else if (nameAndValue.length == 2) {
//			createVariable(variableName, VariableValue, this.type, this.value);
		} else {
//			createVariable(variableName, VariableValue, this.type, ???);
		}


	}

	/**
	 * checks if there is valid final modifier and/or valid type name.
	 * @param line line contains variable declaration that ends with ; or ,
	 * @return 0 if there is no type/ final in the declaration. 1 if there is valid type name, 2 if there are
	 * both valid final modifier and type name.
	 */
	private int getTypeAndModifier(String line) throws Exception{
		String[] splitLine = line.trim().split(" *\\w+ *[=,;] *.*",2)[0].split(" ");
		if (splitLine.length == 2 && this.validFinal(splitLine[0]) && this.validType(splitLine[1])) {
			return splitLine.length;
		} else if (splitLine.length == 1 && this.validType(splitLine[0])) {
			this.isFinal = false;
			return splitLine.length;
		}
		else if (splitLine.length==0) {
			this.isFinal = false;
			this.type = null;
			return splitLine.length;
		}
		else {
			throw new Exception("invalid variable declaration");
		}
	}
	private boolean validType(String toCheck) {
		if (Types.typeMap.containsKey(toCheck)) {
			this.type = toCheck;
			return true;
		}
		return false;
	}
	private boolean validFinal(String modifier) {
		return this.isFinal = modifier.equals("final");
	}
	public boolean findVariables(Matcher matcher, String[] splitLine) {
		while (matcher.find()) {
			splitLine[1].substring(matcher.start(), matcher.end());
		}
		return true;
	}
//	public boolean checkVariables(String declaration, String type) {
//		String[] splitDeclaration = declaration.split("=[^\"]",2);
//		if (this.checkName(splitDeclaration[0]) && splitDeclaration.length>1) {
//
//		}
//	}
}
