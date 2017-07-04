package scheme;

import java.io.IOException;

/**
 * Using a LexicalAnalyzer object to read tokens, find a derivation for the
 * string of tokens and build a parse tree representing the derivation.
 */
public class Parser {
	private PushbackLexicalAnalyzer lexer;

	public Parser(LexicalAnalyzer lexer) {
		this.lexer = new PushbackLexicalAnalyzer(lexer);
	}

	public Symbol parse() throws IOException, LexicalAnalysisException, ParserException {
		return parseS();
	}

	private Symbol parseS() throws IOException, LexicalAnalysisException, ParserException {

		// root of the parse tree

		Nonterminal s = new Nonterminal(SymbolType.S);

		s.addChild(parseEXPRS(false));

		return s;
	}

	private Symbol parseEXPRS(boolean calledByParseList) throws IOException, LexicalAnalysisException, ParserException {

		Nonterminal exprs = new Nonterminal(SymbolType.EXPRS);

		Token t = lexer.peek();
		if (t != null && ((t.getSymbolType() != SymbolType.RPAREN && calledByParseList) || !calledByParseList)) {
			exprs.addChild(parseEXPR());
			exprs.addChild(parseEXPRS(calledByParseList));
		}

		return exprs;
	}

	private Symbol parseEXPR() throws IOException, LexicalAnalysisException, ParserException {

		Nonterminal expr = new Nonterminal(SymbolType.EXPR);

		Token t = lexer.next();
		if (t.getSymbolType() == SymbolType.PRIME) {
			expr.addChild(t);
			expr.addChild(parseEXPR());
		} else if (t.getSymbolType() == SymbolType.IDENTIFIER) {
			expr.addChild(t);
		} else if (t.getSymbolType() == SymbolType.INTEGER_LITERAL) {
			expr.addChild(t);
		} else if (t.getSymbolType() == SymbolType.STRING_LITERAL) {
			expr.addChild(t);
		} else if (t.getSymbolType() == SymbolType.LPAREN) {
			lexer.putBack(t);
			expr.addChild(parseLIST());
		} else {
			throw new ParserException("At line " + t.getLineNum() + ": get unexpected input : " + t.getLexeme());
		}

		return expr;
	}

	private Symbol parseLIST() throws IOException, LexicalAnalysisException, ParserException {

		Nonterminal list = new Nonterminal(SymbolType.LIST);

		list.addChild(expect(SymbolType.LPAREN));
		list.addChild(parseEXPRS(true));
		list.addChild(expect(SymbolType.RPAREN));

		return list;
	}

	// The parser can call this method when it expects to consume a
	// particular kind of token.
	private Token expect(SymbolType expectedSymbolType) throws IOException, LexicalAnalysisException, ParserException {
		Token t = lexer.next();
		if (t.getSymbolType() != expectedSymbolType) {
			throw new ParserException(
					"At line " + t.getLineNum() + ": expected " + expectedSymbolType + ", saw " + t.getSymbolType());
		}
		return t;
	}

	// The parser can call this method when it needs to look ahead at the
	// next token (without consuming it), and must *not* see the
	// end of input.
	private Token peekNext() throws IOException, LexicalAnalysisException, ParserException {
		Token t = lexer.peek();
		if (t == null) {
			throw new ParserException("unexpected end of input");
		}
		return t;
	}
}
