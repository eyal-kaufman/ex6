package parser;

public class returnParser extends ExtractArguments{
	returnParser(String line) {
		super(line);
	}

	@Override
	public LineType createLineObject() {
		return null;
	}
}
