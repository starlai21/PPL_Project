package scheme.value;

import java.util.List;

import scheme.ASTNode;
import scheme.Environment;
import scheme.InterpreterException;

/**
 * A PrimitiveFunction object represents a primitive function.
 * It is an abstract class: subclasses must implement the
 * applyPrimitive method.
 */
public abstract class PrimitiveFunction implements Value {

	private String name;
	private ValueType[] argTypes;
	
	/**
	 * Constructor.
	 * 
	 * @param name      the name of this primitive function
	 * @param argTypes  array containing the argument types required by this primitive function
	 */
	protected PrimitiveFunction(String name, ValueType[] argTypes) {
		this.name = name;
		this.argTypes = argTypes;
	}
	
	/**
	 * Check given argument list against the required argument types.
	 * This method should be called from the applyPrimitive method.
	 * 
	 * @param argList the list of argument Values to which the primitive
	 *                function is being applied
	 * @throws InterpreterException if one of the argument types doesn't
	 *                              match the required argument types
	 */
	protected void checkArgs(List<Value> argList) throws InterpreterException {
		if (argList.size() != argTypes.length) {
			throw new InterpreterException("Wrong number of arguments (" + argList.size() +
					") passed to " + toString());
		}
		
		int arg = 0;
		for (Value v : argList) {
			if (!argTypes[arg].checkArgument(v.getValueType())) {
				throw new InterpreterException("Type mismatch in call to " + toString() +
						": arg " + arg + " is " + v.getValueType() + ", expected " + argTypes[arg]);
			}
			arg++;
		}
	}

	@Override
	public Boolean getBoolean() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Value getCar() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Value getCdr() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Integer getInteger() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getString() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ValueType getValueType() {
		return ValueType.PRIMITIVE;
	}
	
	@Override
	public ASTNode getFunctionAST() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Environment getClosureEnvironment() {
		throw new UnsupportedOperationException();
	}
	
	public String getPrimitiveFunctionName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "#<primitive " + getPrimitiveFunctionName() + ">";
	}

}