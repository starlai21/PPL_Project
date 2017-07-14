package scheme.value;

import java.util.List;

import scheme.ASTNode;
import scheme.Environment;

/**
 * A Cons object is a Scheme object representing
 * a cons cell (i.e., a list node).
 */
public class Cons implements Value {
	private Value car, cdr;
	
	/**
	 * Constructor.
	 * 
	 * @param car Value which is the car of the cons cell
	 * @param cdr Value which is the cdr of the cons cell
	 */
	public Cons(Value car, Value cdr) {
		this.car = car;
		this.cdr = cdr;
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
		return car;
	}

	@Override
	public Value getCdr() {
		return cdr;
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
		return ValueType.CONS;
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