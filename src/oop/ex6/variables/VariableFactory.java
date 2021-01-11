package oop.ex6.variables;


import oop.ex6.main.ReadFile;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * create variable object from given line and add it to the block map directly.
 */
public class VariableFactory {
	/** is final declaration*/
	private Boolean isFinal;
	/** what is the type of the declaration*/
	private Types type;
	/** hold all the oop.ex6.variables defined in this block already*/
	private final Map<String, Variable> blockVariables;
	/** hold all the oop.ex6.variables defined in this line already*/
	private final Map<String, Variable> lineVariables;
	/** pattern for split the type and modifier from the original line*/
	private final static Pattern typeAndModifierPattern = Pattern.compile("[ \\t]+\\w+$|\\w+[ \\t]*[,;=]");
	/** hold all the oop.ex6.variables defined in global scope*/
	private final Map<String, Variable> globalVariables;

	/**
	 * invoke from static method trigger the factory.
	 * @param line the line to try creating variable from
	 * @param blockVariables the current variable block
	 * @throws VariableException in case the line it's not valid.
	 */
	private VariableFactory(String line, Map<String, Variable> blockVariables) throws VariableException{
		this.blockVariables = blockVariables;
		// if the global map is the same as the block map, so it's global initializing, and the global map
		// would be empty in this case.
		this.globalVariables = this.isGlobalInitializing() ? new HashMap<>():
							   ReadFile.globalVariables;
		this.lineVariables = new HashMap<>();
		this.parseDeclaration(line);

	}

	/**
	 * Invokes when trying to parse argument from method signature
	 * @param line the line to try creating variable from
	 * @param blockVariables the current variable block
	 * @param variableToReturn set to load into the single variable declared in this process
	 * @throws VariableException in case the line it's not valid
	 */
	private VariableFactory(String line, Map<String, Variable> blockVariables,
							Set<Variable> variableToReturn) throws VariableException{
		this(line, blockVariables);
		variableToReturn.addAll(this.lineVariables.values());
	}

	/**
	 * invoke for creating oop.ex6.variables object and add them to the block map.
	 * this method get line of variable declaration that is finished with , or ;
	 * and create from the given line the appropriate oop.ex6.variables
	 * @param line the line to create oop.ex6.variables from.
	 * @param blockVariables the current block map
	 * @throws VariableException in case of invalid oop.ex6.variables.
	 */
	public static void parseVariableLine(String line, Map<String, Variable> blockVariables) throws VariableException{
		new VariableFactory(line, blockVariables);
	}

	/**
	 * invoke when trying to create Variable object from arguments declared in method signature.
	 * @param line the line to create oop.ex6.variables from.
	 * @param blockVariables the current block map
	 * @return the single Variable that just created.
	 * @throws VariableException in case of invalid oop.ex6.variables.
	 */
	public static Variable createVariableFromArgument(String line, Map<String, Variable> blockVariables)
			throws VariableException{
		HashSet<Variable> singleVariableSet = new HashSet<>();
		new VariableFactory(line, blockVariables, singleVariableSet);
		return singleVariableSet.iterator().next();
	}
	/**
	 * checks if the given variable line is from global scope or not.
	 * @return true if the line is from global scope, false else.
	 */
	private boolean isGlobalInitializing() {
		return this.blockVariables == ReadFile.globalVariables;
	}
	/**
	 * finds the variable in global or in current scope, if exists.
	 * @param name the name of the variable
	 * @return Variable object with the wanted name, if exists in both global and current scope, would
	 * return the current scope, if doesn't exist would return null.
	 */
	private Variable getVariable(String name) {

		if(this.blockVariables.containsKey(name)) {
			return this.blockVariables.get(name);
		}
		else if (this.globalVariables.containsKey(name)) {
			return this.globalVariables.get(name);
		}
		return null;
	}

	/**
	 * check if given name exists in the block (but it's not global)
	 * @param name the name to check
	 * @return true if it exist only in the block, false else.
	 */
	private boolean variableInBlock(String name) {
		return this.blockVariables.containsKey(name) && !this.blockVariables.get(name).isGlobal();
	}

	/**
	 * check if a variable exist in this line already
	 * @param name the name to check
	 * @return true if it's exist in line, false else.
	 */
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
	 * @param name the name of the variable
	 * @return true if it's valid, false else.
	 */
	private boolean checkName(String name) {

		return Variable.isValidName(name)
			   && !this.variableInLine(name)
			   && ((!this.variableInBlock(name) && this.type !=null)
				   ||(this.type == null &&
					  (this.blockVariables.containsKey(name) || this.globalVariables.containsKey(name))));

	}




