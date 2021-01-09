package variables;


import main.Block;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class VariableFactory {
//	private final Pattern validNamePattern = Pattern.compile("_+[a-zA-Z0-9]+|_*[a-zA-Z]\\w*");
//	public static LinkedList<AmbiguityVariable> ambiguityVariables= new LinkedList<>();
	private Boolean isFinal;
	private Types type;
	private final Map<String, Variable> blockVariables;
	private final Map<String, Variable> lineVariables;
	private final static Pattern typeAndModifierPattern = Pattern.compile("[ \\t]+\\w+$|\\w+[ \\t]*[,;=]");
	//TODO
//	private final Map<String, Variable> globalVariables = Block.globalVariables;
	private final Map<String, Variable> globalVariables;

	private VariableFactory(String line, Map<String, Variable> blockVariables) throws VariableException{
		this.blockVariables = blockVariables;
		this.globalVariables = this.isGlobalInitializing() ? new HashMap<>():
							   Block.globalVariables;
		this.lineVariables = new HashMap<>();
		this.parseDeclaration(line);
//		this.globalVariables = globalVariables;
	}

	/**
	 * checks if the given variable line is from global scope or not.
	 * @return true if the line is from global scope, false else.
	 */
	private boolean isGlobalInitializing() {
		return this.blockVariables == Block.globalVariables;
	}
	/**
	 * finds the variable in global or in current scope, if exists.
	 * @param name the name of the variable
	 * @return Variable object with the wanted name, if exists in both global and current scope, would
	 * return the current scope, if doesn't exist would return null.
	 */
	private Variable getVariable(String name) {
//		if (this.variableInBlock(name)) {
//			return this.blockVariables.get(name);
//		}
		if(this.blockVariables.containsKey(name)) {
			return this.blockVariables.get(name);
		}
		else if (this.globalVariables.containsKey(name)) {
			return this.globalVariables.get(name);
		}
		return null;
	}
	private boolean variableInBlock(String name) {
		return this.blockVariables.containsKey(name) && !this.blockVariables.get(name).isGlobal();
	}
	private boolean variableInLine(String name) {
		return this.lineVariables.containsKey(name);
	}

	/**
	 * checks the following:
	 * 1. if the variable's name include reserved java words,
	 * 2. it verify this variable name wasn't already declraed in this line
	 * 3. verify it's not initialize more then once in this block.
	 * 4. if the variable it's not initialized in this line, it verifies the variable was already
	 * initialized in global or outer scope.
	 * @param name
	 * @return
	 */
	private boolean checkName(String name) {
//		boolean inLine = this.lineVariables.containsKey(name);
//		boolean inBlock = this.blockVariables.containsKey(name);
		return Variable.isValidName(name)
			   && !this.variableInLine(name)
//			   && (!this.variableInBlock(name) && this.type !=null);
			   && ((!this.variableInBlock(name) && this.type !=null)
				   ||(this.type == null &&
//					  (this.variableInBlock(name) || this.globalVariables.containsKey(name))));
					  (this.blockVariables.containsKey(name) || this.globalVariables.containsKey(name))));

	}
//	public boolean isValidName(String name) {
//		return this.validNamePattern.matcher(name).matches();
//	}

	public static void parseVariableLine(String line, Map<String, Variable> blockVariables) throws VariableException{
		new VariableFactory(line, blockVariables);
	}

//	public static Variable parseVariable(String line, Map<String, Variable> blockVariables) throws VariableException{
//		new VariableFactory(line, blockVariables);
//	}
	/**
	 * this method get line of variable declaration that is finished with , or ;
	 * and create from the given line the appropriate variables
	 * @param line
	 */
	private void parseDeclaration(String line) throws VariableException{
//		this.blockVariables = blockVariables;
//		this.globalVariables = globalVariables;
//		this.lineVariables = new HashMap<>();

			int numberOfPrefix = this.getTypeAndModifier(line.trim());
			String[] splitLine = line.trim().split(" ", numberOfPrefix+1);

			String[] variables = splitLine[numberOfPrefix].split(",");
			if (variables.length != 1 && this.type == null) {
				throw new VariableException("it's not allowed to cast multi variables in single line");
			}
			for (int i=0 ; i<variables.length; i++) {
				String variable =variables[i].trim();;
				if (i==variables.length-1 && variables[i].trim().endsWith(";")) {
					variable = variable.substring(0,variable.length()-1);
				}
				this.checkVariable(variable, variable.contains("="));
			}
//			for (String var: variables) {
//				this.checkVariable(var, var.contains("="));
//			}


	}

//	/**
//	 * checks if a variable wasn't initlized exists in this block
//	 * @param name
//	 * @return 0 exists in this block, 1 exists in outer block,  -1 doesn't exists
//	 */
//	private int isAlreadyExists(String name) {
//		return 0;
//	}
	private boolean checkValue(String[] nameAndValue) {
		String value = nameAndValue[1].trim();
		String name = nameAndValue[0].trim();
		if (this.type == null) {
			Variable outerVariable = this.getVariable(name);
			if (outerVariable == null || outerVariable.isFinal() && outerVariable.isInitialized()) {
				return false;
			}
			this.type = outerVariable.getType();
		}
		return this.type.checkValueType(value) ||
			   (Types.isVariableCasting(value) &&
				(this.getVariable(value) != null && this.getVariable(value).isInitialized()));
	}

	private void checkVariable(String var, boolean isCasting) throws VariableException{
		String[] nameAndValue = var.split("=",2);
		String variableName = nameAndValue[0].trim();
		/**
		 * 1. name is not valid.
		 * 2. type is null without trying to cast a value to the variable
		 * 3. the value to cast is not valid
		 */
		if (!this.checkName(variableName) || (this.type == null && !isCasting)
			||isCasting && !this.checkValue(nameAndValue)) {
			throw new VariableException("variable named " +variableName + " is not valid");
		}
		this.updateMap(isCasting, variableName);
//			createVariable(variableName, VariableValue, this.type, ???);



	}

	private void updateMap(boolean isCasting, String variableName) {
		Variable oldVariable = this.getVariable(variableName);
		boolean isGlobal;

		if ((this.variableInBlock(variableName) || this.isGlobalInitializing())&& oldVariable != null) {
			oldVariable.setInitialized();
			return;
		} else if (this.blockVariables.containsKey(variableName)){
			isGlobal = true;
//			newVariable = new Variable(variableName, this.type, this.isFinal, isCasting, isGlobal);

		} else //			newVariable = new Variable(variableName, this.type, this.isFinal, isCasting,
			// isGlobal);
			if (oldVariable != null) {
			isGlobal = false;
//			newVariable = new Variable(variableName, this.type, this.isFinal, isCasting, isGlobal);
		}
		else isGlobal = this.isGlobalInitializing();
		Variable newVariable = new Variable(variableName, this.type, this.isFinal, isCasting, isGlobal);
		this.blockVariables.put(variableName, newVariable);
		this.lineVariables.put(variableName, newVariable);

	}

	/**
	 * split given line into type and modifier if exists.
	 * @param line the line to split
	 * @return String array of type and modifier
	 */
	private String[] splitLineTypeAndModifier(String line) {
		//		Pattern splitTypeAndModifier = Pattern.compile("[ \\t]+\\w+$|\\w+[ \\t]*[,;=]");
		Matcher matcher = VariableFactory.typeAndModifierPattern.matcher(line.trim());
		String[] splitLine;
		if (matcher.find()) {
			splitLine = line.substring(0,matcher.start()).trim().split(" ");
		} else {
			splitLine = new String[]{""};
		}
		return splitLine;
	}
//(\w+$)|(\w+[ \t]*[,;=])
	/**
	 * checks if there is valid final modifier and/or valid type name.
	 * @param line line contains variable declaration that ends with ; or ,
	 * @return 0 if there is no type/ final in the declaration. 1 if there is valid type name, 2 if there are
	 * both valid final modifier and type name.
	 */
	private int getTypeAndModifier(String line) throws VariableException{
		String [] splitLine = this.splitLineTypeAndModifier(line);
//		String[] splitLine = line.trim().split(" *\\w+ *[=,;] *.*",2)[0].split(" ");
		if (splitLine.length == 2 && this.validFinal(splitLine[0]) && this.validType(splitLine[1])) {
			return splitLine.length;
		} else if (splitLine.length == 1 && this.validType(splitLine[0])) {
			this.isFinal = false;
			return splitLine.length;
		}
		else if (splitLine.length==0 || splitLine.length == 1 && splitLine[0].equals("")) {
			this.isFinal = false;
			this.type = null;
			return 0;
		}
		else {
			throw new VariableException("invalid variable declaration, wrong format of declaration");
		}
	}
	private boolean validType(String toCheck) {
		if (Types.typeMap.containsKey(toCheck)) {
			this.type = Types.typeMap.get(toCheck);
			return true;
		}
		return false;
	}
	private boolean validFinal(String modifier) {
		return this.isFinal = modifier.equals("final");
	}
//	public boolean findVariables(Matcher matcher, String[] splitLine) {
//		while (matcher.find()) {
//			splitLine[1].substring(matcher.start(), matcher.end());
//		}
//		return true;
//	}

//	public void parseDeclaration(String replace) {
//	}
//	public boolean checkVariables(String declaration, String type) {
//		String[] splitDeclaration = declaration.split("=[^\"]",2);
//		if (this.checkName(splitDeclaration[0]) && splitDeclaration.length>1) {
//
//		}
//	}
}
