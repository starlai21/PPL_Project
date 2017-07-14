package scheme;

import java.util.HashMap;
import java.util.Map;

import scheme.value.Value;

/**
 * An Environment is a collection of bindings
 * of variables (names) to Values.
 * Each Environment (except the global environment)
 * has a parent (outer) environment.
 */
public class Environment {
	private Map<String, Value> valueMap;
	private Environment parent;
	
	/**
	 * Constructor.
	 * 
	 * @param parent the parent environment of the new Environment being initialized
	 */
	public Environment(Environment parent) {
		this.parent = parent;
		this.valueMap = new HashMap<String, Value>();
	}
	
	/**
	 * Look up the Value to which a variable is bound.
	 * If the variable is not bound in this environment,
	 * the parent environment is searched recursively.
	 * 
	 * @param name the name of a variable
	 * @return the Value to which the variable is bound
	 * @throws InterpreterException if the variable is not bound in any environment
	 */
	public Value lookup(String name) throws InterpreterException {
		Environment scope = this;
		
		while (scope != null) {
			Value value = scope.valueMap.get(name);
			if (value != null) {
				return value;
			}
			scope = scope.parent;
		}
		
		throw new InterpreterException("Reference to undefined variable " + name);
	}
	
	/**
	 * Bind a variable to a Value in this environment.
	 * 
	 * @param name  the name of the variable
	 * @param value the Value to which the variable should be bound
	 */
	public void bind(String name, Value value) {
		valueMap.put(name, value);
	}
}