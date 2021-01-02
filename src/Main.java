import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {


	public boolean checkName(String name) {
		Pattern pattern = Pattern.compile("_+[a-zA-Z0-9]+|_*[a-zA-Z]+\\w");
		return pattern.matcher(name).matches();
	}


	private boolean validType(String toCheck) {
		return true;
	}
	public boolean validSemicolonSuffix(String var){
		Pattern pattern = Pattern.compile(";$");
		return pattern.matcher(var).matches();
	}
	public boolean variableCheck(String line) {
		String[] splitLine= line.trim().split(" ", 1);
		//			if the length is smaller than 2, then the variables have been declared without a type.
		//			or the type is not valid, or the line doesn't end with ; sign.
		if (splitLine.length <2 || !this.validType(splitLine[0])
			|| !this.validSemicolonSuffix(splitLine[splitLine.length-1])) {
			return false;
		}
		Pattern pattern = Pattern.compile("[^,;]+ *=* *[^,;]+ *[,;]");
		Matcher matcher = pattern.matcher(splitLine[1]);
		while (matcher.find()) {
			splitLine[1].substring(matcher.start(),matcher.end());

		}
		return true;
	}

	public static void main(String[] args) {
		Main m = new Main();
		System.out.println(m.checkName("0"));
	}
}