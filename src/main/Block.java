package main;

import parser.LineType;
import variables.Types;
import variables.Variable;
import variables.VariableException;
import variables.VariableFactory;

import java.util.HashMap;
import java.util.LinkedList;

public class Block {

    public static HashMap<String, Variable> globalVariables = new HashMap<>();
    private final Block previousBlock;
    private final LinkedList<Types> functionTypeParams = new LinkedList<>();
    private final LinkedList<LineType> blockLines = new LinkedList<>();
    /**
     * all the variables in the block
     */
    protected HashMap<String, Variable> variableMap = new HashMap<>();
    public Block(Block previousBlock) {
        this.previousBlock = previousBlock;
        if (previousBlock == null) {
            setIsGlobal();
            this.variableMap = Block.globalVariables;
        }
    }



    /**
     * ids the global block, default is false
     */
    private boolean isGlobal = false;

    /**
     * updates the variable block map with given line of variables, when creating new methods.
     * @param lineType an object wrap line of javas and its purpose.
     * @throws VariableException if one of the variables is not valid.
     */
    public void updateVariable(LineType lineType) throws VariableException {
        if (!isGlobal) {
            for (String variable : lineType.getVariableList()) {
                Variable argument = VariableFactory.createVariableFromArgument(variable, this.variableMap);
                this.addParamType(argument);

            }
        }
    }
    /**
     * inform isGlobal status
     * @return isGlobal status
     */
    public boolean isGlobal(){
        return isGlobal;
    }

    /**
     * sets isGlobal
     */
    public void setIsGlobal(){
        this.isGlobal = true;
    }

    /**
     * informs that it is not a function
     * @return false
     */
    public boolean isFunction(){
        return false;
    }

    /**
     * this function adds a new function's argument to the linked list hold all of the arguments.
     * @param variable - the variable added
     */
    public void addParamType(Variable variable){
        this.functionTypeParams.add(variable.getType());
    }

    /**
     * getter fot th variable map
     * @return - the hashmap containing all the variables
     */
    public HashMap<String, Variable> getVariableMap(){
        return this.variableMap;
    }

    /**
     * checks if a vairable in this block or exists and initlized in global scope.
     * @param variableName the name of the variable.
     * @return the Variable object if found one, else would return null
     */
    public Variable isVariableInBlock(String variableName){
        if (this.variableMap.containsKey(variableName)) {
            return this.variableMap.get(variableName);
        } else
            return Block.getGlobalVariable(variableName);

    }

    public void updateMap(){
        if (!previousBlock.isGlobal) {
            this.variableMap = new HashMap<>(previousBlock.getVariableMap());
            for (Variable variable: previousBlock.variableMap.values()) {
                if (!variable.isInitialized()){
                    this.variableMap.put(variable.getName(),new Variable(variable.getName(),
                                                                         variable.getType(),
                                                                         variable.isFinal(), false, false));
            }
                
            }
//            HashMap<String, Variable> variableMap = (HashMap<String,
//            Variable>) previousBlock.getVariableMap().entrySet()
//                    .stream()
//                    .filter(map -> map.getValue().isInitialized())
//                    .collect(Collectors.toMap(HashMap.Entry::getKey, HashMap.Entry::getValue));
            
        }
    }
    /**
     * add LineType object to the block's line pool, relevant for method blocks only.
     * @param line object wrap a javas code line with its relevant params
     */
    public void addLine(LineType line) {
        this.blockLines.add(line);
    }

    /**
     * return the pool of lines this block has, relevant only for method blocks.
     * @return linked list hold all lines refer ot this method block
     */
    public LinkedList<LineType> getBlockLines() {
        return blockLines;
    }

    /**
     * return the global vairable map
     * @return the global vairable map
     */
    public static HashMap<String, Variable> getGlobalMap(){
        return Block.globalVariables;
    }

    /**
     * checks if given variable is in global scope
     * @param variableName the variable to search for.
     * @return true if this variable exists in global scope
     */
    private static boolean isInGlobalMap(String variableName) {
        return Block.globalVariables.containsKey(variableName);
    }

    /**
     * return global variable that was already initlized , if exists.
     * @param variableName name of the variable to find
     * @return if it finds it would return the wanted Variable object, else return null.
     */
    private static Variable getGlobalVariable(String variableName) {
        if (Block.isInGlobalMap(variableName) && Block.globalVariables.get(variableName).isInitialized()) {
            return Block.globalVariables.get(variableName);
        }
        return null;
    }
    public LinkedList<Types> getFunctionTypeParams(){
        return this.functionTypeParams;
    }
}
