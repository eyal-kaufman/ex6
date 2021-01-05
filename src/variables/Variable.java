package variables;

import java.util.HashSet;
import java.util.regex.Pattern;

public class Variable {
	private final String name;
	private final Types type;
	private final Boolean isFinal;
	private boolean isInitialized;
	private static final HashSet<String> reservedWords = Variable.loadReservedWords();
	private static final Pattern validNamePattern = Pattern.compile("_+[a-zA-Z0-9]+|_*[a-zA-Z]\\w*");

	public Variable(String name, Types type, Boolean isFinal, boolean isInitialized) {
		this.name = name;
		this.type = type;
		this.isFinal = isFinal;
		this.isInitialized = isInitialized;}

	private static HashSet<String> loadReservedWords(){
		HashSet<String> reservedWords = new HashSet<>(Types.typeMap.keySet());
		reservedWords.add("final");
		return reservedWords;
//		for (variables.Types type: variables.Types.values()) {
//			reservedWords.add()
//		}
	}
	public static boolean isReserved(String name){
		return Variable.reservedWords.contains(name);
	}

	public Types getType() {
		return type;
	}

	public static boolean isValidName(String name) {
		return Variable.validNamePattern.matcher(name).matches();
	}
}
