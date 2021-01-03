import blocks.AllBlocks;
import exceptions.EndingLineException;
import exceptions.NoteException;
import blocks.ValidSig;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Sjavac {

	public static ArrayList<ValidSig> signatureArray = new ArrayList<>();
	public static ArrayList<AllBlocks> blockArray = new ArrayList<>();


	public static void main(String[] args){
		try {
			signatureArray.clear();
			blockArray.clear();
			divideActions(args[0]);
		} catch (Exception e) {
			e.printStackTrace();   // fix this exception
		}

	}

	private static void divideActions(String file) throws FileNotFoundException {
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String line = br.readLine();
			BasicChecks bc = new BasicChecks();
			while(line != null){
				line = bc.checkLineBasics(line);
				if(line.endsWith("{")){

				}
				else if(line.endsWith(";")){

				}
				else{

				}
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(2);
		} catch(EndingLineException|NoteException e){
			e.printStackTrace();
			System.out.println(1);
		}
	}
}