	/**
	 * execute the parsing from variable statement
	 * @param line the line to create oop.ex6.variables from.
	 * @throws VariableException in case of invalid oop.ex6.variables.
	 */
	private void parseDeclaration(String line) throws VariableException{

			int numberOfPrefix = this.getTypeAndModifier(line.trim());
			String[] splitLine = line.trim().split(" ", numberOfPrefix+1);

			String[] variables = splitLine[numberOfPrefix].split(",");
			if (variables.length != 1 && this.type == null) {
				throw new VariableException("it's not allowed to cast multi oop.ex6.variables in single line");
			}
			for (int i=0 ; i<variables.length; i++) {
				String variable =variables[i].trim();;
				if (i==variables.length-1 && variables[i].trim().endsWith(";")) {
					variable = variable.substring(0,variable.length()-1);
				}
				this.checkVariable(variable, variable.contains("="));
			}


	}

	/**
	 * check if the given value is fit to the type in decleration if exist, or to the
	 * variable already declared.
	 * @param nameAndValue name and value of a variable
	 * @return true if it's fit, false else.
	 */
	private boolean checkValue(String[] nameAndValue) {
		String value = nameAndValue[1].trim();
		String name = nameAndValue[0].trim();
		if (this.type == null) {
			Variable outerVariable = this.getVariable(name);
//			if (outerVariable == null || outerVariable.isFinal() && outerVariable.isInitialized()) {
			if (outerVariable == null || outerVariable.isFinal()) {
				return false;
			}
			this.type = outerVariable.getType();
		}
		return this.type.checkValueType(value) ||
			   (Types.isVariableCasting(value) &&
				(this.getVariable(value) != null
				 && this.type.approvedType(this.getVariable(value).getType())
				 &&this.getVariable(value).isInitialized()));
	}

	/**
	 * check if variable is valid, check the name and value of it.
	 * if it's valid it would create new Variable object and add it to the block map.
	 * @param var variable line exculding the type and modifier
	 * @param isCasting expreesion indicates if it's casting or not.
	 * @throws VariableException in case of invalid variable.
	 */
	private void checkVariable(String var, boolean isCasting) throws VariableException{
		String[] nameAndValue = var.split("=",2);
		String variableName = nameAndValue[0].trim();
		 // check the following:
		 //name is not valid.
		 //type is null without trying to cast a value to the variable
		 //the value to cast is not valid
		if (!this.checkName(variableName) || (this.type == null || this.isFinal) && !isCasting
			||isCasting && !this.checkValue(nameAndValue)) {
			throw new VariableException("variable named " +variableName + " is not valid");
		}
		this.updateMap(isCasting, variableName);



	}

	/**
	 * get valid variable, and create Variable object or update an existing one, if
	 * creating new one, it would add it to the block map.
	 * @param isCasting expreesion indicates if it's casting or not.
	 * @param variableName the name of the variable
	 */
	private void updateMap(boolean isCasting, String variableName) {
		Variable oldVariable = this.getVariable(variableName);
		boolean isGlobal;

		if ((this.variableInBlock(variableName) || this.isGlobalInitializing())&& oldVariable != null) {
			oldVariable.setInitialized();
			return;
		} else if (this.blockVariables.containsKey(variableName)){
			isGlobal = true;

		} else
			if (oldVariable != null) {
			isGlobal = false;

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
		Matcher matcher = VariableFactory.typeAndModifierPattern.matcher(line.trim());
		String[] splitLine;
		if (matcher.find()) {
			splitLine = line.substring(0,matcher.start()).trim().split(" ");
		} else {
			splitLine = new String[]{""};
		}
		return splitLine;
	}

	/**
	 * checks if there is valid final modifier and/or valid type name.
	 * @param line line contains variable declaration that ends with ; or ,
	 * @return 0 if there is no type/ final in the declaration. 1 if there is valid type name, 2 if there are
	 * both valid final modifier and type name.
	 */
	private int getTypeAndModifier(String line) throws VariableException{
		String [] splitLine = this.splitLineTypeAndModifier(line);

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

	/**
	 * check if the type extracted is valid.
	 * @param toCheck type extracted from the line
	 * @return true if it's valid, fasle else.
	 */
	private boolean validType(String toCheck) {
		if (Types.typeMap.containsKey(toCheck)) {
			this.type = Types.typeMap.get(toCheck);
			return true;
		}
		return false;
	}

	/**
	 * check if extracted modifier is equals to final modifier.
	 * @param modifier the extracted mdofifer
	 * @return true if it's fit, false else.
	 */
	private boolean validFinal(String modifier) {
		return this.isFinal = modifier.equals(Variable.FINAL_MODIFIER);
	}

}
