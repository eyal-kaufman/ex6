import java.util.regex.Pattern;

public class SignatureCheck {
	private static final Pattern ifSignature = Pattern.compile("if[ \t]*[(][ \t]*\\w+[ \t]*[)] *\\{");
	private static final Pattern whileSignature =Pattern.compile("while[ \t]*[(][ \t]*\\w+[ \t]*[)] *\\{");
	public static boolean ifSignature(String line) {

		return SignatureCheck.ifSignature.matcher(line.trim()).matches();
		}
	public static boolean whileSignature(String line) {
		return SignatureCheck.whileSignature.matcher(line.trim()).matches();
	}
}


