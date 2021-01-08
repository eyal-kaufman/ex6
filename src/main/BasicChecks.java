package main;

import main.exceptions.*;

public class BasicChecks {

	public BasicChecks(){}

	public String checkLineBasics(String line) throws NoteException, EndingLineException {
		if(line.contains("//") && !line.startsWith("//")){
			throw new NoteException();
		}
		line = line.replace("//", " ");             //removes all the notes in the line
		if(!line.endsWith("{") || !line.endsWith("}") || !line.endsWith(";")){
			throw new EndingLineException();
		}
		return line;
	}
}
