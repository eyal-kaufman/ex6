package main;

import variables.Variable;

import java.util.LinkedHashMap;

public class Functions extends Block{

    /**
     * the function name
     */
    private final String name;

    /**
     * the variables in the function signature
     */
    private final LinkedHashMap<String, Variable> parameters;

    /**
     * constructor
     * @param name - name of the function
     * @param parameters - the parameters in the the function signature
     */
    public Functions(String name, LinkedHashMap<String, Variable> parameters) {
        this.parameters = parameters;
        this.name = name;
    }

    /**
     * getter name
     */
    public String getName(){
        return this.name;
    }

    /**
     * is this a function
     * @return - true
     */
    @Override
    public boolean isFunction(){
        return true;
    }

    /**
     * getter for parameters
     * @return - map of the parameters
     */
    public LinkedHashMap<String, Variable> getParameters(){
        return parameters;

    }

}
