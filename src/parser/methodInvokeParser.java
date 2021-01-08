package parser;

public class methodInvokeParser extends ExtractArguments{
	methodInvokeParser(String line) {
		super(line);
	}

	@Override
	public LineType createLineObject() {
		return null;
	}
}
