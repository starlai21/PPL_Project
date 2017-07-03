package scheme;

/**
 * A LexicalAnalysisException indicates that the source
 * text contains a sequence of characters that cannot be matched
 * by any legal token pattern.
 */
public class LexicalAnalysisException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * 
	 * @param text     the text that could not be matched
	 * @param lineNum  the line number where the unmatched text occurred
	 */
	public LexicalAnalysisException(String text, int lineNum) {
		super("Lexical analysis error at line " + lineNum + " at input " + text);
	}
}
