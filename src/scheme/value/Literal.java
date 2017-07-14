package scheme.value;

import java.util.List;

import scheme.ASTNode;
import scheme.Environment;

/**
 * A Literal object represents a Scheme integer, boolean, or string value.
 */
public class Literal implements Value {

	private Object value;
	
	/**
	 * Constructor.
	 * NOTE: Don't use this constructor directly.  Use the
	 * javaToSchemeValue static method in the ValueFactory class. 
	 * 
	 * @param value a Java Integer, Boolean, or String value
	 */
	public Literal(Object value) {
		this.value = value;
	}
	
	@Override
	public Value applyPrimitive(List<Value> argumentList) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Boolean getBoolean() {
		if (getValueType() != ValueType.BOOLEAN) {
			throw new IllegalStateException();
		}
		return (Boolean) value;
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
		if (getValueType() != ValueType.INTEGER) {
			throw new IllegalStateException();
		}
		return (Integer) value;
	}

	@Override
	public String getString() {
		if (getValueType() != ValueType.STRING) {
			throw new IllegalStateException();
		}
		return (String) value;
	}

	@Override
	public ValueType getValueType() {
		return ValueType.valueOf(value.getClass().getSimpleName().toUpperCase());
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
		if (value instanceof String) {
			return "\"" + value + "\"";
		} else if (value instanceof Boolean) {
			return "#" + (getBoolean() ? "t" : "f");
		} else {
			return value.toString();
		}
	}
}