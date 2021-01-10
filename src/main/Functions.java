package main;
//
import parser.LineType;
import variables.Variable;
import variables.VariableException;
import variables.VariableFactory;
//
import java.util.LinkedHashMap;
//
public class Functions extends Block{
	public Functions(Block previousBlock){
		super(previousBlock);

	}

	/**
	 * updates the variable block map with given line of variables, when creating new methods.
	 * @param lineType an object wrap line of javas and its purpose.
	 * @throws VariableException if one of the variables is not valid.
	 */
	public void updateVariable(LineType lineType) throws VariableException {
		if (!this.isGlobal) {
			for (String variable : lineType.getVariableList()) {
				Variable argument = VariableFactory.createVariableFromArgument(variable, this.variableMap);
				this.addParamType(argument);

			}
		}
	}
//
//    /**
//     * the function name
//     */
//    private final String name;
//
//    /**
//     * the variables in the function signature
//     */
//    private final LinkedHashMap<String, Variable> parameters;
//
//    /**
//     * constructor
//     * @param name - name of the function
//     * @param parameters - the parameters in the the function signature
//     */
//    public Functions(String name, LinkedHashMap<String, Variable> parameters) {
//        this.parameters = parameters;
//        this.name = name;
//    }
//
//    /**
//     * getter name
//     */
//    public String getName(){
//        return this.name;
//    }
//
//    /**
//     * is this a function
//     * @return - true
//     */
//    @Override
//    public boolean isFunction(){
//        return true;
//    }
//
//    /**
//     * getter for parameters
//     * @return - map of the parameters
//     */
//    public LinkedHashMap<String, Variable> getParameters(){
//        return parameters;
//
//    }
//
}
