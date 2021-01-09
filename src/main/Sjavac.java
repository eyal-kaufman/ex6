package main;

import main.exceptions.EndingLineException;
import main.exceptions.NoteException;
import variables.VariableFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Sjavac {

	final static private int ILElGAL = 1;
	final static private int LEGAL = 0;
	final static private int IO_EXCEPTION = 2;

	public static void main(String[] args) throws FileNotFoundException {
//		ArrayList<String> parenthesesArray = new ArrayList<>();
//		ArrayList<Block> blockArray = new ArrayList<>();
//		HashMap<String, Functions> functionMap = new HashMap();
//		Block mainBlock = new Block();
//		mainBlock.setIsGlobal();
//		blockArray.add(mainBlock);
		try {
			if(args.length != 1){
				throw new IOException("ERROR: can only put one file");
			}
//			blockArray.clear();
//			divideActions(args[0]);
		} catch (IOException e) {
			System.err.println(e.getMessage());   // fix this exception
		}
		try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
//			ReadFile reading = new ReadFile(br);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
//		String line = br.readLine();
//			BasicChecks bc = new BasicChecks();
//			while(line != null) {
////				Parsers pars = new
//				line = br.readLine();
//			}

//	} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
		}

//		private static void divideActions(String file) throws FileNotFoundException {
//		try(BufferedReader br = new BufferedReader(new FileReader(file))){
//			String line = br.readLine();
//			BasicChecks bc = new BasicChecks();
//			while(line != null){
//
//				line = bc.checkLineBasics(line);
//				if(line.endsWith("{")){
////					blockArray.add(new BlockFactory(line.replace("{","")));
////					parenthesesArray.add("{");
//				}
//				else if(line.endsWith(";")){
////					VariableFactory var = new VariableFactory();
////					var.parseDeclaration(line.replace(";",""));
//				}
//
//				line = br.readLine();
//			}
//		} catch (IOException e){
//			e.printStackTrace();
//			System.out.println(2);
//		} catch(EndingLineException|NoteException e){
//			e.printStackTrace();
//			System.out.println(1);
//		}
//	}


}
