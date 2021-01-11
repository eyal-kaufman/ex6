package main;

import handlers.exception.InvalidActionTerms;
import main.exceptions.InvalidArgsInMain;
import parser.exception.ActionSyntaxInvalidException;
import variables.VariableException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * this is the main class, runs the whole program
 * making sure that the file given to us by the user are valid
 */
public class Sjavac {

	/**
	 * printed if the program is ilegal
	 */
	final static private int ILElGAL = 1;
	/**
	 * printed if the program is legal
	 */
	final static private int LEGAL = 0;
	/**
	 * printed if the program has an IO exception
	 */
	final static private int IO_EXCEPTION = 2;

	/**
	 * this function runs the program making sure that the file is written and
	 * handled in a valid way.
	 * @param args - the file the user sends to check the files content validity.
	 */
	public static void main(String[] args){

		try {
			if (args.length != 1) {
				throw new InvalidArgsInMain();
			}
			BufferedReader buffer = new BufferedReader(new FileReader(args[0]));
			ReadFile.readFirst(buffer);
			ReadFile.readFunctionsData();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.out.println(IO_EXCEPTION);
		} catch (InvalidArgsInMain | VariableException | ActionSyntaxInvalidException | InvalidActionTerms e) {
			System.err.println(e.getMessage());
			System.out.println(ILElGAL);
		}
		System.out.println(LEGAL);
	}
}
