import variables.Variable;
import variables.VariableFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {


	public boolean checkName(String name) {
		Pattern pattern = Pattern.compile("_+[a-zA-Z0-9]+|_*[a-zA-Z]\\w*");
		return pattern.matcher(name).matches();
	}


	private boolean validType(String toCheck) {
		return true;
	}
	public boolean validSemicolonSuffix(String var){
		b = "";
		b = "a";
		Pattern pattern = Pattern.compile(";$");
		return pattern.matcher(var).matches();
	}
	public boolean variableCheck(String line) {

		String[] splitLine= line.trim().split(" ", 3);
		//			if the length is smaller than 2, then the variables have been declared without a type.
		//			or the type is not valid, or the line doesn't end with ; sign.
		int c;
		if (splitLine.length <2 || !this.validType(splitLine[0])
			|| !this.validSemicolonSuffix(splitLine[splitLine.length-1])) {

			return false;
		}
		Pattern pattern = Pattern.compile("[^,;]+? *=* *[^,;]+ *[,;]");
		Matcher matcher = pattern.matcher(splitLine[1]);
		while (matcher.find()) {
			splitLine[1].substring(matcher.start(),matcher.end());
//			tryyy

		}
		return true;
	}
	private String b;
	public static void main(String[] args) {
		Map<String, Variable> global = new HashMap<>();
		Map<String, Variable> block = new HashMap<>();

		VariableFactory fac = new VariableFactory();
		int i, a ;
//		i = 0, a=1;
		String[] Lines = {"double a, b;", "int i1, i2 = 6;", "char c='Z', f;", "boolean a, b ,c , d = true," +
																			   " e, f = 5;",
				"String a = \"hello\" , b = \"goodbye\";"};
		try {
			for (String line : Lines) {
				fac.parseDeclaration(line, block, global);
			}
			fac.parseDeclaration("int i1, i2 = 6;", block, global);
		} catch (Exception e) {
			System.out.println();
		}




		//		String[] splitLine= "a = 3;".trim().split(" *\\w+ *[=,;] *.*",2)[0].trim().split(" ",1);
//		String[] splitDeclaration = "b=".split("=",2);
//		Pattern pattern = Pattern.compile("[,;]");
//		Matcher matcher = pattern.matcher("a, b = b, int b=0, final int b,c;");
//		while (matcher.find()){
//			System.out.println("a, b = b, int b=0, final int b,c;".substring(matcher.start(),matcher.end()));
//		}
//		System.out.println(splitDeclaration[1]);
//		for (String st : splitDeclaration) {
//			System.out.println(st.equals("") + st);
//
//		}
//		Main m = new Main();
	}
}