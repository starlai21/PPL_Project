package scheme;

import java.io.IOException;
import java.util.Stack;

public class PushbackLexicalAnalyzer implements LexicalAnalyzer {
	private LexicalAnalyzer lexer;
	private Stack<Token> storage;
	
	public PushbackLexicalAnalyzer(LexicalAnalyzer lexer) {
		this.lexer = lexer;
		storage = new Stack<Token>();
	}
	
	@Override
	public Token peek() throws IOException, LexicalAnalysisException {
		if (!storage.isEmpty()) {
			return storage.peek();
		} else {
			return lexer.peek();
		}
	}
	
	@Override
	public Token next() throws IOException, LexicalAnalysisException {
		if (!storage.isEmpty()) {
			return storage.pop();
		} else {
			return lexer.next();
		}
	}
	
	public void putBack(Token t) {
		storage.push(t);
	}
}
