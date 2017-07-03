package scheme;

/**
 * An object implementing the Symbol interface represents
 * a terminal symbol (Token) or a nonterminal symbol
 * (Nonterminal) in the parse tree.
 */
public interface Symbol {
	public SymbolType getSymbolType();
	
	public int getNumChildren();
	
	public Symbol getChild(int index);
}
