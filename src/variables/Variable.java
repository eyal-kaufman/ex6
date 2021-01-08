package variables;

import java.util.HashSet;
import java.util.regex.Pattern;

public class Variable {
	private final String name;
	private final Types type;
	private final boolean isFinal;
	private boolean isInitialized;
	private final boolean isGlobal;
//	private final boolean isAmbiguity;
	private static final HashSet<String> reservedWords = Variable.loadReservedWords();
	private static final Pattern validNamePattern = Pattern.compile("_+[a-zA-Z0-9]+|_*[a-zA-Z]\\w*");

	public Variable(String name, Types type, boolean isFinal, boolean isInitialized, boolean isGlobal) {
//		this.isAmbiguity = isAmbiguity;
		this.name = name;
		this.type = type;
		this.isFinal = isFinal;
		this.isInitialized = isInitialized;
		this.isGlobal = isGlobal;
	}

	private static HashSet<String> loadReservedWords(){
		HashSet<String> reservedWords = new HashSet<>(Types.typeMap.keySet());
		reservedWords.add("final");
		return reservedWords;
//		for (variables.Types type: variables.Types.values()) {
//			reservedWords.add()
//		}
	}
//	public static boolean isReserved(String name){
//		return Variable.reservedWords.contains(name);
//	}

	public Types getType() {
		return type;
	}

	public String getName(){
		return this.name;
	}

	public static boolean isValidName(String name) {
		return Variable.validNamePattern.matcher(name).matches();
	}

	public Boolean isFinal() {
		return isFinal;
	}

	public boolean isInitialized() {
		return isInitialized;
	}
	public void setInitialized() {
		this.isInitialized = true;
	}
	public boolean isGlobal() {
		return isGlobal;
	}

//	public boolean isAmbiguity() {
//		return isAmbiguity;
//	}
}
