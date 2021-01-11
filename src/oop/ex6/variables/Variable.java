package oop.ex6.variables;


import java.util.regex.Pattern;

/**
 * define variable in this project
 */
public class Variable {
	/** final modifier*/
	public static final String FINAL_MODIFIER = "final";
	/** name of the variable*/
	private final String name;
	/** type of the variable*/
	private final Types type;
	/** indicates if variable is final*/
	private final boolean isFinal;
	/** indicates if variable is initalized*/
	private boolean isInitialized;
	/** indicates if variable is global*/
	private final boolean isGlobal;

	/** the pattern define if given string is valid variable name*/
	private static final Pattern validNamePattern = Pattern.compile("_+[a-zA-Z0-9]+|_*[a-zA-Z]\\w*");

	/**
	 *  create new variable
	 * @param name name of variable
	 * @param type type of variable
	 * @param isFinal indicates if variable is final
	 * @param isInitialized indicates if variable is initalized
	 * @param isGlobal indicates if variable is global
	 */
	public Variable(String name, Types type, boolean isFinal, boolean isInitialized, boolean isGlobal) {
		this.name = name;
		this.type = type;
		this.isFinal = isFinal;
		this.isInitialized = isInitialized;
		this.isGlobal = isGlobal;
	}


	/**
	 * get the type
	 * @return type of the variable
	 */
	public Types getType() {
		return type;
	}

	/**
	 * get the name
	 * @return name of the variable
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * check if the given name is valid variable name
	 * @param name given name to check
	 * @return true if it's valid, false else.
	 */
	public static boolean isValidName(String name) {
		return Variable.validNamePattern.matcher(name).matches();
	}

	/**
	 * get if the var is final
	 * @return true if it's final, false else
	 */
	public Boolean isFinal() {
		return isFinal;
	}
	/**
	 * get if the var is initialized
	 * @return true if it's initialized, false else
	 */
	public boolean isInitialized() {
		return isInitialized;
	}

	/**
	 * set the variable to be initialized.
	 */
	public void setInitialized() {
		this.isInitialized = true;
	}
	/**
	 * get if the var is global
	 * @return true if it's global, false else
	 */
	public boolean isGlobal() {
		return isGlobal;
	}


}
