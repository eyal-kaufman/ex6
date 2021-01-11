package oop.ex6;

import oop.ex6.handlers.exception.InvalidActionTerms;
import oop.ex6.main.ReadFile;
import oop.ex6.parser.exception.ActionSyntaxInvalidException;
import oop.ex6.variables.VariableException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {

		try {
			BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\kaszo\\Documents\\oop\\ex6\\src\\oop\\ex6\\tets"));
			ReadFile.readFirst(reader);
			ReadFile.readFunctionsData();

		} catch (IOException | ActionSyntaxInvalidException | VariableException | InvalidActionTerms e ) {
			System.out.println(e.getMessage());
		}

	}
}