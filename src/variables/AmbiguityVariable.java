package variables;

public class AmbiguityVariable extends Variable{
	private final boolean isAmbiguity;

	public AmbiguityVariable(String name, Types type, boolean isFinal, boolean isInitialized,
							 boolean isGlobal, boolean isAmbiguity) {
		super(name, type, isFinal, isInitialized, isGlobal);
		this.isAmbiguity = isAmbiguity;
	}

	public boolean isAmbiguity() {
		return isAmbiguity;
	}
}
