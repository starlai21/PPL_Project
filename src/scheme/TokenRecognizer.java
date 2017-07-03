package scheme;

/**
 * An instance of TokenRecognizer can be used to recognize
 * a valid token at the beginning of a line of text.
 */
public interface TokenRecognizer {
	/**
	 * Recognize a token at the beginning of the given line of text.
	 * 
	 * @param text    a line of text
	 * @param lineNum the line number of the line of text within its source file 
	 * @return a Token whose lexeme consists of one or more characters
	 *         at the beginning of the given line of text
	 * @throws LexicalAnalysisException if the characters at the
	 *         beginning of the line of text do not form a valid token
	 */
	public Token recognizeToken(String text, int lineNum) throws LexicalAnalysisException;
}
