//package parser;
//
//import variables.Variable;
//
//import java.util.HashMap;
//
//public class IfOrWhileParser extends ReturnPackage{
//
//	String line;
//
//	IfOrWhileParser(String line){
//		super(line);
//	}
//
//	@Override
//	public ReturnPackage getInformation() {
//		String[] splitInformation = line.split("[//(//]");
//		String name = splitInformation[0];
//		String variablesString = line.substring(line.indexOf("(") + 1,line.indexOf(')'));
//		String[] variableList = variablesString.split(",");
//
//	}
//}
