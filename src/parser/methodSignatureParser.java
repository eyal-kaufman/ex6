package parser;

public class methodSignatureParser extends ExtractArguments {

	methodSignatureParser(String line) {
		super(line);
	}

	@Override
	public LineType createLineObject() {
		return null;
	}
}
