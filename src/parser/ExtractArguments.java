package parser;

public abstract class ExtractArguments {

	String line;

	ExtractArguments(String line){
		this.line = line;
	}

	public abstract LineType createLineObject();
}
