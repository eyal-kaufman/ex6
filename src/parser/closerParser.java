package parser;

public class closerParser extends ExtractArguments{

	closerParser(String line) {
		super(line);
	}

	@Override
	public LineType createLineObject() {
		return null;
	}
}
