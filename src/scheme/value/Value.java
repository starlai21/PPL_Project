package scheme.value;

import java.util.List;

import scheme.ASTNode;
import scheme.Environment;
import scheme.InterpreterException;

/**
 * Interface implemented by all objects which represent
 * Scheme values.
 */
public interface Value {
	/** @return the type of this value. */
	public ValueType getValueType();
	
	/**
	 * @return the car of this Cons cell.
	 * @throws UnsupportedOperationException if this value is not a Cons cell
	 */
	public Value getCar();

	/**
	 * @return the cdr of this Cons cell.
	 * @throws UnsupportedOperationException if this value is not a Cons cell
	 */
	public Value getCdr();
	
	/**
	 * @return the Java Integer value represented by this Scheme integer value
	 * @throws UnsupportedOperationException if this value is not an integer value
	 */
	public Integer getInteger();

	/**
	 * @return the Java Boolean value represented by this Scheme boolean value
	 * @throws UnsupportedOperationException if this value is not a boolean value
	 */
	public Boolean getBoolean();
	
	/**
	 * @return the Java String value represented by this Scheme string value
	 * @throws UnsupportedOperationException if this value is not a string value
	 */
	public String getString();
	
	/**
	 * Apply this primitive function to given list of arguments.
	 * @param argList list of argument values
	 * @return the result of the primitive function
	 * @throws InterpreterException if an error occurs (e.g., wrong type of
	 *                              argument value is passed)
	 */
	public Value applyPrimitive(List<Value> argList) throws InterpreterException;
	
	/**
	 * @return the AST (abstract syntax tree) representing the formal parameters
	 *         and body of this user-defined function
	 * @throws UnsupportedOperationException if this value is not a user-defined function
	 */
	public ASTNode getFunctionAST();

	/**
	 * @return the closure environment of this user-defined function
	 * @throws UnsupportedOperationException if this value is not a user-defined function
	 */
	public Environment getClosureEnvironment();
}