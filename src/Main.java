import main.ReadFile;
import parser.exception.ActionSyntaxInvalidException;
import variables.Types;
import variables.Variable;
import variables.VariableException;
import variables.VariableFactory;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
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

//		VariableFactory fac = new VariableFactory();
//		int a = 09;
//		System.out.println(a == 9);
//		i = 0, a=1;
		String[] Lines = {"double e, f;", "int i1, i2 = 6;", "char ct='Z', fgg;",
				"boolean a, b ,c , d = true, gdgg, hfdhdf = 5;",
				"String hh = \"hello\" , bgg = \"goodbye\";"};
		try {
			BufferedReader reader = new BufferedReader(new FileReader("\\Users\\Eyal's\\IdeaProjects\\ex6" +
																	  "\\src" +
																	  "\\tests\\test009.sjava"));
			ReadFile.readFirst(reader);
//			for (String line : Lines) {
//				VariableFactory.parseVariableLine(line, block);
//			}
//			System.out.println(Types.BOOLEAN.checkValueType("0.5"));
//			fac.parseDeclaration("boolean a, b ,c , d = true, gdgg, hfdhdf = 5;", block, global);
		} catch (IOException | ActionSyntaxInvalidException | VariableException e ) {
			System.out.println(e.getMessage());
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