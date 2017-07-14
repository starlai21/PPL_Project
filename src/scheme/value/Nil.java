package scheme.value;

import java.util.List;

import scheme.ASTNode;
import scheme.Environment;

/**
 * The Nil object represents the empty list.
 * 
 * Note: use the getNil() static method in the ValueFactory
 * class to get the single instance of the Nil class.
 */
public class Nil implements Value {
	/**
	 * Constructor.
	 * Do not use this constructor directly:
	 * instead, use the getNil() static method in the ValueFactory
	 * class to get the single instance of the Nil class.
	 */
	Nil() {
		
	}
	
	@Override
	public Value applyPrimitive(List<Value> argumentList) {
		throw new UnsupportedOperationException();
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
		return ValueType.NIL;
	}

	@Override
	public ASTNode getFunctionAST() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Environment getClosureEnvironment() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public String toString() {
		return ValueFactory.toString(this);
	}
}