package scheme;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Implementation of LexicalAnalyzer.
 */
public class LexicalAnalyzerImpl implements LexicalAnalyzer {
	private BufferedReader reader;
	private TokenRecognizer tokenRecognizer;
	private int lineNum;
	private String lineBuf;
	private Token cachedToken;
	private boolean atEOF;
	
	public LexicalAnalyzerImpl(Reader reader, TokenRecognizer tokenRecognizer) {
		this.reader = new BufferedReader(reader);
		this.tokenRecognizer = tokenRecognizer;
	}
	
	@Override
	public Token peek() throws IOException, LexicalAnalysisException {
		fill();
		return cachedToken;
	}
	
	@Override
	public Token next() throws IOException, LexicalAnalysisException {
		fill();
		if (cachedToken == null) {
			throw new LexicalAnalysisException("Unexpected end of input", lineNum);
		}
		Token result = cachedToken;
		cachedToken = null;
		return result;
	}
	
	private void fill() throws IOException, LexicalAnalysisException {
		// if we already have a cached token, then there's nothing to do
		if (cachedToken != null) {
			return;
		}
		
		while (!atEOF && !haveNonEmptyLine()) {
			readOneLine();
		}
		if (atEOF) {
			return;
		}
		
		// try to recognize a token at the beginning of the line buffer
		cachedToken = tokenRecognizer.recognizeToken(lineBuf, lineNum);
		//System.out.println(lineBuf);
		
		// consume the lexeme
		lineBuf = lineBuf.substring(cachedToken.getLexeme().length());
	}

	private boolean haveNonEmptyLine() {
		if (lineBuf == null) {
			return false;
		}
		// skip leading whitespace
		for (int i = 0; i < lineBuf.length(); i++) {
			char c = lineBuf.charAt(i);
			if (!Character.isWhitespace(c)) {
				// trim any leading whitespace
				lineBuf = lineBuf.substring(i);
				return true;
			}
		}
		lineBuf = "";
		return false; // the entire line buffer is whitespace
	}

	private void readOneLine() throws IOException {
		lineBuf = reader.readLine();
		if (lineBuf == null) {
			atEOF = true;
		}
		lineNum++;
	}
}
