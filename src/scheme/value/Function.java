package scheme.value;

import java.util.List;

import scheme.ASTNode;
import scheme.Environment;

/**
 * A Function object is a Value which represents a user-defined
 * function (lambda expression).
 */
public class Function implements Value {
	private Environment closureEnv;
	private ASTNode ast;
	
	/**
	 * Constructor.
	 * 
	 * @param closureEnv the closure environment, which is the Environment
	 *                   in which the lambda expression was evaluated
	 * @param ast        the Abstract Syntax Tree (AST) representing the
	 *                   formal parameters and body of the lambda expression
	 */
	public Function(Environment closureEnv, ASTNode ast) {
		this.closureEnv = closureEnv;
		this.ast = ast;
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
		return ValueType.FUNCTION;
	}

	@Override
	public ASTNode getFunctionAST() {
		return ast;
	}

	@Override
	public Environment getClosureEnvironment() {
		return closureEnv;
	}

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		
		buf.append("#<procedure (");
		for (int i = 0; i < ast.getNumChildren() - 1; i++) {
			if (i != 0) {
				buf.append(" ");
			}
			buf.append(ast.getChild(i).getIdentifier());
		}
		buf.append(")>");
		
		return buf.toString();
	}
}