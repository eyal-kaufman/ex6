package parser;

public class whileParser extends ExtractArguments{

	whileParser(String line) {
		super(line);
	}

	@Override
	public LineType createLineObject() {
		return null;
	}
}
