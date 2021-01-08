package parser;

public abstract class  ReturnPackage {

	String line;


	ReturnPackage(String line){
		this.line = line;
	}

	public abstract ReturnPackage getInformation();
}
