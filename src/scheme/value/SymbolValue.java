package scheme.value;

import java.util.List;

import scheme.ASTNode;
import scheme.Environment;
import scheme.InterpreterException;

/**
 * A SymbolValue object represents a literal Scheme symbol value.
 * For example, the scheme expression
 * 
 * <pre>
 * (quote a)
 * </pre>
 * 
 * would yield a literal symbol value whose name is "a".
 * 
 * Note that this class's constructor is private.
 * Use the getSymbol static method in the ValueFactory
 * class to create SymbolValue objects.
 */
public class SymbolValue implements Value {
	private String name;

	/**
	 * Constructor.  Don't call this constructor directly;
	 * instread, use the getSymbol static method in the ValueFactory
	 * class to create SymbolValue objects.
	 * 
	 * @param name name of the symbol
	 */
	SymbolValue(String name) {
		this.name = name;
	}

	@Override
	public Value applyPrimitive(List<Value> argumentList)
			throws InterpreterException {
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
	public ASTNode getFunctionAST() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Environment getClosureEnvironment() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Integer getInteger() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getString() {
		return name;
	}

	@Override
	public ValueType getValueType() {
		return ValueType.SYMBOL;
	}

	@Override
	public String toString() {
		return name;
	}
}