package main;

import variables.Variable;

import java.util.HashMap;

public class Block {

    /**
     * all the variables in the block
     */
    protected HashMap<String, Variable> variableMap = new HashMap<>();

    /**
     * ids the global block, default is false
     */
    private boolean isGlobal = false;

    /**
     * inform isGlobal status
     * @return isGlobal status
     */
    public boolean getIsGlobal(){
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
     * this function adds a new variable to the map
     * @param variable - the variable added
     */
    public void addVariable(Variable variable){
        variableMap.put(variable.getName(),variable);
    }

    /**
     * getter fot th variable map
     * @return - the hashmap containing all the variables
     */
    public HashMap<String, Variable> getVariableMap(){
        return this.variableMap;
    }


}
