package main;

import parser.exception.ActionSyntaxInvalidException;
import variables.VariableException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Sjavac {

	final static private int ILElGAL = 1;
	final static private int LEGAL = 0;
	final static private int IO_EXCEPTION = 2;

	public static void main(String[] args) throws FileNotFoundException {

		try {
			if (args.length != 1) {
				throw new IOException("ERROR: can only put one file");
			}
			BufferedReader buffer = new BufferedReader(new FileReader(args[0]));
			ReadFile.readFirst(buffer);
			ReadFile.readFunctionsData();
		} catch (IOException e) {
			System.err.println(e.getMessage()); // fix this exception
		} catch (VariableException e) {
			e.printStackTrace();
		} catch (ActionSyntaxInvalidException e) {
			e.printStackTrace();
		}
	}
}
