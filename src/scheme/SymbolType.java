package scheme;

public enum SymbolType {
	// terminal symbols
	LPAREN(true),
	RPAREN(true),
	INTEGER_LITERAL(true),
//	BOOLEAN_LITERAL(true),
	STRING_LITERAL(true),
//	QUOTE_KEYWORD(true),
//	LAMBDA_KEYWORD(true),
//	IF_KEYWORD(true),
//	LET_KEYWORD(true),
//	DEFINE_KEYWORD(true),
//	AND_KEYWORD(true),
//	OR_KEYWORD(true),
//	NOT_KEYWORD(true),
	IDENTIFIER(true),
	PRIME(true),
	EOF(true),
	// nonterminal symbols
	// TODO: add any terminal symbols your grammar uses.
	S(false),
	EXPRS(false),
	EXPR(false),
	LIST(false)
	;
	
	private final boolean  terminal;
	
	private SymbolType(boolean terminal) {
		this.terminal = terminal;
	}
	
	public boolean isTerminal() {
		return terminal;
	}
}
