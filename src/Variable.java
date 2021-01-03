import java.util.HashSet;

public class Variable {
	private String name;
	private String Type;
//	private Block block;
	private static final HashSet<String> reservedWords = Variable.loadReservedWords();

	private static HashSet<String> loadReservedWords(){
		HashSet<String> reservedWords = new HashSet<>(Types.typeMap.keySet());
		reservedWords.add("final");
		return reservedWords;
//		for (Types type: Types.values()) {
//			reservedWords.add()
//		}
	}
	public static boolean isReserved(String name){
		return Variable.reservedWords.contains(name);
	}
}
