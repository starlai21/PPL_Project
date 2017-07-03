package scheme;

public class ParserException extends Exception {
	private static final long serialVersionUID = 1L;

	public ParserException(String msg) {
		super(msg);
	}

	public ParserException(String msg, int lineNum) {
		super("At line " + lineNum + ": "  + msg);
	}
}
