package scheme;

/**
 * An InterpreterException is thrown if there is a fatal error
 * (e.g., undefined variable, wrong arg type passed to primitive function)
 * while evaluating a Scheme expression or definition.
 */
public class InterpreterException extends Exception {
	private static final long serialVersionUID = 1L;

	public InterpreterException(String msg) {
		super(msg);
	}
}