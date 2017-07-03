package scheme;

import java.io.IOException;

/**
 * An object implementing the LexicalAnalyzer interface
 * returns a sequence of tokens read from the source text.
 * It supports the capability of looking ahead one token.
 */
public interface LexicalAnalyzer {
	/**
	 * Look ahead for the next token to be returned by the next() method.
	 * 
	 * @return the next Token to be returned by the next() method,
	 *         or null if the end of input has been reached
	 * @throws IOException
	 * @throws LexicalAnalysisException if an invalid sequence of
	 *         characters is encountered
	 */
	public Token peek() throws IOException, LexicalAnalysisException;

	/**
	 * Read one token from the input text and return it.
	 * Throws a LexicalAnalysisException if the end of input has been
	 * reached.
	 * 
	 * @return a Token
	 * @throws IOException
	 * @throws LexicalAnalysisException if an invalid sequence of
	 *         characters is encountered, or if the lexical analyzer
	 *         is at the end of input
	 */
	public Token next() throws IOException, LexicalAnalysisException;
}
