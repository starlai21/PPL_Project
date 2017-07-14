package scheme;

/**
 * Instances of the Token class represent a single token
 * of input from the source text. 
 */
public class Token implements Symbol {
	private final SymbolType symbolType;
	private final String lexeme;
	private final int lineNum;

	/**
	 * Constructor.
	 * 
	 * @param symbolType the token type
	 * @param lexeme    the lexeme of the token as it appeared in the source text
	 */
	public Token(SymbolType symbolType, String lexeme, int lineNum) {
		this.symbolType = symbolType;
		this.lexeme = lexeme;
		this.lineNum = lineNum;
	}
	
	/**
	 * @return the token type of the token
	 */
	public SymbolType getSymbolType() {
		return symbolType;
	}
	
	/**
	 * @return the lexeme of the token
	 */
	public String getLexeme() {
		return lexeme;
	}
	
	/**
	 * @return the line number on which this token occurred
	 */
	public int getLineNum() {
		return lineNum;
	}
	
	@Override
	public Symbol getChild(int index) {
	        throw new IndexOutOfBoundsException();
	}

	@Override
	public int getNumChildren() {
	        return 0;
	}

	@Override
	public void addChild(Symbol child) {
	        throw new UnsupportedOperationException("Can't add children to a Token");
	}
	
	@Override
	public String toString() {
		return symbolType + "[" + lexeme + "]@" + lineNum;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		Token other = (Token) obj;
		
		// note: we intentionally don't compare the line number -
		// it's strictly for information
		
		return this.symbolType == other.symbolType
			&& this.lexeme.equals(other.lexeme);
	}
	
	@Override
	public int hashCode() {
		return symbolType.hashCode() + (39 * lexeme.hashCode());
	}
}
