package oop.ex6.main;


import oop.ex6.variables.Variable;


import java.util.HashMap;


/**
 * describe a scope in Sjavac: section of code that could be described as all the global members, or
 * specific method, or inner scope such as if or while section.
 */
public class Block {
    /** pointer to the previous block if exists*/
    private final Block previousBlock;
    /**
     * all the oop.ex6.variables in the block
     */
    protected HashMap<String, Variable> variableMap = new HashMap<>();
    /** create new block so if there is no pointer to previous block, it would set it as global
     * scope.*/
    public Block(Block previousBlock) {
        this.previousBlock = previousBlock;
        if (previousBlock == null) {
            setIsGlobal();
            this.variableMap = ReadFile.globalVariables;
        }
    }

    /**
     * ids the global block, default is false
     */
    protected boolean isGlobal = false;


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
     * getter fot th variable map
     * @return - the hashmap containing all the oop.ex6.variables
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

    /**
     * shallow copy the previous block map of all the oop.ex6.variables that were initialized, and overwrite
     * the oop.ex6.variables the were not initialized.
     */
    public void updateMap(){
        if (!previousBlock.isGlobal) {
            this.variableMap = new HashMap<>(previousBlock.getVariableMap());
        }
    }

    /**
     * checks if given variable is in global scope
     * @param variableName the variable to search for.
     * @return true if this variable exists in global scope
     */
    private static boolean isInGlobalMap(String variableName) {
        return ReadFile.globalVariables.containsKey(variableName);
    }

    /**
     * return global variable that was already initlized , if exists.
     * @param variableName name of the variable to find
     * @return if it finds it would return the wanted Variable object, else return null.
     */
    private static Variable getGlobalVariable(String variableName) {
        if (Block.isInGlobalMap(variableName) && ReadFile.globalVariables.get(variableName).isInitialized()) {
            return ReadFile.globalVariables.get(variableName);
        }
        return null;
    }


}
